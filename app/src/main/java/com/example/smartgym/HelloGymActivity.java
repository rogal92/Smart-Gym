package com.example.smartgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.smartgym.database.GymDatabase;

public class HelloGymActivity extends AppCompatActivity {
    private GymDatabase gymDatabase;
    private Button startTtainingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_gym);

        gymDatabase = new GymDatabase(this);
        startTtainingButton = findViewById(R.id.startTraining);
        startTtainingButton.setOnClickListener(
                view -> new Intent(this, EquipmentFinderActivity.class));

    }
}