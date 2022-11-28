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

    Budget[] budgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_budgets);

        ArrayAdapter<Budget> adapter = new ArrayAdapter<Budget>(this,
                android.R.layout.simple_list_item_1, MainActivity.loadBudgets(getApplicationContext()));
        ListView listView = findViewById(R.id.listBudgetsView);
        listView.setAdapter(adapter);

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
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
// TODO Auto-generated method stub
        super.onRestart();

        ArrayAdapter<Budget> adapter = new ArrayAdapter<Budget>(this,
                android.R.layout.simple_list_item_1, MainActivity.loadBudgets(getApplicationContext()));
        ListView listView = findViewById(R.id.listBudgetsView);
        listView.setAdapter(adapter);

        // Create a message handling object as an anonymous class.
        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Do something in response to the click
            }
        };
        listView.setOnItemClickListener(messageClickedHandler);
        //Do your code here
    }
}