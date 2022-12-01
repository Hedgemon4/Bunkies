package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {

    ArrayList<Budget> budgets;
    Budget budget;
    BudgetCategory category;

    TextView description;
    TextView progressText;
    TextView title;
    ProgressBar budgetProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF06B600")));

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        budgets = MainActivity.loadBudgets(getApplicationContext());

        int index = getIntent().getIntExtra("budgetIndex", 0);
        budget = budgets.get(index);
        category = budget.categories.get(getIntent().getIntExtra("categoryIndex", 0));

        title = findViewById(R.id.categoryTitle);
        title.setText(category.name);

        description = findViewById(R.id.categoryDescription);
        description.setText(category.description);

        progressText = findViewById(R.id.categoryProgressText);
        progressText.setText(category.getSpendingProgress() + " spent");

        budgetProgress = findViewById(R.id.categoryProgressBar);
        budgetProgress.setProgress((int) Math.ceil((category.calculateTotalSpent() / category.goal * 100)));

        ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this,
                android.R.layout.simple_list_item_1, category.transactions);
        ListView listView = findViewById(R.id.listTransactionsView);
        listView.setAdapter(adapter);

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewTransaction.class);
                intent.putExtra("budgetIndex", index);
                intent.putExtra("categoryIndex", getIntent().getIntExtra("categoryIndex", 0));
                intent.putExtra("editIndex", position);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(messageClickedHandler);

        final Button button = findViewById(R.id.addTransactionButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewTransaction.class);
            intent.putExtra("budgetIndex", index);
            intent.putExtra("categoryIndex", getIntent().getIntExtra("categoryIndex", 0));
            startActivity(intent);
        });

        final Button editButton = findViewById(R.id.editCategoryButton);
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewCategory.class);
            intent.putExtra("index", index);
            intent.putExtra("editIndex", getIntent().getIntExtra("categoryIndex", 0));
            startActivity(intent);
        });
    }

    protected void onRestart() {
        super.onRestart();

        budgets = MainActivity.loadBudgets(getApplicationContext());

        Budget.refreshAll(budgets);

        int index = getIntent().getIntExtra("budgetIndex", 0);
        budget = budgets.get(index);
        category = budget.categories.get(getIntent().getIntExtra("categoryIndex", 0));

        title = findViewById(R.id.categoryTitle);
        title.setText(category.name);

        description = findViewById(R.id.categoryDescription);
        description.setText(category.description);

        budgetProgress = findViewById(R.id.categoryProgressBar);
        budgetProgress.setProgress((int) Math.ceil((category.calculateTotalSpent() / category.goal * 100)));

        ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this,
                android.R.layout.simple_list_item_1, category.transactions);
        ListView listView = findViewById(R.id.listTransactionsView);
        listView.setAdapter(adapter);

        progressText = findViewById(R.id.categoryProgressText);
        progressText.setText(category.getSpendingProgress() + " spent");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}