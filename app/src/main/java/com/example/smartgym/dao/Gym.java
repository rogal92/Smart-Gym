package com.example.smartgym.dao;

public class Gym {
    private long id;
    private String name;
    private GymLocation gymLocation;

    public Gym(long id, String name, GymLocation gymLocation) {
        this.id = id;
        this.name = name;
        this.gymLocation = gymLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GymLocation getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(GymLocation gymLocation) {
        this.gymLocation = gymLocation;
    }
}
