package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;
import static com.example.myapplication.R.id.bottom_timer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.temporal.ChronoField;

public class TimerActivity extends AppCompatActivity {
    float x1,x2,y1,y2;

    private Chronometer chronometer;
    private long PauseOffSet = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private ImageButton reset_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        switchActivities();

        chronometer = findViewById(R.id.Ieremie_Gabriela_Chrono);
        toggleButton = findViewById(R.id.Ieremie_Gabriela_tbtn);
        reset_btn = findViewById(R.id.Ieremie_Gabriela_btn);

        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chronometer.setBase(SystemClock.elapsedRealtime() - PauseOffSet);
                    chronometer.start();
                    isPlaying = true;
                }
                else{
                    chronometer.stop();
                    PauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
                    isPlaying = false;
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    PauseOffSet = 0;
                    chronometer.stop();
                    isPlaying = false;
                    toggleButton.setChecked(false);
                }else{
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    PauseOffSet = 0;
                    chronometer.stop();
                    isPlaying = false;
                    toggleButton.setChecked(false);
                }
            }
        });

    }

    private void switchActivities(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_timer);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == bottom_home){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            } else if(itemId == R.id.bottom_activities){
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
                finish();
            }else if(itemId == R.id.bottom_memento){
                startActivity(new Intent(getApplicationContext(),AddMemento.class));
                finish();
            }else if(itemId == R.id.bottom_settings){
                startActivity(new Intent(getApplicationContext(),AddSettings.class));
                finish();
            }else if(itemId == R.id.bottom_timer) {
                return true;
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
                    Intent i = new Intent(TimerActivity.this, AddMemento.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(TimerActivity.this, AddSettings.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}