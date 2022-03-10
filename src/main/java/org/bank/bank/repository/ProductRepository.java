package org.bank.bank.repository;

import org.bank.bank.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
