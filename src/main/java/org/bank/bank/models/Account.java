package org.bank.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account extends Product {


    @Column(nullable = true)
    private int minimalTerm;
    @Column(nullable = true)
    private String duration;

    @Override
    public String getProductType() {
        return "account";
    }

    @Override
    public String getInformationAboutProduct() {
        StringBuilder sb = new StringBuilder();
        sb.append("Minimal duration: " + duration);
        sb.append(System.lineSeparator());
        sb.append("Minimal term " + minimalTerm);
        return  sb.toString();
    }
}
