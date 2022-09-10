package com.example.smartgym.constants;

import com.example.smartgym.R;

public enum EquipmentImage {
    AB_ROLLER(R.drawable.abroller),
    BENCH_PRESS(R.drawable.benchpress),
    CHEST_FLY_MACHINE(R.drawable.chestflymachine),
    CHEST_PRESS_MACHINE(R.drawable.chestpressmachine),
    DECLINED_BENCH_PRESS(R.drawable.declinedbenchpress);

    private int resource;

    EquipmentImage(int resource) {
        this.resource = resource;
    }

    public int getResource() {
        return resource;
    }
}
