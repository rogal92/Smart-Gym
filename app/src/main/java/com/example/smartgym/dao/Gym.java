package com.example.smartgym.dao;

public class Gym {
    private final long id;
    private final String name;
    private final GymLocation gymLocation;
    private final int logoId;

    public Gym(long id, String name, GymLocation gymLocation, int logoId) {
        this.id = id;
        this.name = name;
        this.gymLocation = gymLocation;
        this.logoId = logoId;
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

    public int getLogoId() {
        return logoId;
    }
}
