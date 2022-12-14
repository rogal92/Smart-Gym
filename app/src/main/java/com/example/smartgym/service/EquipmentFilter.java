package com.example.smartgym.service;

import com.example.smartgym.constants.EquipmentField;
import com.example.smartgym.dao.GymEquipment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EquipmentFilter {

    private final Set<GymEquipment> gymEquipmentList;

    public EquipmentFilter(Set<GymEquipment> gymEquipmentList) {
        this.gymEquipmentList = gymEquipmentList;
    }

    public Set<GymEquipment> getFilteredEquipment(String filterKey) {
        Set<GymEquipment> resultList = new HashSet<>();
        Arrays.stream(EquipmentField.values())
                .forEach(field -> resultList.addAll(filterEquipment(field, filterKey)));
        return resultList;
    }

    private Set<GymEquipment> filterEquipment(EquipmentField field, String filterKey) {
        Set<GymEquipment> resultSet;
        switch (field) {
            case NAME -> resultSet = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getName().contains(filterKey))
                    .collect(Collectors.toSet());
            case DESCRIPTION -> resultSet = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getDescription().contains(filterKey))
                    .collect(Collectors.toSet());
            case MUSCLE_USED -> resultSet = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getMuscleUsed().contains(filterKey))
                    .collect(Collectors.toSet());
            case USAGE_TIPS -> resultSet = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getUsageTips().contains(filterKey))
                    .collect(Collectors.toSet());
            case FOR_WHO -> resultSet = gymEquipmentList.stream()
                    .filter(gymEquipment -> gymEquipment.getForWho().contains(filterKey))
                    .collect(Collectors.toSet());
            default -> resultSet = new HashSet<>(gymEquipmentList);
        }
        return resultSet;
    }
}
