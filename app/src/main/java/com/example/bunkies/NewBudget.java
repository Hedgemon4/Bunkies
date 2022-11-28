package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewBudget extends AppCompatActivity {

    TextView nameInput;
    TextView descInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        nameInput = findViewById(R.id.newBudgetName);
        descInput = findViewById(R.id.newBudgetDescription);

        final Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());

            String newName = nameInput.getText().toString();
            String newDesc = descInput.getText().toString();
            List<BudgetCategory> newCategories = new ArrayList<BudgetCategory>();
            List<Roomie> newRoomies = new ArrayList<Roomie>();
            Budget newBudget = new Budget(newName, newDesc, newCategories, newRoomies);
            budgets.add(newBudget);

            MainActivity.saveBudgets(budgets, getApplicationContext());

            finish();
        });

        final Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}