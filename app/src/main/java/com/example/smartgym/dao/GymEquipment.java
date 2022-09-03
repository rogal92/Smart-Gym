package com.example.smartgym.dao;

import java.util.Map;

public class GymEquipment {
    private final Map<String, String> contentMap;
    private boolean isExpanded;

    public GymEquipment(Map<String, String> contentMap) {
        this.contentMap = contentMap;
    }

    public Map<String, String> getContentMap() {
        return contentMap;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
