package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddListItem extends AppCompatActivity {
    private EditText editTaskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String taskName = bundle.getString("taskName");

        editTaskName = findViewById(R.id.editTaskName);

        if (taskName != "")
            editTaskName.setText(taskName);
    }

    public void onCancelClick(View view) {
        finish();
    }

    public void onAddClick(View view) {

    }
}