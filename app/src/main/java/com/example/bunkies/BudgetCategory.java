package com.example.bunkies;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;

public class BudgetCategory implements Serializable {
    double goal;
    double totalSpent;
    String name;
    String description;
    List<Transaction> transactions;

    public BudgetCategory(double goal, String name, String description, List<Transaction> transactions) {
        this.goal = goal;
        this.name = name;
        this.description = description;
        this.transactions = transactions;
        this.totalSpent = calculateTotalSpent();
    }

    double calculateTotalSpent() {
        double total = 0;
        for(int i = 0; i < transactions.size(); i++){
            total += transactions.get(i).amount;
        }
        return total;
    }

    public String getSpendingProgress() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(totalSpent) + " of " + formatter.format(goal);
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return name + " (" + formatter.format(totalSpent) + " of " + formatter.format(goal) + " spent)";
    }

    void refresh() {
        this.totalSpent = calculateTotalSpent();
    }
}