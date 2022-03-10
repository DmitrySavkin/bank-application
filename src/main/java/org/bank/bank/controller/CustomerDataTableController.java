package org.bank.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bank.models.Customer;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.paging.Page;
import org.bank.bank.models.paging.PageArray;
import org.bank.bank.models.paging.PagingRequest;
import org.bank.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer-datatable")
public class CustomerDataTableController {


    @Autowired
    private CustomerService customerService;


    @PostMapping
    public Page<CustomerDTO> list(@RequestBody PagingRequest pagingRequest) {
        Page<CustomerDTO> customers = customerService.getCustomers(pagingRequest);
        log.info("Customer page " + customers);
        return  customers;
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        PageArray array = customerService.getCustomersArray(pagingRequest);
        log.info("Customer array " + array);
        return array;
    }
}
