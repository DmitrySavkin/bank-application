package org.bank.bank.services;

import org.bank.bank.models.Customer;
import org.bank.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

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
}
