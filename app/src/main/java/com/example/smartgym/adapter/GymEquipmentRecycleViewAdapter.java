package com.example.smartgym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgym.R;
import com.example.smartgym.dao.GymEquipment;
import com.example.smartgym.service.EquipmentReader;

import java.util.List;

public class GymEquipmentRecycleViewAdapter extends RecyclerView.Adapter<GymEquipmentRecycleViewAdapter.MyViewHolder> {
    private final List<GymEquipment> gymEquipmentList;
    private final Context context;

    public GymEquipmentRecycleViewAdapter(Context context, List<GymEquipment> gymEquipmentList) {
        this.context = context;
        this.gymEquipmentList = gymEquipmentList;
    }

    @NonNull
    @Override
    public GymEquipmentRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_equipment_row, parent, false);
        return new GymEquipmentRecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GymEquipmentRecycleViewAdapter.MyViewHolder holder, int position) {
        GymEquipment currentEquipment = gymEquipmentList.get(position);
        holder.equipmentName.setText(currentEquipment.getContentMap().get(EquipmentReader.NAME));
    }

    @Override
    public int getItemCount() {
        return gymEquipmentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView equipmentName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentName = itemView.findViewById(R.id.equipmentName);
        }
    }
}
