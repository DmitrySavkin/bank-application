package org.bank.bank.models;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Deposit extends  Account {

    private boolean returnable;
    private boolean replinished;
    private double rate;
}
