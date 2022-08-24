package com.example.smartgym.gym;

import com.example.smartgym.dao.GymCoach;
import com.example.smartgym.dao.GymUser;

import java.util.List;

public class Gym {
    private String name;
    private List<GymCoach> coaches;
    private List<GymUser> users;
    private List<Equipment> gymEquipment;
}
