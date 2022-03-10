package org.bank.bank.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "customers_products")
@IdClass(CustomerProduct.class)
public class CustomerProduct implements  Serializable{

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Product product;

    private BigDecimal amount;


    public CustomerProduct(Customer customer) {
        this.customer = customer;
    }


    public  class CustomerProductPK  implements Serializable {
        long customer;
        long product;
    }

}
