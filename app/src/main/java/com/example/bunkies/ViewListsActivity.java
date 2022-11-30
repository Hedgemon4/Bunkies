package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ViewListsActivity extends AppCompatActivity implements BunkiesListClickListener{
    public static ArrayList<BunkiesList> bunkiesLists;
    private RecyclerView recyclerView;
    private BunkiesListAdapter bunkiesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);
        String filename = "bunkiesLists.txt";
        try {
            FileInputStream fis = openFileInput(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            if(in.available() != 0)
                bunkiesLists = (ArrayList<BunkiesList>) in.readObject();
            else{
                bunkiesLists = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.viewListsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        bunkiesListAdapter = new BunkiesListAdapter(bunkiesLists);
        recyclerView.setAdapter(bunkiesListAdapter);

        bunkiesListAdapter.setClickListener(this);
    }

    @Override
    public void onTextClick(View view, int position) {
        Toast.makeText(this, "Clicked.", Toast.LENGTH_SHORT).show();
    }

    public void onNewListClick(View view){
        Toast.makeText(this, "Clicked.", Toast.LENGTH_SHORT).show();
    }


}