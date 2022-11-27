package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Transaction t1 = new Transaction("Costco", 39.00, new Date());
        Transaction t2 = new Transaction("Walmart", 100.45, new Date());
        Transaction t3 = new Transaction("Girl Scout Cookies", 12.67, new Date());
        Transaction t4 = new Transaction("Superstore", 17.99, new Date());
        Transaction t5 = new Transaction("Freshco", 45.00, new Date());

        Transaction[] t = {t1, t2, t3, t4, t5};

        BudgetCategory c1 = new BudgetCategory(500.00, "Groceries", "This is to buy groceries with.", t);
        BudgetCategory c2 = new BudgetCategory(1225.50, "Rent", "This is to pay rent with.", t);
        BudgetCategory c3 = new BudgetCategory(30.25, "Transit", "For bussing and taxis.", t);

        BudgetCategory[] c = {c1, c2, c3};

        Budget b1 = new Budget("Personal", "This budget is for me only.", c);
        Budget b2 = new Budget("Household", "This budget is for the whole house.", c);

        Budget[] budgets = new Budget[]{b1, b2};

        MainActivity.saveBudgets(budgets, getApplicationContext());

        final Button button = findViewById(R.id.budget);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), listOfBudgets.class);
            startActivity(intent);
        });
    }

    public static void saveBudgets(Budget[] budgets, Context activity) {
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

    public static Budget[] loadBudgets(Context activity) {
        String fileName = "budgets.dat";
        Budget[] fetchedBudgets = {};

        try {
            ObjectInputStream inputStream = new ObjectInputStream(activity.openFileInput(fileName));
            fetchedBudgets  = (Budget[]) inputStream.readObject();
            inputStream.close();
        }
        catch (Exception e){
            Log.e("MyApp" , "File Not Found: " + e);
        }
        return fetchedBudgets;
    }
}