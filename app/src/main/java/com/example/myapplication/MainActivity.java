package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_home);
        progressBar = findViewById(R.id.Grigoras_Stefan_ProgessBar);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == bottom_home){
                return true;
            } else if(itemId == R.id.bottom_activities){
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
                finish();
            }else if(itemId == R.id.bottom_memento){
                startActivity(new Intent(getApplicationContext(),AddMemento.class));
                finish();
            }else if(itemId == R.id.bottom_settings){
                startActivity(new Intent(getApplicationContext(),AddSettings.class));
                finish();
            }
            return false;
        });
            updateProgressBar(50);
    }

    private void updateProgressBar(int progress){
        progressBar.setProgress(progress);
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
                Intent i = new Intent(MainActivity.this, AddSettings.class);
                startActivity(i);
            }else if(x1 > x2){
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
            break;
        }
        return false;
    }
}