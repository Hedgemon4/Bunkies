package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddListItem extends AppCompatActivity {
    private EditText editTaskName;
    private EditText editTaskDescription;
    private EditText editTaskRoommates;
    public static ArrayList<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String taskName = bundle.getString("taskName");
        String taskDescription = bundle.getString("taskDescription");
        String [] taskPeople = bundle.getStringArray("taskPeople");
        listItems = (ArrayList<ListItem>) bundle.getSerializable("listItems");

        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTextTaskDescription);
        editTaskRoommates = findViewById(R.id.editTextAddRommie);

        if (!taskName.equals(""))
            editTaskName.setText(taskName);
        if(!taskDescription.equals(""))
            editTaskDescription.setText(taskDescription);
    }

    public void onCancelClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("listItems", listItems);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void onAddClick(View view) {
        String taskName = editTaskName.getText().toString();
        String taskDescription = editTaskDescription.getText().toString();
        String [] taskPeople = {};
        if (!taskName.equals("")) {
            listItems.add(new ListItem(taskName, taskDescription, taskPeople, false));
            Intent intent = new Intent(this, ListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("listItems", listItems);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "You must enter text to add a list item.", Toast.LENGTH_SHORT).show();
        }
    }
}
