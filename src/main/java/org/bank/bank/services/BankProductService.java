package org.bank.bank.services;

import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;

import java.util.List;

public interface BankProductService {

    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product openNewProductByCustomer(CustomerProduct customerProduct);
}
