package com.example.smartgym.dao;

public class Coach {
    private long id;
    private String name;
    private long gymId;

    public Coach(long id, String name, long gymId) {
        this.id = id;
        this.name = name;
        this.gymId = gymId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getGymId() {
        return gymId;
    }
}
