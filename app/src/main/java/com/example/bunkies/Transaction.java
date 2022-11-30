package com.example.bunkies;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Transaction implements Serializable {
    String name;
    double amount;
    String date;

    public Transaction(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return date + ": " + name + " (" + formatter.format(amount) + ")";
    }
}
