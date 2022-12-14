package com.example.bunkies.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bunkies.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListClickListener {

    public static ArrayList<ListItem> listItems;
    private RecyclerView recyclerView;
    private EditText addTask;
    private ListItemAdapter listItemAdapter;
    private BunkiesList bunkiesList;
    private TextView listName;
    private TextView listMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle bundle = getIntent().getExtras();
        listItems = (ArrayList<ListItem>) bundle.getSerializable("listItems");
        bunkiesList = (BunkiesList) bundle.getSerializable("bunkiesList");
        saveList();

        recyclerView = findViewById(R.id.listItemRecyclerView);
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

        listName = findViewById(R.id.viewListListName);
        listName.setText(bunkiesList.getListName());

        String s = "Members: ";
        String m = String.join(", ", bunkiesList.getPeople());
        s += m;

        listMembers = findViewById(R.id.listMembersTextView);
        listMembers.setText(s);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Lists");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e29f1d")));
    }

    @Override
    public void onCheckClick(View view, int position) {
        boolean b = listItems.get(position).isDone();
        listItems.get(position).setDone(!b);
        recyclerView.post(() -> listItemAdapter.notifyItemChanged(position));
        saveList();
    }

    @Override
    public void onTextClick(View view, int position) {
        Intent intent = new Intent(this, AddListItem.class);
        Bundle bundle = new Bundle();
        bundle.putString("taskName", listItems.get(position).getText());
        bundle.putString("taskDescription", listItems.get(position).getDescription());
        bundle.putBooleanArray("people", listItems.get(position).getPeople());
        bundle.putSerializable("listItems", listItems);
        bundle.putBoolean("newItem", false);
        bundle.putInt("index", position);
        bundle.putSerializable("bunkiesList", bunkiesList);
        intent.putExtras(bundle);
        startActivity(intent);
        saveList();
        finish();
    }

    public void addListItem(String itemTitle) {
        if (!itemTitle.equals("")) {
            boolean[] b = new boolean[bunkiesList.getPeople().length];
            listItems.add(new ListItem(itemTitle, "", b, false));
            listItemAdapter.notifyItemInserted(listItemAdapter.getItemCount());
            saveList();
        } else {
            Toast.makeText(this, "You must enter a task name to add a list item.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onAddListItemClick(View view) {
        Intent intent = new Intent(this, AddListItem.class);
        Bundle bundle = new Bundle();
        bundle.putString("taskName", addTask.getText().toString());
        bundle.putString("taskDescription", "");
        bundle.putBooleanArray("people", new boolean[bunkiesList.getPeople().length]);
        bundle.putSerializable("listItems", listItems);
        bundle.putBoolean("newItem", true);
        bundle.putSerializable("bunkiesList", bunkiesList);
        intent.putExtras(bundle);
        startActivity(intent);
        saveList();
        finish();
    }

    public void saveList() {
        try {
            File file = new File(this.getFilesDir(), bunkiesList.getListFile());
            file.delete();
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(listItems);
            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        saveList();
        finish();
        return true;
    }
}
