package org.bank.bank.services;

import lombok.*;
import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.dtio.ProductDTO;
import org.bank.bank.models.paging.*;
import org.bank.bank.repository.ProductRepository;
import org.bank.bank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BankProductServiceImpl implements  BankProductService {

    private static final Comparator<? super ProductDTO> EMPTY_COMPORATOR = (e1, e2) -> 0;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepository customerRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product openNewProductByCustomer(CustomerProduct customerProduct) {
        customerProduct.getCustomer().addCustomerProduct(customerProduct);
        customerRepository.save(customerProduct.getProduct());
        return customerProduct.getProduct();
    }


    public PageArray getProductArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("id", "title", "description", "type")
                .map(Column::new)
                .collect(Collectors.toList()));

        Page<ProductDTO> customerPage = getProducts(pagingRequest);
        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(customerPage.getRecordsFiltered());
        pageArray.setRecordsTotal(customerPage.getRecordsTotal());
        pageArray.setDraw(customerPage.getDraw());
        pageArray.setData(customerPage.getData().stream()
                        .map(this::toStringList).collect(Collectors.toList()));

        return pageArray;
}

    private List<String> toStringList(ProductDTO product)  {
        return Arrays.asList(product.getTitle(), product.getDescription());
    }
    public Page<ProductDTO> getProducts(PagingRequest pagingRequest) {
        List<ProductDTO> customers = getAllProducts().stream().map(c -> c.toProductDTO()).collect(Collectors.toList());
        return  getPage(customers, pagingRequest);
    }

    public Page<ProductDTO> getPage(List<ProductDTO> customers, PagingRequest pagingRequest) {
        List<ProductDTO> filtered = customers.stream()
                .sorted(sortProducts(pagingRequest))
                .filter(filterEmployee(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = customers.stream().filter(filterEmployee(pagingRequest)).count();
        Page<ProductDTO> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(page.getDraw());
        return page;
    }

    private Predicate<? super ProductDTO> filterEmployee(PagingRequest pagingRequest) {
        if (Objects.isNull(pagingRequest.getSearch()) )
            return  product -> true;

        String value = pagingRequest.getSearch().getValue();
        return product -> product.getTitle().toLowerCase().contains(value);


    }

    private Comparator<? super ProductDTO> sortProducts(PagingRequest pagingRequest) {
        if (Objects.isNull(pagingRequest.getOrder())){
            return EMPTY_COMPORATOR;
        }
        try {
            Order order = pagingRequest.getOrder().get(0);
            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns().get(columnIndex);
            Comparator<ProductDTO> comparator = ProductComporator.getComporator(column.getData(), order.getDir());
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
    static class ProductComporator {

        @Getter
        @Setter
        @AllArgsConstructor
        static  class Key {
            String name;
            Direction dir;

        }
        private String data;
        private Boolean orderable;
        static Map<Key, Comparator<ProductDTO>> map = new HashMap<>();
        static {
            map.put(new Key("title", Direction.asc), Comparator.comparing(ProductDTO::getTitle));
            map.put(new Key("title", Direction.desc), Comparator.comparing(ProductDTO::getTitle).reversed());

        }


        public static Comparator<ProductDTO> getComporator(String name, Direction dir) {
            return map.get(new CustomerServiceImp.CustomerComporator.Key(name, dir));
        }
    }
}
