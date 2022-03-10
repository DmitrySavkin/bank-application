package org.bank.bank.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public  class CustomerProductPK  implements Serializable {
    long customer;
    long product;
}