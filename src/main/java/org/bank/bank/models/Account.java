package org.bank.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account extends Product {

    @Nullable
    @Column(nullable = true)
    private String minimalTerm;
    @Nullable
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
