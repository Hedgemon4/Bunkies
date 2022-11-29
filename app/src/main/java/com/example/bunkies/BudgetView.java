package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class BudgetView extends AppCompatActivity {

    ArrayList<Budget> budgets;
    Budget budget;

    TextView description;
    TextView progressText;
    TextView title;
    ProgressBar budgetProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_view);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        budgets = MainActivity.loadBudgets(getApplicationContext());

        int index = getIntent().getIntExtra("pos", 0);
        budget = budgets.get(index);

        final Button button = findViewById(R.id.addCategoryButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewCategory.class);
            intent.putExtra("index", index);
            startActivity(intent);
        });

        title = findViewById(R.id.budgetTitle);
        title.setText(budget.name);

        description = findViewById(R.id.budgetDescriptionView);
        description.setText(budget.description);

        ArrayAdapter<BudgetCategory> adapter = new ArrayAdapter<BudgetCategory>(this,
                android.R.layout.simple_list_item_1, budget.categories);
        ListView listView = findViewById(R.id.listCategoriesView);
        listView.setAdapter(adapter);

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

            }
        };
        listView.setOnItemClickListener(messageClickedHandler);

        progressText = findViewById(R.id.progressText);
        progressText.setText(budget.getSpendingProgress() + " spent");

        budgetProgress = findViewById(R.id.budgetProgressBar);
        budgetProgress.setProgress((int) Math.ceil((budget.calculateTotalSpent() / budget.calculateTotalGoal() * 100)));
    }

    protected void onRestart() {
        super.onRestart();
        budgets = MainActivity.loadBudgets(getApplicationContext());

        int index = getIntent().getIntExtra("pos", 0);
        budget = budgets.get(index);
        ArrayAdapter<BudgetCategory> adapter = new ArrayAdapter<BudgetCategory>(this,
                android.R.layout.simple_list_item_1, budget.categories);
        ListView listView = findViewById(R.id.listCategoriesView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
