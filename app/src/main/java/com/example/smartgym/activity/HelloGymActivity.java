package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.smartgym.R;

public class HelloGymActivity extends AppCompatActivity {
    private Button checkEquipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_gym);
        checkEquipment = findViewById(R.id.checkEquipment);
        checkEquipment.setOnClickListener(
                view -> new Intent(this, EquipmentProviderActivity.class));
    }
}
