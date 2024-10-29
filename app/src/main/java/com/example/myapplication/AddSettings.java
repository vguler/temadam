package com.example.myapplication;

import static com.example.myapplication.R.id.bottom_home;
import static com.example.myapplication.R.id.bottom_settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Clase.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddSettings extends AppCompatActivity {
    public static final String USER_KEY = "user_key";
    private TextInputEditText tietNume;
    private TextInputEditText tietPrenume;
    private TextInputEditText tietData;
    private ImageButton imageButton;
    private Button saveButton;
    private Intent intent;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        BottomNavigationView bottomNavigationView = findViewById(R.id.Grigoras_Stefan_bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottom_settings);

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
                return true;
            }
            return false;
        });
        initComponents();
        intent = getIntent();
    }

    public void initComponents(){
        tietNume = findViewById(R.id.Ion_Denis_numeText);
        tietPrenume = findViewById(R.id.Ion_Denis_prenumeText);
        tietData = findViewById(R.id.Ion_Denis_nastereText);
        saveButton = findViewById(R.id.Ion_Denis_saveButton);

        Intent homeIntent = new Intent(AddSettings.this, MainActivity.class);

        saveButton.setOnClickListener(v -> {
            if(isValid()){
                User user = builUserFromView();
                intent.putExtra(USER_KEY,user);
                setResult(RESULT_OK);
                Log.i("AddSettings","User" + user);
                startActivity(homeIntent);
                finish();
            }
        });
    }

    private User builUserFromView() {
        String nume = tietNume.getText().toString();
        String prenume = tietPrenume.getText().toString();
        Date dataNastere = eu.ase.ro.damapp.fragments.DateConverter.toDate(tietData.getText().toString());
        return new User(nume,prenume,dataNastere);
    }

    private boolean isValid(){
        if(tietNume.getText() == null || tietNume.getText().toString().length() < 2){
            Toast.makeText(getApplicationContext(), R.string.add_nume_invalid_it_must_have_minimum_2_characters,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tietPrenume.getText() == null || tietPrenume.getText().toString().length() < 2){
            Toast.makeText(getApplicationContext(), R.string.add_prenume_invalid_it_must_have_minimum_2_characters,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(tietData.getText() == null || eu.ase.ro.damapp.fragments.DateConverter.
                toDate(tietData.getText().toString()) == null){
            Toast.makeText(getApplicationContext(),"Data invalid.The format should be dd-MM-yyyy",Toast.LENGTH_LONG).show();
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
                    Intent i = new Intent(AddSettings.this, AddMemento.class);
                    startActivity(i);
                }else if(x1 > x2){
                    Intent i = new Intent(AddSettings.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}