package org.bank.bank.services;

import org.bank.bank.models.Customer;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.paging.Page;
import org.bank.bank.models.paging.PageArray;
import org.bank.bank.models.paging.PagingRequest;

import java.util.List;


public interface CustomerService {

        List<Customer>  getAllCustomers();
        Customer getCustomerDetails(long id);
        Customer addCustomer(Customer customer);
        List<Customer> getCustomersByPassNumber(String passNumber);
        Page<CustomerDTO> getPage(List<CustomerDTO> customers, PagingRequest pagingRequest);
        Page<CustomerDTO> getCustomers(PagingRequest pagingRequest);
        PageArray getCustomersArray(PagingRequest pagingRequest);

    Customer getCustomerByLastnameAndPassword(Customer customer);
}
