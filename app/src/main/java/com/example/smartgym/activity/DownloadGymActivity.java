package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartgym.R;
import com.example.smartgym.adapter.GymRecycleViewAdapter;
import com.example.smartgym.dao.Gym;
import com.example.smartgym.database.GymDatabase;

import java.sql.DatabaseMetaData;
import java.util.List;

public class DownloadGymActivity extends AppCompatActivity {
    //list of gyms should be taken from external db in the future
    private final GymDatabase gymDatabase;

    public DownloadGymActivity() {
        this.gymDatabase = new GymDatabase(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_gym);
        RecyclerView downloadGymRecyclerView = findViewById(R.id.downloadGymRecyclerView);
        var availableGyms = gymDatabase.getAvailableGyms();
        var adapter = new GymRecycleViewAdapter(this, availableGyms);
        downloadGymRecyclerView.setAdapter(adapter);
        downloadGymRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}