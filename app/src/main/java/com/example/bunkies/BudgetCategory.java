package com.example.bunkies;

import java.io.Serializable;

public class BudgetCategory implements Serializable {
    double goal;
    double totalSpent;
    String name;
    String description;
    Transaction[] transactions;

    public BudgetCategory(double goal, String name, String description, Transaction[] transactions) {
        this.goal = goal;
        //this.totalSpent = totalSpent;
        this.name = name;
        this.description = description;
        this.transactions = transactions;
    }
}