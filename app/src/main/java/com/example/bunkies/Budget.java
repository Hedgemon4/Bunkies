package com.example.bunkies;

import java.io.Serializable;
import java.util.Arrays;

public class Budget implements Serializable {
    double goal;
    double totalSpent;
    String name;
    String description;
    BudgetCategory[] categories;

    public Budget(String name, String description, BudgetCategory[] categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;

//        this.goal = goal;
//        this.totalSpent = totalSpent;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
