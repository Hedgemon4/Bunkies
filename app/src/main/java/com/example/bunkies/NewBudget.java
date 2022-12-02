package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewBudget extends AppCompatActivity {

    CheckBox gunterBox;
    CheckBox chadBox;
    CheckBox theophaniaBox;

    TextView nameInput;
    TextView descInput;
    int editIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF06B600")));

        gunterBox = findViewById(R.id.gunterBox);
        chadBox = findViewById(R.id.chadBox);
        theophaniaBox = findViewById(R.id.theophaniaBox);

        editIndex = getIntent().getIntExtra("editIndex", -1);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        nameInput = findViewById(R.id.newBudgetName);
        descInput = findViewById(R.id.newBudgetDescription);

        final Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            if (!nameInput.getText().toString().equals("")) {
                ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());

                String newName = nameInput.getText().toString();
                String newDesc = descInput.getText().toString();
                List<BudgetCategory> newCategories = new ArrayList<BudgetCategory>();
                RoomieValues newRoomies = new RoomieValues();

                newRoomies.gunter = gunterBox.isChecked();
                newRoomies.chad = chadBox.isChecked();
                newRoomies.theophania = theophaniaBox.isChecked();

                if (editIndex == -1) {
                    Budget newBudget = new Budget(newName, newDesc, newCategories, newRoomies);
                    budgets.add(newBudget);
                } else {
                    newCategories = budgets.get(editIndex).categories;
                    Budget newBudget = new Budget(newName, newDesc, newCategories, newRoomies);
                    budgets.set(editIndex, newBudget);
                }
                MainActivity.saveBudgets(budgets, getApplicationContext());

                finish();

            } else {
                Toast.makeText(this, "You must enter a name for your budget.", Toast.LENGTH_SHORT).show();
                nameInput.setError("Please enter a name for your budget.");
            }
        });


        final Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> {
            finish();
        });

        if (editIndex != -1) {
            ArrayList<Budget> budgets = MainActivity.loadBudgets(getApplicationContext());
            Budget editBudget = budgets.get(editIndex);
            nameInput.setText(editBudget.name);
            descInput.setText(editBudget.description);
            addButton.setText("Save");

            if (editBudget.roomies.gunter) gunterBox.setChecked(true);
            if (editBudget.roomies.chad) chadBox.setChecked(true);
            if (editBudget.roomies.theophania) theophaniaBox.setChecked(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}