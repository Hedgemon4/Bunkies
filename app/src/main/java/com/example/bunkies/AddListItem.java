package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddListItem extends AppCompatActivity {
    private EditText editTaskName;
    private EditText editTaskDescription;
    private CheckBox chadCheckBox;
    private CheckBox emilyCheckBox;
    private CheckBox gunterCheckBox;
    private CheckBox theoCheckbox;
    public static ArrayList<ListItem> listItems;
    private boolean newItem;
    private int index;

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

        editTaskName = findViewById(R.id.editTaskName);
        editTaskDescription = findViewById(R.id.editTextTaskDescription);
        chadCheckBox = findViewById(R.id.addListItemChadCheckbox);
        chadCheckBox.setChecked(people[0]);
        emilyCheckBox = findViewById(R.id.addListItemEmilyCheckbox);
        emilyCheckBox.setChecked(people[1]);
        gunterCheckBox = findViewById(R.id.addListItemGünterCheckbox);
        gunterCheckBox.setChecked(people[2]);
        theoCheckbox = findViewById(R.id.addListItemTheoCheckbox);
        theoCheckbox.setChecked(people[3]);

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
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void onAddClick(View view) {
        String taskName = editTaskName.getText().toString();
        String taskDescription = editTaskDescription.getText().toString();
        boolean[] people = {chadCheckBox.isChecked(), emilyCheckBox.isChecked(), gunterCheckBox.isChecked(),
                theoCheckbox.isChecked()};
        if (!taskName.equals("")) {
            if (newItem)
                listItems.add(new ListItem(taskName, taskDescription, people, false));
            else
                listItems.set(index, new ListItem(taskName, taskDescription, people, false));
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
