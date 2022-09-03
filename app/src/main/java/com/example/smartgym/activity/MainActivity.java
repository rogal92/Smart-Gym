package com.example.smartgym.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartgym.R;
import com.example.smartgym.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button findGymButton, downloadGymButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        findGymButton = findViewById(R.id.findGymButton);
        downloadGymButton = findViewById(R.id.downloadGymButton);
        findGymButton.setOnClickListener(
                view -> startActivity(new Intent(this, HelloGymActivity.class)));
        downloadGymButton.setOnClickListener(
                view -> startActivity(new Intent(this, DownloadGymActivity.class)));
    }
}
