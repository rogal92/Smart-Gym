package com.example.smartgym.constants;

public enum EquipmentField {
    NAME("NAME"),
    DESCRIPTION("DESCRIPTION"),
    MUSCLE_USED("MUSCLE USED"),
    USAGE_TIPS("TIPS FOR USING THE MACHINE"),
    FOR_WHO("WHO SHOULD USE IT");

    public final String name;

    EquipmentField(String name) {
        this.name = name;
    }
}
