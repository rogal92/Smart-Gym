package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartgym.R;
import com.example.smartgym.adapter.GymEquipmentRecycleViewAdapter;
import com.example.smartgym.service.EquipmentReader;

public class EquipmentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_provider);
        RecyclerView gymEquipmentProviderView = findViewById(R.id.equipmentRecycleView);
        var adapter = new GymEquipmentRecycleViewAdapter(this, EquipmentReader.ALL_GYM_EQUIPMENTS);
        gymEquipmentProviderView.setAdapter(adapter);
        gymEquipmentProviderView.setLayoutManager(new LinearLayoutManager(this));
    }
}