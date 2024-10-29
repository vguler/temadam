package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_activities;
import static com.example.myapplication.R.id.bottom_home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.Clase.Activitate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    public static final String ACTIVITATE_KEY = "activitate_key";
    ImageView imgGlide;
    Button saveButton;
    private TextInputEditText tietdenumire;
    private TextInputEditText tietdescriere;
    private Spinner prioritateSpinner;
    private Spinner durationSpinner;
    private Intent intent;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imgGlide = findViewById(R.id.Grigoras_Stefan_imgGlide);
        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_activities);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == bottom_home){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            } else if(itemId == R.id.bottom_activities){
                return true;
            }else if(itemId == R.id.bottom_memento){
                startActivity(new Intent(getApplicationContext(),AddMemento.class));
                finish();
            }else if(itemId == R.id.bottom_settings){
                startActivity(new Intent(getApplicationContext(),AddSettings.class));
                finish();
            }
            return false;
        });
        initComponents();
        intent = getIntent();
    }

    private void initComponents(){
        tietdenumire = findViewById(R.id.Ion_Alexandru_denumiretxt);
        tietdescriere = findViewById(R.id.Ion_Alexandru_descrieretxt);
        prioritateSpinner = findViewById(R.id.Ion_Alexandru_spinner);
        durationSpinner = findViewById(R.id.Ion_Alexandru_durataSpinner);
        saveButton = findViewById(R.id.Ion_Alexandru_savebutton);

        Intent homeIntent = new Intent(AddActivity.this, MainActivity.class);

        saveButton.setOnClickListener(v -> {
            if(isValid()){
                Activitate activitate = buildActivitateFromView();
                intent.putExtra(ACTIVITATE_KEY,activitate);
                setResult(RESULT_OK,intent);
                Log.i("AddActivity","Activitate:" + activitate);
                startActivity(homeIntent);
                finish();
            }
        });
    }
    private Activitate buildActivitateFromView(){
        String denumire = tietdenumire.getText().toString();
        String descriere = tietdescriere.getText().toString();
        String prioritate = (String) prioritateSpinner.getSelectedItem();
        String durata = (String) durationSpinner.getSelectedItem();
        return new Activitate(denumire,descriere,durata,prioritate);
    }
    public boolean isValid(){
        if(tietdenumire.getText() == null || tietdenumire.getText().toString().trim().length() < 2){
            Toast.makeText(getApplicationContext(), "Invalid!At least 3 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
                    Intent i = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(AddActivity.this, AddMemento.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}