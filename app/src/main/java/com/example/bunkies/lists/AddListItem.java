package com.example.bunkies.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bunkies.R;

import java.util.ArrayList;

public class AddListItem extends AppCompatActivity implements PersonClickListener {
    private EditText editTaskName;
    private EditText editTaskDescription;
    private RecyclerView recyclerView;
    private PersonItemAdapter personItemAdapter;
    public static ArrayList<ListItem> listItems;
    private boolean newItem;
    private int index;
    private BunkiesList bunkiesList;
    public static ArrayList<PersonItem> listPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);

        assert getSupportActionBar() != null;   //null check

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

        personItemAdapter = new PersonItemAdapter(listPeople);
        recyclerView.setAdapter(personItemAdapter);

        personItemAdapter.setClickListener(this);

        String[] names = bunkiesList.getPeople();
        for (int i = 0; i < names.length; i++)
            listPeople.add(new PersonItem(names[i], people[i]));

        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTextTaskDescription);

        Button button = findViewById(R.id.addListItemAdd);

        if (!newItem) {
            button.setText("Save");
            getSupportActionBar().setTitle(taskName);
        } else
            getSupportActionBar().setTitle("New Task");

        if (!taskName.equals(""))
            editTaskName.setText(taskName);
        if (!taskDescription.equals(""))
            editTaskDescription.setText(taskDescription);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e29f1d")));
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
            people[i] = listPeople.get(i).isOnTask();

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
            Toast.makeText(this, "You must enter a task name to add a list item.", Toast.LENGTH_SHORT).show();
            editTaskName.setError("Please enter a name for your list item.");
        }
    }

    @Override
    public void onCheckClick(View view, int position) {
        boolean b = listPeople.get(position).isOnTask();
        listPeople.get(position).setOnTask(!b);
        personItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onCancelClick(null);
        return true;
    }
}
