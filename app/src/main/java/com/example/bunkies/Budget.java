package com.example.bunkies;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Budget implements Serializable {
    double goal;
    double totalSpent;
    String name;
    String description;
    List<BudgetCategory> categories;
    List<Roomie> roomies;

    public Budget(String name, String description, List<BudgetCategory> categories, List<Roomie> roomies) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.roomies = roomies;

//        this.goal = goal;
//        this.totalSpent = totalSpent;
    }

    public Budget(String name, String description, List<BudgetCategory> categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.roomies = new ArrayList<Roomie>();

//        this.goal = goal;
//        this.totalSpent = totalSpent;
    }

    public Budget(String name, String description) {
        this.name = name;
        this.description = description;
        this.categories = new ArrayList<BudgetCategory>();
        this.roomies = new ArrayList<Roomie>();

//        this.goal = goal;
//        this.totalSpent = totalSpent;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
