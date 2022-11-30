package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    }

    @Override
    public void onTextClick(View view, int position) {
        Toast.makeText(this, "Clicked.", Toast.LENGTH_SHORT).show();
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
            bundle.putString("listName", bunkiesLists.get(position).getListName());
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onNewListClick(View view) {
        Toast.makeText(this, "Clicked.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NewListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bunkiesLists", bunkiesLists);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
