package com.example.smartgym.dao;

import java.util.Set;

public class Gym {
    private long id;
    private String name;
    private String location;

    public Gym(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
