package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListClickListener {

    public static ArrayList<ListItem> listItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listItems.add(new ListItem("Test", false, new String[]{"Seth"}));
        listItems.add(new ListItem("Test again", false, new String[]{"Teresa"}));
        listItems.add(new ListItem("Test once more", false, new String[]{"Seth", "Liam"}));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ListItemAdapter listItemAdapter = new ListItemAdapter(listItems);
        recyclerView.setAdapter(listItemAdapter);

        listItemAdapter.setClickListener(this);

        // Initalize EditText for adding tasks
        addTask = findViewById(R.id.enterListItemTextView);

        addTask.setOnEditorActionListener((task, index, event) -> {
            if (index == 6 || event.getAction() == 0)
                addListItem(addTask.getText().toString());
            return true;
        });
    }

    @Override
    public void onCheckClick(View view, int position) {
        System.out.printf("Clicked");
    }

    public void addListItem(String itemTitle){
        if(!itemTitle.equals(""))
        listItems.add(new ListItem(itemTitle));
    }
}
