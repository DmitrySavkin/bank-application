package org.bank.bank.services;

import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;
import org.bank.bank.models.dtio.CustomerDTO;
import org.bank.bank.models.dtio.ProductDTO;
import org.bank.bank.models.paging.Page;
import org.bank.bank.models.paging.PageArray;
import org.bank.bank.models.paging.PagingRequest;

import java.util.List;

public interface BankProductService {

    List<Product> getAllProducts();
    Product addNewProduct(Product product);
    Product openNewProductByCustomer(CustomerProduct customerProduct);
    Page<ProductDTO> getPage(List<ProductDTO> products, PagingRequest pagingRequest);
    Page<ProductDTO> getProducts(PagingRequest pagingRequest);
    PageArray getProductArray(PagingRequest pagingRequest);
}
