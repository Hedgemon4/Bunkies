package com.example.bunkies;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV;

    private LocalTime time;

    private EditText hours;
    private EditText minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));

        hours = findViewById(R.id.editEventHours);
        minutes = findViewById(R.id.editEventMinutes);

        getSupportActionBar().setTitle("Calendar");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF3700B3")));

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }

    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
    }

    public void saveEventAction(View view) {
        if (!eventNameET.getText().toString().equals("")) {
            Pattern hoursPattern = Pattern.compile("([0-1]?[0-9])|([2][0-3])");
            Matcher hoursMatcher = hoursPattern.matcher(this.hours.getText().toString());
            Pattern minutesPattern = Pattern.compile("([0-5]?[0-9])");
            Matcher minutesMatcher = minutesPattern.matcher(this.minutes.getText().toString());
            if (hoursMatcher.matches()) {
                int hours = Integer.parseInt(this.hours.getText().toString());
                if (minutesMatcher.matches()) {
                    int minutes = Integer.parseInt(this.minutes.getText().toString());
                    time = LocalTime.of(hours, minutes);
                    String eventName = eventNameET.getText().toString();
                    Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
                    Event.eventsList.add(newEvent);
                    finish();
                } else {
                    Toast.makeText(this, "You must enter a valid number of minutes (0 to 59)", Toast.LENGTH_SHORT).show();
                    this.minutes.setError("You must enter a valid number of minutes (0 to 59)");
                }
            } else {
                Toast.makeText(this, "You must enter a valid number of hours (0 to 23)", Toast.LENGTH_SHORT).show();
                this.hours.setError("You must enter a valid number of hours (0 to 23)");
            }
        } else {
            Toast.makeText(this, "You must enter a name for your event.", Toast.LENGTH_SHORT).show();
            eventNameET.setError("Your must enter a name for your event.");
        }
    }

    public void onCancelClick(View view) {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
