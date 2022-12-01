package com.example.bunkies.messaging;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView messageRecyclerView;
    private messageListApdater messageAdapter;
    List<String> messageList;
    TextView chatName;
    int counter;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        chatName = findViewById(R.id.textView_Gchannel);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("chatName");

        getSupportActionBar().setTitle(name);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable((Color.parseColor("#FFFF7036"))));
        chatName.setText(name);
        FloatingActionButton editButton = findViewById(R.id.editButton);
        Button send = findViewById(R.id.button_gchat_send);
        send.setOnClickListener(this::addMessage);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editChat = new Intent(MessageListActivity.this, editChatActivity.class);
                editChat.putExtra("ChatName",name);
                startActivityForResult(editChat,1);
            }
        });
        messageRecyclerView = findViewById(R.id.recycler_gchat);
        messageList = messageCreation();
        messageAdapter = new messageListApdater(this, messageList);
        counter = messageList.size();
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageAdapter);
    }



    public List<String> messageCreation(){
        String [] messages = {"Hey","Morning", "Could you do some dishes today?", "lol no"};
        List<String> list = Arrays.asList(messages);
        messageList = new ArrayList<String>(list);
        return messageList;
    }
    public void addMessage(View view){
        EditText newMessage = findViewById(R.id.edit_gchat_message);
        if(newMessage == null){
            Toast.makeText(this, "No message to send", Toast.LENGTH_SHORT).show();
        }
        assert newMessage != null;
        String newMsg = newMessage.getText().toString();
        if(newMsg.length() > 0 ) {
            messageList.add(newMsg);
            //this isnt working whyyyyy
            newMessage.setText("");
            messageRecyclerView.getAdapter().notifyDataSetChanged();

        }
        else{
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case(1):{
                if(resultCode == Activity.RESULT_OK){
                    String newName = data.getStringExtra("chatName");
                    chatName.setText(newName);
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