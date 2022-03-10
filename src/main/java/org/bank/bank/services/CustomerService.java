package org.bank.bank.services;

import org.bank.bank.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {

        List<Customer>  getAllCustomers();
        Customer getCustomerDetails(long id);
        Customer addCustomer(Customer customer);
        List<Customer> getCustomersByPassNumber(String passNumber);

}
