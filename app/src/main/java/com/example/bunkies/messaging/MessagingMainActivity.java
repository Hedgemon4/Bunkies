package com.example.bunkies.messaging;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bunkies.R;

import java.util.ArrayList;

public class MessagingMainActivity extends AppCompatActivity {
    ArrayList<Contact> contacts;
    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_main);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        getSupportActionBar().setTitle("Messages");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable((Color.parseColor("#FFFF7036"))));

        rvContacts = (RecyclerView) findViewById(R.id.recycler_gchat);
        Button newChat = findViewById(R.id.Addbutton);
        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newChat = new Intent(MessagingMainActivity.this, newChatActivity.class);
                startActivityForResult(newChat, 1);
            }
        });

        contacts = Contact.createContactsList(5);
        contactAdapter adapter = new contactAdapter(contacts);

        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case(1):{
                if(resultCode == Activity.RESULT_OK){
                    String newName = data.getStringExtra("chatName");
                    contacts.add(Contact.addContact(newName));
                    rvContacts.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}