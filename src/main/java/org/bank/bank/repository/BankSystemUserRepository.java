package org.bank.bank.repository;

import org.bank.bank.models.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankSystemUserRepository extends JpaRepository<BankUser, Integer> {

    Optional<BankUser> findByUsername(String name);
}
