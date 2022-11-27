package com.example.bunkies;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    String name;
    double amount;
    Date date;

    public Transaction(String name, double amount, Date date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }
}
