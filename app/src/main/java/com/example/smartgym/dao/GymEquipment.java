package com.example.smartgym.dao;

import static com.example.smartgym.constants.EquipmentField.*;
import static com.example.smartgym.service.EquipmentReader.*;

import com.example.smartgym.constants.EquipmentField;

public class GymEquipment {
    private final String name;
    private final String description;
    private final String muscleUsed;
    private final String usageTips;
    private final String forWho;
    private boolean isVisible;

    public GymEquipment(String name, String description, String muscleUsed, String usageTips,
                        String forWho) {
        this.name = name;
        this.description = description;
        this.muscleUsed = muscleUsed;
        this.usageTips = usageTips;
        this.forWho = forWho;
    }


    public boolean isVisible() {
        return isVisible;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMuscleUsed() {
        return muscleUsed;
    }

    public String getUsageTips() {
        return usageTips;
    }

    public String getForWho() {
        return forWho;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    @Override
    public String toString() {
        return String.format(
                """
                %s
                %s
                
                %s
                %s
                
                %s
                %s
                
                %s
                %s
                """,
                DESCRIPTION.name, description,
                MUSCLE_USED.name, muscleUsed,
                USAGE_TIPS.name, usageTips,
                FOR_WHO.name, forWho);
    }
}
