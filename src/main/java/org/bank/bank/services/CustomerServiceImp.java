package org.bank.bank.services;

import lombok.*;
import org.bank.bank.models.Customer;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.paging.*;
import org.bank.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerServiceImp implements CustomerService {

    private static final Comparator<? super CustomerDTO> EMPTY_COMPORATOR = (e1, e2) -> 0;


    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerDetails(long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersByPassNumber(String passNumber) {
        return customerRepository.findByPassNumberContains(passNumber);
    }

    public PageArray getCustomersArray(PagingRequest pagingRequest) {

        pagingRequest.setColumns(Stream.of("id","firstname", "lastname", "passNumber", "email")
                .map(Column::new)
                .collect(Collectors.toList()));

        Page<CustomerDTO> customerPage = getCustomers(pagingRequest);
        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(customerPage.getRecordsFiltered());
        pageArray.setRecordsTotal(customerPage.getRecordsTotal());
        pageArray.setDraw(customerPage.getDraw());
        pageArray.setData(customerPage.getData().stream()
                        .map(this::toStringList).collect(Collectors.toList()));

        return pageArray;
    }

    private List<String> toStringList(CustomerDTO customer)  {
       return Arrays.asList( customer.getFirstname(), customer.getLastname(),
                customer.getPassNumber(), customer.getEmail());
    }
    public Page<CustomerDTO> getCustomers(PagingRequest pagingRequest) {
        List<CustomerDTO> customers = getAllCustomers().stream().map(c -> c.toCustomerDto()).collect(Collectors.toList());
        return  getPage(customers, pagingRequest);
    }

    public Page<CustomerDTO> getPage(List<CustomerDTO> customers, PagingRequest pagingRequest) {
        List<CustomerDTO> filtered = customers.stream()
                .sorted(sortCustomers(pagingRequest))
                .filter(filterEmployee(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = customers.stream().filter(filterEmployee(pagingRequest)).count();
        Page<CustomerDTO> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(page.getDraw());
        return page;
    }

    private Predicate<? super CustomerDTO> filterEmployee(PagingRequest pagingRequest) {
            if (Objects.isNull(pagingRequest.getSearch()) )
                return  customer -> true;

            String value = pagingRequest.getSearch().getValue();
            return customer -> customer.getFirstname().toLowerCase().contains(value)
                    ||
                    customer.getLastname().toLowerCase().contains(value)
                    ||
                    customer.getPassNumber().contains(value)
                    ||
                    customer.getEmail().toLowerCase().contains(value);
    }

    private Comparator<? super CustomerDTO> sortCustomers(PagingRequest pagingRequest) {
        if (Objects.isNull(pagingRequest.getOrder())){
            return EMPTY_COMPORATOR;
        }
        try {
            Order order = pagingRequest.getOrder().get(0);
            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns().get(columnIndex);
            Comparator<CustomerDTO> comparator = CustomerComporator.getComporator(column.getData(), order.getDir());
            if (Objects.isNull(comparator)) {
                return EMPTY_COMPORATOR;
            }
            return comparator;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY_COMPORATOR;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class CustomerComporator {

         @Getter
         @Setter
         @AllArgsConstructor
        static  class Key {
            String name;
            Direction dir;

        }
        private String data;
        private Boolean orderable;
        static Map<Key, Comparator<CustomerDTO>> map = new HashMap<>();
        static {
            map.put(new Key("firstname", Direction.asc), Comparator.comparing(CustomerDTO::getFirstname));
            map.put(new Key("firstname", Direction.desc), Comparator.comparing(CustomerDTO::getFirstname).reversed());

            map.put(new Key("lastname", Direction.asc), Comparator.comparing(CustomerDTO::getLastname));
            map.put(new Key("lastname", Direction.desc), Comparator.comparing(CustomerDTO::getLastname).reversed());
            map.put(new Key("email", Direction.asc), Comparator.comparing(CustomerDTO::getEmail));
            map.put(new Key("email", Direction.desc), Comparator.comparing(CustomerDTO::getEmail).reversed());
            map.put(new Key("passnumber", Direction.asc), Comparator.comparing(CustomerDTO::getPassNumber));
            map.put(new Key("passnumber", Direction.desc), Comparator.comparing(CustomerDTO::getPassNumber).reversed());

        }


        public static Comparator<CustomerDTO> getComporator(String name, Direction dir) {
            return map.get(new Key(name, dir));
        }
    }



}
