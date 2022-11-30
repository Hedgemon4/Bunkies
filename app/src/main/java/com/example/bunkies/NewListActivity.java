package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {
    private EditText listName;
    private CheckBox chadCheckbox;
    private CheckBox emilyCheckbox;
    private CheckBox gunterCheckbox;
    private CheckBox theoCheckbox;
    public static ArrayList<BunkiesList> bunkiesLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        chadCheckbox = findViewById(R.id.createListItemChadCheckbox);
        emilyCheckbox = findViewById(R.id.createListItemEmilyCheckbox);
        gunterCheckbox = findViewById(R.id.createListItemGunterCheckbox);
        theoCheckbox = findViewById(R.id.createListItemTheoCheckbox);

        listName = findViewById(R.id.createListNameEditText);

        Bundle bundle = getIntent().getExtras();
        bunkiesLists = (ArrayList<BunkiesList>) bundle.getSerializable("bunkiesLists");
    }

    public void onCancelClick(View view) {
        finish();
    }

    public void onCreateClick(View view) {
        if (listName.getText().toString().equals("")) {
            Toast.makeText(this, "You must fill in the name field to create a new list.", Toast.LENGTH_SHORT).show();
        } else {
            if (chadCheckbox.isChecked() || emilyCheckbox.isChecked() || gunterCheckbox.isChecked() || theoCheckbox.isChecked()) {
                int i = 0;
                if (chadCheckbox.isChecked())
                    i += 1;
                if (emilyCheckbox.isChecked())
                    i += 1;
                if (gunterCheckbox.isChecked())
                    i += 1;
                if (theoCheckbox.isChecked())
                    i += 1;
                String[] people = new String[i];
                int j = 0;
                if (chadCheckbox.isChecked())
                    people[j++] = "Chad";
                if (emilyCheckbox.isChecked())
                    people[j++] = "Emily";
                if (gunterCheckbox.isChecked())
                    people[j++] = "Günter";
                if (theoCheckbox.isChecked())
                    people[j] = "Theophania";
                bunkiesLists.add(new BunkiesList(listName.getText().toString(), people, listName.getText().toString() + ".txt"));
                try {
                    File file = new File(this.getFilesDir(),"bunkiesLists.txt");
                    if(file.exists())
                        file.delete();
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
                    out.writeObject(bunkiesLists);
                    out.close();
                    fileOutputStream.close();
                    System.out.println("Got here");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, ViewListsActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "You must select at least one person to add to the list.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
