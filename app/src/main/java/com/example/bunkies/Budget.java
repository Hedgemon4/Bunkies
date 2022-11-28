package com.example.bunkies;

import java.io.Serializable;
import java.text.NumberFormat;
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

        this.goal = calculateTotalGoal();
        this.totalSpent = calculateTotalSpent();
    }

    public Budget(String name, String description, List<BudgetCategory> categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.roomies = new ArrayList<Roomie>();

        this.goal = calculateTotalGoal();
        this.totalSpent = calculateTotalSpent();
    }

    public Budget(String name, String description) {
        this.name = name;
        this.description = description;
        this.categories = new ArrayList<BudgetCategory>();
        this.roomies = new ArrayList<Roomie>();

        this.goal = calculateTotalGoal();
        this.totalSpent = calculateTotalSpent();
    }

    double calculateTotalSpent() {
        double total = 0;
        for(int i = 0; i < categories.size(); i++){
            total += categories.get(i).totalSpent;
        }
        return total;
    }

    double calculateTotalGoal() {
        double total = 0;
        for(int i = 0; i < categories.size(); i++){
            total += categories.get(i).goal;
        }
        return total;
    }

    public String getSpendingProgress(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(totalSpent) + " of " + formatter.format(goal);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
