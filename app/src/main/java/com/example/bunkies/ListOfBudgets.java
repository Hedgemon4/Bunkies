package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListOfBudgets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_budgets);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        ArrayAdapter<Budget> adapter = new ArrayAdapter<Budget>(this,
                android.R.layout.simple_list_item_1, MainActivity.loadBudgets(getApplicationContext()));
        ListView listView = findViewById(R.id.listBudgetsView);
        listView.setAdapter(adapter);

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BudgetView.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(messageClickedHandler);

        final Button button = findViewById(R.id.newBudgetButton);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewBudget.class);
            startActivity(intent);
        });
    }

    protected void onRestart() {
        super.onRestart();

        ArrayAdapter<Budget> adapter = new ArrayAdapter<Budget>(this,
                android.R.layout.simple_list_item_1, MainActivity.loadBudgets(getApplicationContext()));
        ListView listView = findViewById(R.id.listBudgetsView);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}