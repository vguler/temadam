package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    float x1,x2,y1,y2;
    TextView nameView;
    TextView surnameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchActivities();

        userDataCallback();
    }

    private void userDataCallback() {
        nameView = findViewById(R.id.Ion_Denis_userNameView);
        surnameView = findViewById(R.id.Ion_Denis_userSurnameView);

        Intent intent = getIntent();

        if (intent.hasExtra(AddSettings.USER_NAME_KEY) && intent.hasExtra(AddSettings.USER_SURNAME_KEY)) {
            String name = intent.getStringExtra(AddSettings.USER_NAME_KEY);
            String surname = intent.getStringExtra(AddSettings.USER_SURNAME_KEY);

            if (name != null && surname != null) {
                nameView.setText(name);
                surnameView.setText(surname);
            }
        }
    }

    private void switchActivities(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_home);

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