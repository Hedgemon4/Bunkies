package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddListItem extends AppCompatActivity implements ListClickListener{
    private EditText editTaskName;
    private EditText editTaskDescription;
    private RecyclerView recyclerView;
    private ListItemAdapter listItemAdapter;
    public static ArrayList<ListItem> listItems;
    private boolean newItem;
    private int index;
    private BunkiesList bunkiesList;
    public static ArrayList<ListItem> listPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String taskName = bundle.getString("taskName");
        String taskDescription = bundle.getString("taskDescription");
        listItems = (ArrayList<ListItem>) bundle.getSerializable("listItems");
        newItem = bundle.getBoolean("newItem");
        boolean[] people = bundle.getBooleanArray("people");
        index = bundle.getInt("index");
        bunkiesList = (BunkiesList) bundle.getSerializable("bunkiesList");
        listPeople = new ArrayList<>();

        recyclerView = findViewById(R.id.addListPeopleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listItemAdapter = new ListItemAdapter(listPeople);
        recyclerView.setAdapter(listItemAdapter);

        listItemAdapter.setClickListener(this);

        String[] names = bunkiesList.getPeople();
        for (int i = 0; i < names.length; i++)
            listPeople.add(new ListItem(names[i], people[i]));

        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTextTaskDescription);

        Button button = findViewById(R.id.addListItemAdd);
        if (!newItem)
            button.setText("Save");

        if (!taskName.equals(""))
            editTaskName.setText(taskName);
        if (!taskDescription.equals(""))
            editTaskDescription.setText(taskDescription);
    }

    public void onCancelClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("listItems", listItems);
        bundle.putSerializable("bunkiesList", bunkiesList);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void onAddClick(View view) {
        String taskName = editTaskName.getText().toString();
        String taskDescription = editTaskDescription.getText().toString();
        boolean[] people = new boolean[bunkiesList.getPeople().length];
        for (int i = 0; i < people.length; i++)
            people[i] = listPeople.get(i).isDone();

        if (!taskName.equals("")) {
            if (newItem)
                listItems.add(new ListItem(taskName, taskDescription, people, false));
            else
                listItems.set(index, new ListItem(taskName, taskDescription, people, false));
            Intent intent = new Intent(this, ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("listItems", listItems);
            bundle.putSerializable("bunkiesList", bunkiesList);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "You must enter text to add a list item.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckClick(View view, int position) {
        boolean b = listPeople.get(position).isDone();
        listPeople.get(position).setDone(!b);
        listItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onTextClick(View view, int position) {
        boolean b = listPeople.get(position).isDone();
        listPeople.get(position).setDone(!b);
        listItemAdapter.notifyDataSetChanged();
    }
}
