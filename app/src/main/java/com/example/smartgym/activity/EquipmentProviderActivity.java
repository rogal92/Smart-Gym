package com.example.smartgym.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.smartgym.R;
import com.example.smartgym.adapter.GymEquipmentRecycleViewAdapter;
import com.example.smartgym.dao.GymEquipment;
import com.example.smartgym.service.EquipmentReader;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EquipmentProviderActivity extends AppCompatActivity {
    private final EquipmentReader equipmentReader;

    public EquipmentProviderActivity() {
        this.equipmentReader = new EquipmentReader();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_provider);
        RecyclerView gymEquipmentProviderView = findViewById(R.id.equipmentRecycleView);
        var adapter = new GymEquipmentRecycleViewAdapter(this, getGymEquipment());
        gymEquipmentProviderView.setAdapter(adapter);
        gymEquipmentProviderView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<GymEquipment> getGymEquipment() {
        Callable<List<GymEquipment>> getGymEquipmentCallable =
                () -> equipmentReader.getGymEquipment(this);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        var getEquipmentFuture = executorService.submit(getGymEquipmentCallable);

        try {
            return getEquipmentFuture.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        return null;
    }
}