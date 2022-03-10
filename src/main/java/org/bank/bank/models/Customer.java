package org.bank.bank.models;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String passNumber;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="customer_products",
            joinColumns = {
            @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
            @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, updatable = false)})

     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerProduct> customerProduct = new ArrayList<>();

    public void addCustomerProduct(CustomerProduct cp) {
        customerProduct.add(cp);
    }


    //INSERT INTO CUSTOMERS VALUES ('d576ab3b-8a36-4dff-b50c-9d9d4ca6072c','dimsaz@mail.ru', 'Alex', 'Marksus');
    /*
    INSERT INTO CUSTOMERS VALUES (1,'dimsaz@mail.ru', 'Alex', 'Marksus');
    INSERT INTO CUSTOMERS VALUES (2,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (3,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (4,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (5,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (6,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (7,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (8,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (9,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (10,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (11,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (12,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (13,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO CUSTOMERS VALUES (14,'dimsaz@mail.ru', 'Mary', 'Poolsha');
    INSERT INTO ACCOUNT VALUES (1000, 8694, 'Test account', 1);
    INSERT INTO ACCOUNT VALUES (1001, 8694, 'Test account', 1);
    INSERT INTO ACCOUNT VALUES (1003, 694, 'Test account', 1);
    INSERT INTO ACCOUNT VALUES (1004, 82694, 'Test account', 1);
    INSERT INTO ACCOUNT VALUES (1005, 94, 'Test account', 1);

     */

}
