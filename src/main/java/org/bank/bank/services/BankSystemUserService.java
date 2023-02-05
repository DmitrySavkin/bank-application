package org.bank.bank.services;

import org.bank.bank.config.PasswordConfig;
import org.bank.bank.models.BankUser;
import org.bank.bank.repository.BankSystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BankSystemUserService implements UserDetailsService {

    @Autowired
    private BankSystemUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public  void init() {
        repository.save(new BankUser(1, "dsa", passwordEncoder.encode("password"), "ADMIN", true, true, true, true));
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails details = repository.findByUsername(s).get();
        return details;
    }
}
