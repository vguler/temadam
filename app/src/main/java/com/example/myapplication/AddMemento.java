package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;
import static com.example.myapplication.R.id.bottom_memento;
import static com.example.myapplication.R.id.bottom_settings;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import java.util.Map;


public class AddMemento extends AppCompatActivity {
    float x1,x2,y1,y2;

    private CalendarView cvMemento;

    private TextView tvEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memento);

        cvMemento = findViewById(R.id.guler_vlad_mem_cv);

        cvMemento.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mementoDialog(year, month, dayOfMonth);
            }
        });

        switchActivities();
    }

    private void mementoDialog(int year, int month, int dayOfMonth) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add your future event with a memento");
        View viewWithDialog = getLayoutInflater().inflate(R.layout.dialog_add_memento, null);
        TextInputEditText nameMemento = viewWithDialog.findViewById(R.id.guler_vlad_name_til);
        TextInputEditText descriptionMemento = viewWithDialog.findViewById(R.id.guler_vlad_description_til);
        TimePicker timeMemento = viewWithDialog.findViewById(R.id.guler_vlad_mem_hour_tp);
        builder.setView(viewWithDialog);
        builder.setPositiveButton("Save event", (dialog, which) -> {
            String mementoName = nameMemento.getText().toString();
            String mementoDescription = descriptionMemento.getText().toString();
            int mementoHour = timeMemento.getHour();
            int mementoMinutes = timeMemento.getMinute();
            if (isValid(year, month, dayOfMonth, mementoHour, mementoMinutes, mementoName, mementoDescription)) {
                saveUserPreferences(year, month, dayOfMonth, mementoHour, mementoMinutes, mementoName, mementoDescription);
                updateCalendar(year, month, dayOfMonth);
            }
        });

        builder.setNegativeButton("Cancel event", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void saveUserPreferences(int year, int month, int dayOfMonth, int hour, int minute, String mementoName, String mementoDescription) {
        SharedPreferences sharedPreferences = getSharedPreferences("mementos", MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        String mementoKey = "memento" + year + (month + 1) + dayOfMonth + hour + minute + "_" + System.currentTimeMillis();
        preferencesEditor.putString(mementoKey + "name", mementoName);
        preferencesEditor.putString(mementoKey + "description", mementoDescription);
        preferencesEditor.apply();
    }

    private void updateCalendar(int year, int month, int dayOfMonth) {
        Set<String> eventDays = getEventDays();
        String eventsText = "Your programmed events:\n";
        for (String day : eventDays) {
            eventsText += day + "\n";
        }

        TextView eventsTextView = findViewById(R.id.guler_vlad_tv_events);
        eventsTextView.setText(eventsText);
    }

    private Set<String> getEventDays() {
        SharedPreferences sharedPreferences = getSharedPreferences("mementos", MODE_PRIVATE);
        Set<String> eventDays = new HashSet<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("memento")) {
                int startIndex = "memento".length();
                int endIndex = startIndex + 12;
                String dateString = key.substring(startIndex, endIndex);

                try {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault());
                    Date date = inputFormat.parse(dateString);
                    String formattedDateString = outputFormat.format(date);
                    String eventName = sharedPreferences.getString(key.replace("name", "") + "name", "");
                    String eventDescription = sharedPreferences.getString(key.replace("description", "") + "description", "");
                    String eventText = "- " + formattedDateString + " - " + eventName + "- " + eventDescription;
                    eventDays.add(eventText);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        return eventDays;
    }

    private boolean isValid(int year, int month, int dayOfMonth, int hour, int minute, String mementoName, String mementoDescription) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            Toast.makeText(getApplicationContext(), "The event should happen in the future or at least today!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mementoName == null || mementoName.trim().length() < 3) {
            Toast.makeText(getApplicationContext(), "Invalid name! At least 3 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mementoDescription == null || mementoDescription.trim().length() < 8) {
            Toast.makeText(getApplicationContext(), "Invalid description! At least 8 chracters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void switchActivities(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_memento);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == bottom_home){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            } else if(itemId == R.id.bottom_activities){
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
                finish();
            }else if(itemId == R.id.bottom_memento){
                return true;
            }else if(itemId == R.id.bottom_settings){
                startActivity(new Intent(getApplicationContext(),AddSettings.class));
                finish();
            }else if(itemId == R.id.bottom_timer) {
                startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                finish();
            }
            return false;
        });
    }



    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if(x1 < x2){
                    Intent i = new Intent(AddMemento.this, AddActivity.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(AddMemento.this, TimerActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}