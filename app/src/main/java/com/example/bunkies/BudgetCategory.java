package com.example.bunkies;

import java.io.Serializable;
import java.util.List;

public class BudgetCategory implements Serializable {
    double goal;
    double totalSpent;
    String name;
    String description;
    List<Transaction> transactions;

    public BudgetCategory(double goal, String name, String description, List<Transaction> transactions) {
        this.goal = goal;
        //this.totalSpent = totalSpent;
        this.name = name;
        this.description = description;
        this.transactions = transactions;
    }
}