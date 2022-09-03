package com.example.smartgym.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgym.R;
import com.example.smartgym.dao.Gym;
import com.example.smartgym.dao.GymLocation;

import java.util.List;

public class GymRecycleViewAdapter extends RecyclerView.Adapter<GymRecycleViewAdapter.MyViewHolder> {
    private final Context context;
    private final List<Gym> gymList;

    public GymRecycleViewAdapter(Context context, List<Gym> gymList) {
        this.context = context;
        this.gymList = gymList;
    }

    @NonNull
    @Override
    public GymRecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_gym_row, parent, false);
        return new GymRecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GymRecycleViewAdapter.MyViewHolder holder, int position) {
        Gym currentGym = gymList.get(position);
        GymLocation gymLocation = currentGym.getGymLocation();

        holder.gymName.setText(currentGym.getName());
        holder.localization.setText(String.format("%s, %s%n%s %d",
                gymLocation.getCity(),
                gymLocation.getPostCode(),
                gymLocation.getStreet(),
                gymLocation.getStreetNumber()));
        holder.gymLogo.setImageResource(currentGym.getLogoId());
    }

    @Override
    public int getItemCount() {
        return gymList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gymLogo;
        TextView gymName, localization;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gymLogo = itemView.findViewById(R.id.imageView);
            gymName = itemView.findViewById(R.id.equipmentName);
            localization = itemView.findViewById(R.id.gymLocalization);
        }
    }
}
