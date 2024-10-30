package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;
import static com.example.myapplication.R.id.bottom_memento;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddMemento extends AppCompatActivity {
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memento);

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