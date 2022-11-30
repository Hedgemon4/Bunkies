package com.example.bunkies.messaging;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bunkies.R;

public class newChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}