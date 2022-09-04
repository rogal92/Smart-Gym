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
        Set<GymEquipment> test = getFilteredEquipment("olympic");
        test.size();
        return new GymEquipmentRecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GymEquipmentRecycleViewAdapter.MyViewHolder holder, int position) {
        GymEquipment currentEquipment = gymEquipmentList.get(position);
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

    private Set<GymEquipment> getFilteredEquipment(String filterKey) {
        Set<GymEquipment> resultList = new HashSet<>();
        Arrays.stream(EquipmentField.values())
                .forEach(field -> resultList.addAll(filterEquipment(field, filterKey)));
        return resultList;
    }

    private Set<GymEquipment> filterEquipment(EquipmentField field, String filterKey) {
        Set<GymEquipment> resultList;
        switch (field) {
            case NAME -> resultList = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getName().contains(filterKey))
                    .collect(Collectors.toSet());
            case DESCRIPTION -> resultList = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getDescription().contains(filterKey))
                    .collect(Collectors.toSet());
            case MUSCLE_USED -> resultList = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getMuscleUsed().contains(filterKey))
                    .collect(Collectors.toSet());
            case USAGE_TIPS -> resultList = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getUsageTips().contains(filterKey))
                    .collect(Collectors.toSet());
            case FOR_WHO -> resultList = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getForWho().contains(filterKey))
                    .collect(Collectors.toSet());
            default -> resultList = new HashSet<>(gymEquipmentList);
        }
        return resultList;
    }
}
