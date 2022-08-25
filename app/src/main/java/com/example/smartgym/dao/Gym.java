package com.example.smartgym.dao;

import java.util.Set;

public class Gym {
    private long id;
    private String name;
    private Set<GymCoach> coaches;
    private Set<Equipment> gymEquipment;

    public Gym(long id, String name, Set<GymCoach> coaches, Set<Equipment> gymEquipment) {
        this.id = id;
        this.name = name;
        this.coaches = coaches;
        this.gymEquipment = gymEquipment;
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

    public Set<GymCoach> getCoaches() {
        return coaches;
    }

    public void setCoaches(Set<GymCoach> coaches) {
        this.coaches = coaches;
    }

    public Set<Equipment> getGymEquipment() {
        return gymEquipment;
    }

    public void setGymEquipment(Set<Equipment> gymEquipment) {
        this.gymEquipment = gymEquipment;
    }
}
