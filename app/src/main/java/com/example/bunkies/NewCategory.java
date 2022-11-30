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

    int editIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);

        int index = getIntent().getIntExtra("pos", 0);
        editIndex = getIntent().getIntExtra("editIndex", -1);

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


            if (editIndex == -1) {
                BudgetCategory newCategory = new BudgetCategory(newGoal, newName, newDesc, newTransactions);
                budgets.get(index).categories.add(newCategory);
            } else {
                newTransactions = budgets.get(index).categories.get(editIndex).transactions;
                BudgetCategory newCategory = new BudgetCategory(newGoal, newName, newDesc, newTransactions);
                budgets.get(index).categories.set(editIndex, newCategory);
            }

            MainActivity.saveBudgets(budgets, getApplicationContext());

            finish();
        });

        final Button cancelButton = findViewById(R.id.cancelCategory);
        cancelButton.setOnClickListener(v -> {
            finish();
        });

        if (editIndex != -1) {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());
            Budget editBudget = budgets.get(index);
            BudgetCategory editCategory = editBudget.categories.get(editIndex);
            nameInput.setText(editCategory.name);
            descInput.setText(editCategory.description);
            goalInput.setText(editCategory.goal + "");
            addButton.setText("Save");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}