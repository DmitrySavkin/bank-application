package org.bank.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bank.models.Customer;
import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;
import org.bank.bank.models.dtio.CustomerProductDTO;
import org.bank.bank.models.paging.Page;
import org.bank.bank.models.paging.PageArray;
import org.bank.bank.models.paging.PagingRequest;
import org.bank.bank.services.BankProductService;
import org.bank.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/customers")
@Slf4j
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @Autowired
    private BankProductService bankProductService;

    @GetMapping
    public String hello(Model model) {
        model.addAttribute("customer", new Customer());

        // new Customer().getClass().getName()
        return "index";
    }

    @PostMapping("/add-new-customer")
    public RedirectView addNewCustomer (@ModelAttribute("customer") Customer customer, BindingResult result) {
        log.info("Customer: " + customer);
        customerService.addCustomer(customer);
        return new RedirectView("/customers/");
    }

    @GetMapping("/find-customer/")
    public String customers(Model model) {

            // new Customer().getClass().getName()
            return "index";
    }

   @GetMapping("/customer")
   public String getCustomerDetail(@RequestParam long id, Model model) {
        log.info("id "  + id);
        log.info("Customer " + customerService.getCustomerDetails(id));
        model.addAttribute("customer", customerService.getCustomerDetails(id));
        model.addAttribute("availableProducts", bankProductService.getAllProducts());
        model.addAttribute("customerProduct", new CustomerProduct(customerService.getCustomerDetails(id)));
       return "customer-profil";
   }

    @GetMapping("/find-customer/{passnumber}")
    public String findCustomerByPassnumber(@PathVariable @Nullable  String passnumber, Model model) {

        log.info("Passnumber " + passnumber);
        return "index :: customer-fragment";
    }


}
