package org.bank.bank.repository;

import org.bank.bank.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByPassNumberContains(String passnumber);
    Customer findByLastnameAndPassNumber(String lastname, String passnumber);
}
