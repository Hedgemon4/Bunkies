package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onListClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<ListItem> listItems = new ArrayList<>();
        listItems.add(new ListItem("Test", false, new String[]{"Seth"}));
        listItems.add(new ListItem("Test again", false, new String[]{"Teresa"}));
        listItems.add(new ListItem("Test once more", false, new String[]{"Seth", "Liam"}));
        bundle.putSerializable("listItems", listItems);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
