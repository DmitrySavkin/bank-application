package org.bank.bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.bank.bank.models.*;
import org.bank.bank.models.dtio.CustomerProductDTO;
import org.bank.bank.services.BankProductService;
import org.bank.bank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Paths;

@Controller
@Slf4j
public class BankProductController {

    @Autowired
    private BankProductService bankProductService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/products")
    public String allProducts(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("credit", new Credit());
        model.addAttribute("products", bankProductService.getAllProducts());
        log.info("Products " + bankProductService.getAllProducts());
        return "bank-product";
    }

    @PostMapping("/products/add-new-account")
    public RedirectView addNewAccount(@ModelAttribute("account")  Account account) {
        bankProductService.addNewProduct(account);
        log.info("new account " + account);
        return new RedirectView("/products/");
    }

    @PostMapping("/products/add-new-credit")
    public RedirectView addNewCredit(@ModelAttribute("credit")  Credit credit) {
        bankProductService.addNewProduct(credit);
        log.info("new credit " + credit);
        return new RedirectView("/products/");
    }

    @PostMapping(path = "/products/new-product-by-customer")
    public String openProduct(CustomerProduct customerProduct, Model model) {
        log.info("Opening  " + customerProduct);
        bankProductService.openNewProductByCustomer(customerProduct);
        log.info("After Opening  " + customerProduct);
        model.addAttribute("availableProducts", bankProductService.getAllProducts());
        model.addAttribute("customer", customerService.getCustomerDetails(customerProduct.getCustomer().getId()));
        return "redirect:/customers/customer?id=" + customerProduct.getCustomer().getId() ;
    }


}
