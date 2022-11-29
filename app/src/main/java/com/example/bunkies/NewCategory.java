package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewCategory extends AppCompatActivity {

    TextView nameInput;
    TextView goalInput;
    TextView descInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        int index = getIntent().getIntExtra("pos", 0);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameInput = findViewById(R.id.newCategoryName);
        descInput = findViewById(R.id.newCategoryDescription);
        goalInput = findViewById(R.id.goalInput);

        final Button addButton = findViewById(R.id.addCategory);
        addButton.setOnClickListener(v -> {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());

            String newName = nameInput.getText().toString();
            double newGoal = Double.parseDouble(goalInput.getText().toString());
            String newDesc = descInput.getText().toString();
            List<Transaction> newTransactions = new ArrayList<Transaction>();
            BudgetCategory newCategory = new BudgetCategory(newGoal, newName, newDesc, newTransactions);
            budgets.get(index).categories.add(newCategory);

            MainActivity.saveBudgets(budgets, getApplicationContext());

            finish();
        });

        final Button cancelButton = findViewById(R.id.cancelCategory);
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