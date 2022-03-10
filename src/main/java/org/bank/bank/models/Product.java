package org.bank.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerProduct> customerProduct =  new ArrayList<>();
    private LocalDate openingDate;
    //
    public abstract String getProductType();

    public String getInformationAboutProduct(){
        return getDescription();
    }

    public void addCustomerProduct(CustomerProduct cp) {
        customerProduct.add(cp);
    }
}
