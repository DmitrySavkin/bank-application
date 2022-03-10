package org.bank.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class Credit extends  Product {

    private int failTerm;
    private String duration;
    private double rate;

    @Override
    public String getProductType() {
        return "credit";
    }

    @Override
    public String getInformationAboutProduct() {
        StringBuilder sb = new StringBuilder();
        sb.append("Duration " + duration);
        sb.append(System.lineSeparator());
        sb.append("Rate " + rate);
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
