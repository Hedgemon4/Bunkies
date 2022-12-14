package com.example.bunkies;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;


import com.example.bunkies.lists.ViewListsActivity;
import com.example.bunkies.messaging.MessagingMainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBudget();
    }

    void initializeBudget(){
        Transaction t1 = new Transaction("Costco", 39.00, LocalDate.now().toString());
        Transaction t2 = new Transaction("Walmart", 100.45, LocalDate.now().toString());
        Transaction t3 = new Transaction("Girl Scout Cookies", 12.67, LocalDate.now().toString());
        Transaction t4 = new Transaction("Superstore", 17.99, LocalDate.now().toString());
        Transaction t5 = new Transaction("Freshco", 45.00, LocalDate.now().toString());

        List<Transaction> t = new ArrayList<Transaction>();
        t.add(t1);
        t.add(t2);
        t.add(t3);
        t.add(t4);
        t.add(t5);

        BudgetCategory c1 = new BudgetCategory(500.00, "Groceries", "This is to buy groceries with.", t);
        BudgetCategory c2 = new BudgetCategory(1225.50, "Rent", "This is to pay rent with.", t);
        BudgetCategory c3 = new BudgetCategory(30.25, "Transit", "For bussing and taxis.", t);

        List<BudgetCategory> c = new ArrayList<BudgetCategory>();
        c.add(c1);
        c.add(c2);
        c.add(c3);

        Budget b1 = new Budget("Personal", "This budget is for me only.", c);
        Budget b2 = new Budget("Household", "This budget is for the whole house.", c);
        b2.roomies = new RoomieValues();
        b2.roomies.theophania = true;

        List<Budget> budgets = new ArrayList<Budget>();
        budgets.add(b1);
        budgets.add(b2);

        MainActivity.saveBudgets(budgets, getApplicationContext());

        final Button budgetButton = findViewById(R.id.budget);
        budgetButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ListOfBudgets.class);
            startActivity(intent);
        });
    }

    public static void saveBudgets(List<Budget> budgets, Context activity) {
        budgets = Budget.refreshAll(budgets);
        String fileName = "budgets.dat";
        try {
            File file = new File(activity.getFilesDir(), fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(budgets);
            stream.flush();
            stream.close();
        }
        catch (IOException e){
            Log.e("MyApp","IO Exception: " + e);
        }

    }

    public static ArrayList<Budget> loadBudgets(Context activity) {
        String fileName = "budgets.dat";
        ArrayList<Budget> fetchedBudgets = new ArrayList<Budget>();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(activity.openFileInput(fileName));
            fetchedBudgets  = (ArrayList<Budget>) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            Log.e("MyApp" , "File Not Found: " + e);
        }
        return fetchedBudgets;
    }

    public void CalendarButton(View view){
        Intent intent = new Intent(this, CalendarMainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void onListClick(View view) {
        Intent intent = new Intent(this, ViewListsActivity.class);
        startActivity(intent);
    }

    public void messageClick(View v){
        Intent messaging = new Intent(MainActivity.this, MessagingMainActivity.class);
        startActivity(messaging);

    }
}
