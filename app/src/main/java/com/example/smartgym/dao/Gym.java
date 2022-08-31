package com.example.smartgym.dao;

public class Gym {
    private final long id;
    private final String name;
    private final GymLocation gymLocation;

    public Gym(long id, String name, GymLocation gymLocation) {
        this.id = id;
        this.name = name;
        this.gymLocation = gymLocation;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GymLocation getGymLocation() {
        return gymLocation;
    }
}
