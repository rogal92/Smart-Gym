package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.smartgym.R;

public class HelloGymActivity extends AppCompatActivity {
    private Button startTtainingButton, checkEquipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_gym);
        startTtainingButton = findViewById(R.id.startTraining);
        checkEquipment = findViewById(R.id.checkEquipment);
        startTtainingButton.setOnClickListener(
                view -> new Intent(this, TrainingActivity.class));
        checkEquipment.setOnClickListener(
                view -> new Intent(this, EquipmentProviderActivity.class));
    }
}
