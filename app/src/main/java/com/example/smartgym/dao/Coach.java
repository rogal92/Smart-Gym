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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }
}
