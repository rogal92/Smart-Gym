package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartgym.R;
import com.example.smartgym.database.GymDatabase;

import java.sql.DatabaseMetaData;

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
        //TODO finish him
        gymDatabase.getAvailableGyms();
        gymDatabase.getGymLogos();
    }
}