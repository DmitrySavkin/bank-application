package org.bank.bank.models.dtio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bank.bank.models.Customer;
import org.bank.bank.models.CustomerProduct;
import org.bank.bank.models.Product;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProductDTO {

    private Customer customer;
    private Product product;
    private BigDecimal amount;


    public CustomerProductDTO(Customer customer) {
        this.customer = customer;
    }

}
