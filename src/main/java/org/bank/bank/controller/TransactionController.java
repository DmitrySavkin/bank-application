package org.bank.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bank.models.Account;
import org.bank.bank.models.Credit;
import org.bank.bank.models.Customer;
import org.bank.bank.models.CustomerProduct;
import org.bank.bank.services.BankProductService;
import org.bank.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/transaction")
    public String transaction (Model model) {
        model.addAttribute("customer", new Customer());
        return "transaction";
    }

    @PostMapping("/choosing-account")
    public String choosingAccount(Model model,   @ModelAttribute("customer") Customer customer){
        Customer customer1 = customerService.getCustomerByLastnameAndPassword(customer);
        System.out.println(customer1);
        return "transaction";

    }
}
