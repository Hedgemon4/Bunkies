package com.example.bunkies.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bunkies.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private messageListApdater mMessageAdapter;
    List<String> messageList;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        TextView chatName = findViewById(R.id.textView_Gchannel);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("chatName");
        chatName.setText(name);
        FloatingActionButton editButton = findViewById(R.id.editButton);
        Button send = findViewById(R.id.button_gchat_send);
        send.setOnClickListener(this::addMessage);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editChat = new Intent(MessageListActivity.this, editChatActivity.class);
                startActivity(editChat);
            }
        });
        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        messageList = messageCreation();
        mMessageAdapter = new messageListApdater(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }
    public List messageCreation(){

        List<String> messageList = Arrays.asList("Hey","Morning", "Could you do some dishes today?", "lol no");
        return messageList;
    }
    public List addMessage(View view){
        EditText newMessage = findViewById(R.id.edit_gchat_message);
        if(newMessage == null){
            Toast.makeText(this, "No message to send", Toast.LENGTH_SHORT).show();
            return messageList;
        }
        messageList.add(newMessage.getText().toString());
        return messageList;

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}