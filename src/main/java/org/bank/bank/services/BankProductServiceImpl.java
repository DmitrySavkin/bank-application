package org.bank.bank.services;

import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;
import org.bank.bank.repository.CustomerRepository;
import org.bank.bank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankProductServiceImpl implements  BankProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

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
        customerRepository.save(customerProduct.getCustomer());
        return customerProduct.getProduct();
    }
}
