package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ViewListsActivity extends AppCompatActivity implements BunkiesListClickListener {
    public static ArrayList<BunkiesList> bunkiesLists;
    private RecyclerView recyclerView;
    private BunkiesListAdapter bunkiesListAdapter;

    // TODO: Make list overview clear and add another page title
    // TODO: Add way to view who is in a budget
    // TODO: Fix button and text size

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lists);
        String filename = "bunkiesLists.txt";
        bunkiesLists = new ArrayList<>();
        try {
            File file = new File(this.getFilesDir(), "bunkiesLists.txt");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fis);
                bunkiesLists = (ArrayList<BunkiesList>) in.readObject();
                System.out.println("Loaded file");
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

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Lists");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e29f1d")));
    }

    @Override
    public void onTextClick(View view, int position) {
        try {
            File file = new File(this.getFilesDir(), bunkiesLists.get(position).getListFile());
            ArrayList<ListItem> listItems = new ArrayList<>();
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fis);
                listItems = (ArrayList<ListItem>) in.readObject();
            } else {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                out.writeObject(listItems);
                out.close();
                fileOutputStream.close();
            }
            Intent intent = new Intent(this, ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("listItems", listItems);
            bundle.putSerializable("bunkiesList", bunkiesLists.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onNewListClick(View view) {
        Intent intent = new Intent(this, NewListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bunkiesLists", bunkiesLists);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
