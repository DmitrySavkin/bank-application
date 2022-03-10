package org.bank.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(BankApplication.class, args);
	}

}
