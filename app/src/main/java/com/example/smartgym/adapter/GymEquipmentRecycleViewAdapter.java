package com.example.smartgym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgym.R;
import com.example.smartgym.constants.EquipmentField;
import com.example.smartgym.dao.GymEquipment;
import com.example.smartgym.service.EquipmentReader;
import com.kontakt.sdk.android.common.model.SecureCommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GymEquipmentRecycleViewAdapter extends RecyclerView.Adapter<GymEquipmentRecycleViewAdapter.MyViewHolder> {
    private Set<GymEquipment> gymEquipmentList;
    private final Context context;

    public GymEquipmentRecycleViewAdapter(Context context, Set<GymEquipment> gymEquipmentList) {
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
        List<GymEquipment> gymEquipments = gymEquipmentList.stream()
                .sorted(Comparator.comparing(GymEquipment::getName)).collect(Collectors.toList());
        GymEquipment currentEquipment = gymEquipments.get(position);
        holder.equipmentName.setText(currentEquipment.getName());
        holder.equipmentData.setText(currentEquipment.toString());
        holder.equipmentName.setOnClickListener(view -> {
            currentEquipment.setVisible(!currentEquipment.isVisible());
            notifyItemChanged(position);
        });
        holder.layout.setVisibility(currentEquipment.isVisible() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return gymEquipmentList.size();
    }

    public void filterEquipment(Set<GymEquipment> filterList) {
        this.gymEquipmentList = filterList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView equipmentName, equipmentData;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            equipmentName = itemView.findViewById(R.id.equipmentName);
            equipmentData = itemView.findViewById(R.id.equipmentData);
            layout = itemView.findViewById(R.id.layoutExpand);
        }
    }
}
