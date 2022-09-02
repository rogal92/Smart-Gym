package com.example.smartgym.dao;

import java.util.Map;

public class Equipmentt {
    private final Map<String, String> contentMap;

    public Equipmentt(Map<String, String> contentMap) {
        this.contentMap = contentMap;
    }

    public Map<String, String> getContentMap() {
        return contentMap;
    }
}
