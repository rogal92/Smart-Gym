package com.example.smartgym.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import com.example.smartgym.R;
import com.example.smartgym.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.smartgym.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionMenuItemView findGymItemView = findViewById(R.id.findGymButton);
        ActionMenuItemView downloadGymItemView = findViewById(R.id.downloadGymButton);
        ActionMenuItemView getEquipmentItemView = findViewById(R.id.getAllEquipment);
        findGymItemView.setOnClickListener(
                view -> startActivity(new Intent(this, HelloGymActivity.class)));
        downloadGymItemView.setOnClickListener(
                view -> startActivity(new Intent(this, DownloadGymActivity.class)));
        getEquipmentItemView.setOnClickListener(
                view -> startActivity(new Intent(this, EquipmentProviderActivity.class)));
    }
}
