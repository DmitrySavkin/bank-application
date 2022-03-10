package org.bank.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.dtio.ProductDTO;
import org.bank.bank.models.paging.Page;
import org.bank.bank.models.paging.PageArray;
import org.bank.bank.models.paging.PagingRequest;
import org.bank.bank.services.BankProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/api/v1/product-datatable")
public class BankProductDataTableController {

    @Autowired
    private BankProductService bankProductService;


    @PostMapping
    public Page<ProductDTO> list(@RequestBody PagingRequest pagingRequest) {
        Page<ProductDTO> products = bankProductService.getProducts(pagingRequest);
        log.info("Customer page " + products);
        return  products;
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        PageArray array = bankProductService.getProductArray(pagingRequest);
        log.info("Customer array " + array);
        return array;
    }
}
