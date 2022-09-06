package com.example.smartgym.constants;

import com.example.smartgym.R;

public enum EquipmentImage {
    AB_ROLLER(R.drawable.abroller);

    private int resource;

    EquipmentImage(int resource) {
        this.resource = resource;
    }

    public int getResource() {
        return resource;
    }
}
