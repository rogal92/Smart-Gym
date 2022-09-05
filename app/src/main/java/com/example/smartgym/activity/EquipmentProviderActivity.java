package com.example.smartgym.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;

import com.example.smartgym.R;
import com.example.smartgym.adapter.GymEquipmentRecycleViewAdapter;
import com.example.smartgym.dao.GymEquipment;
import com.example.smartgym.service.EquipmentFilter;
import com.example.smartgym.service.EquipmentReader;
import com.kontakt.sdk.android.common.model.SecureCommandType;

import java.util.List;
import java.util.Set;

public class EquipmentProviderActivity extends AppCompatActivity {

    private final EquipmentFilter equipmentFilter;
    private final GymEquipmentRecycleViewAdapter adapter;

    public EquipmentProviderActivity() {
        Set<GymEquipment> gymEquipmentList = EquipmentReader.ALL_GYM_EQUIPMENTS;
        this.adapter = new GymEquipmentRecycleViewAdapter(this, gymEquipmentList);
        this.equipmentFilter = new EquipmentFilter(gymEquipmentList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_provider);
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