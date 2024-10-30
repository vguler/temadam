package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.domain.DateConverter;
import com.example.myapplication.domain.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private TextInputEditText tietTaskName;
    private Spinner spnTaskType;

    private TextInputEditText tietTaskDescription;

    private TextInputEditText tietTaskDeadline;

    private Button btnSaveTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponents();
    }

    private void initComponents() {

        tietTaskName = findViewById(R.id.ion_alexandru_tiet_task_name);
        spnTaskType = findViewById(R.id.ion_alexandru_spinner_duration);
        tietTaskDescription = findViewById(R.id.ion_alexandru_tiet_task_description);
        btnSaveTask = findViewById(R.id.ion_alexandru_button_save_task);
        tietTaskDeadline = findViewById(R.id.ion_alexandru_tiet_task_deadline);
        btnSaveTask.setOnClickListener(v -> {

           if (isValid()) {


               Task task = buildTaskFromView();
               Log.i("AddActivity", "Task = " + task);
           }


        });

    }

    private Task buildTaskFromView(){

        String taskName = tietTaskName.getText().toString();
        String taskDescription = tietTaskDescription.getText().toString();
        String taskType = (String) spnTaskType.getSelectedItem();
        Date taskDeadline = DateConverter.toDate(tietTaskDeadline.getText().toString());

        return new Task(taskName,  taskType, taskDescription, taskDeadline);

    }
    private boolean isValid() {

        if (tietTaskName.getText() == null || tietTaskName.getText().toString().trim().length() <= 3) {

            Toast.makeText(getApplicationContext(), R.string.add_task_name_must_have_a_minimum_of_3_characters, Toast.LENGTH_LONG).show();
            return false;
        }

        if (tietTaskDescription.getText() == null || tietTaskDescription.getText().toString().trim().length() <= 10) {

            Toast.makeText(getApplicationContext(), R.string.add_task_description_must_have_a_minimum_of_10_characters, Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    }

    // git add .
    // git commit -m "initial"
    // git push -f origin alex
