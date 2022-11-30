 package com.example.bunkies.messaging;

 import android.app.Activity;
 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;

 import androidx.appcompat.app.AppCompatActivity;

 import com.example.bunkies.R;

 public class editChatActivity extends AppCompatActivity {

     EditText chatName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chat);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show back button
        Intent intent = getIntent();
        String name = intent.getExtras().getString("chatName");
        chatName = findViewById(R.id.chatName);
        chatName.setText(name);


        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this::cancelClick);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this::saveClick);

    }
    public void cancelClick(View v){
        finish();
    }

    public void saveClick(View v){
        String name = chatName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("chatName", name);
        setResult(Activity.RESULT_OK,intent);
        finish();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}