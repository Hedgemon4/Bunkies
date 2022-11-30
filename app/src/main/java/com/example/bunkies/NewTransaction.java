package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewTransaction extends AppCompatActivity {

    TextView nameInput;
    TextView amountInput;
    TextView dateInput;
    int editIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        editIndex = getIntent().getIntExtra("editIndex", -1);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        int budgetIndex = getIntent().getIntExtra("budgetIndex", 0);
        int categoryIndex = getIntent().getIntExtra("categoryIndex", 0);

        nameInput = findViewById(R.id.newTransactionName);
        amountInput = findViewById(R.id.amountInput);
        dateInput = findViewById(R.id.newTransactionDate);

        final Button addButton = findViewById(R.id.addTransaction);
        addButton.setOnClickListener(v -> {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());

            String newName = nameInput.getText().toString();
            double newAmount = Double.parseDouble(amountInput.getText().toString());
            String newDate = dateInput.getText().toString();
            Transaction newTransaction = new Transaction(newName, newAmount, newDate);

            if (editIndex == -1) {
                budgets.get(budgetIndex).categories.get(categoryIndex).transactions.add(newTransaction);
            } else {
                budgets.get(budgetIndex).categories.get(categoryIndex).transactions.set(editIndex, newTransaction);
            }

            MainActivity.saveBudgets(budgets, getApplicationContext());

            finish();
        });

        final Button cancelButton = findViewById(R.id.cancelTransaction);
        cancelButton.setOnClickListener(v -> {
            finish();
        });

        if (editIndex != -1) {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());
            Budget editBudget = budgets.get(budgetIndex);
            BudgetCategory editCategory = editBudget.categories.get(categoryIndex);
            Transaction editTransaction = editCategory.transactions.get(editIndex);
            nameInput.setText(editTransaction.name);
            amountInput.setText(editTransaction.amount + "");
            dateInput.setText(editTransaction.date);
            addButton.setText("Save");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}