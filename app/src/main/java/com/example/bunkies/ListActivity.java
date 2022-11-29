package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListClickListener {

    public static ArrayList<ListItem> listItems;
    private RecyclerView recyclerView;
    private EditText addTask;
    private ListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle bundle = getIntent().getExtras();
        listItems = (ArrayList<ListItem>) bundle.getSerializable("listItems");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listItemAdapter = new ListItemAdapter(listItems);
        recyclerView.setAdapter(listItemAdapter);

        listItemAdapter.setClickListener(this);

        // Initialize EditText for adding tasks
        addTask = findViewById(R.id.enterListItemTextView);

        addTask.setOnEditorActionListener((task, index, event) -> {
            if (index == 6 || event.getAction() == 0) {
                addListItem(addTask.getText().toString());
                addTask.setText("");
            }
            return true;
        });
    }

    @Override
    public void onCheckClick(View view, int position) {
        boolean b = listItems.get(position).isDone();
        listItems.get(position).setDone(!b);
        System.out.printf("Clicked");
    }

    public void addListItem(String itemTitle) {
        if (!itemTitle.equals("")) {
            listItems.add(new ListItem(itemTitle));
            listItemAdapter.notifyItemInserted(listItemAdapter.getItemCount());
        } else {
            Toast.makeText(this, "You must enter text to add a list item.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onAddListItemClick(View view){
        Intent intent = new Intent(this, AddListItem.class);
        Bundle bundle = new Bundle();
        bundle.putString("taskName", addTask.getText().toString());
        bundle.putString("taskDescription", "");
        bundle.putStringArray("taskPeople", new String[]{});
        bundle.putSerializable("listItems", listItems);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
