package com.example.smartgym.dao;

import com.example.smartgym.constants.Goal;

public class User {
    private long id;
    private String name;
    private String gender;
    private int age;
    private Goal goal;

    public User(long id, String name, String gender, int age, Goal goal) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.goal = goal;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
