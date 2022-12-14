package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import com.example.smartgym.R;
import com.example.smartgym.adapter.GymEquipmentRecycleViewAdapter;
import com.example.smartgym.service.EquipmentFilter;
import com.example.smartgym.service.EquipmentReader;

public class EquipmentProviderActivity extends AppCompatActivity {
    private EquipmentFilter equipmentFilter;
    private GymEquipmentRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_provider);
        var gymEquipments = EquipmentReader.getEquipmentReader(this).getGymEquipments();
        this.adapter = new GymEquipmentRecycleViewAdapter(this, gymEquipments);
        this.equipmentFilter = new EquipmentFilter(gymEquipments);
        RecyclerView gymEquipmentProviderView = findViewById(R.id.equipmentRecycleView);
        gymEquipmentProviderView.setAdapter(adapter);
        gymEquipmentProviderView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearchEquipment);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String filterKey) {
                adapter.filterEquipment(equipmentFilter.getFilteredEquipment(filterKey));
                return false;
            }
        });
        return true;
    }
}