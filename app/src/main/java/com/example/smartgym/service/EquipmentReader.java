package com.example.smartgym.service;

import static com.example.smartgym.constants.EquipmentField.*;

import android.content.Context;

import com.example.smartgym.constants.EquipmentField;
import com.example.smartgym.dao.GymEquipment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//TODO make a singleton
public class EquipmentReader {
    public static List<GymEquipment> ALL_GYM_EQUIPMENTS = List.of();
    private final Context context;

    public EquipmentReader(Context context) {
        this.context = context;
        ALL_GYM_EQUIPMENTS = getGymEquipment();
    }

    private List<GymEquipment> getGymEquipment() {
        List<GymEquipment> resultList = new ArrayList<>();
        InputStream inputStream = null;
        //TODO refactor
        try {
            inputStream = context.getAssets().open("equipment.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, String> contentMap = new HashMap<>();
        List<String> lines = br.lines().collect(Collectors.toList());
        StringBuilder equipmentMap = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("1")) ++i;

            while (!lines.get(i).startsWith("2"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("2")) {
                contentMap.put(NAME.name, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("3"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("3")) {
                contentMap.put(DESCRIPTION.name, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("4"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("4")) {
                contentMap.put(MUSCLE_USED.name, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("5"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("5")) {
                contentMap.put(USAGE_TIPS.name, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("---"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("---")) {
                contentMap.put(FOR_WHO.name, equipmentMap.toString());
                equipmentMap.setLength(0);
                resultList.add(new GymEquipment(
                        contentMap.get(NAME.name),
                        contentMap.get(DESCRIPTION.name),
                        contentMap.get(MUSCLE_USED.name),
                        contentMap.get(USAGE_TIPS.name),
                        contentMap.get(FOR_WHO.name)));
                contentMap.clear();
            }
        }
        return resultList.stream()
                .sorted(Comparator.comparing(GymEquipment::getName))
                .collect(Collectors.toList());
    }
}
