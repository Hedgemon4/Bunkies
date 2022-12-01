package com.example.bunkies.messaging;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bunkies.R;

public class newChatActivity extends AppCompatActivity {
    EditText chatName;
    private CheckBox ChadBox;
    private CheckBox EmilyBox;
    private CheckBox GuntBox;
    private CheckBox TheoBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show back button

        getSupportActionBar().setTitle("New Chat");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable((Color.parseColor("#FFFF7036"))));

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this::cancelClick);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this::saveClick);

        ChadBox = findViewById(R.id.ChadCheck);
        EmilyBox = findViewById(R.id.EmilyCheck);
        GuntBox = findViewById(R.id.guntCheck);
        TheoBox = findViewById(R.id.TheoCheck);

        chatName = findViewById(R.id.chatName);
    }

    public void cancelClick(View v){
        finish();
    }

    public void saveClick(View v){
        String name = chatName.getText().toString();
        if(name.length()<=0){
            Toast.makeText(this,"Please fill in a name",Toast.LENGTH_SHORT).show();
            return;
        }else if(ChadBox.isChecked() || EmilyBox.isChecked() || GuntBox.isChecked() || TheoBox.isChecked()){
            Intent intent = new Intent();
            intent.putExtra("chatName", name);
            setResult(Activity.RESULT_OK,intent);
            finish();

        }
        Toast.makeText(this, "Please select a person to add to the chat", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}