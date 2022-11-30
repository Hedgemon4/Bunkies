package com.example.bunkies;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bunkies.messaging.MessagingMainActivity;

import org.jetbrains.annotations.Nullable;





public final class MainActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button messaging = findViewById(R.id.messages);
        messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent messaging = new Intent(MainActivity.this, MessagingMainActivity.class);
                startActivity(messaging);

            }
        });


    }
}