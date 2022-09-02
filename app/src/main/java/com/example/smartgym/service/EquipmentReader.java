package com.example.smartgym.service;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.smartgym.dao.Equipmentt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquipmentReader {
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String MUSCLE_USED = "MUSCLE_USED";
    private static final String USAGE_TIPS = "TIPS_FOR_USING_THE_MACHINE";
    private static final String FOR_WHO = "WHO_SHOULD_USE_IT";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Equipmentt> getAvailableGymEquipment(Context context) {
        List<Equipmentt> resultList = new ArrayList<>();
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
                contentMap.put(NAME, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("3"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("3")) {
                contentMap.put(DESCRIPTION, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("4"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("4")) {
                contentMap.put(MUSCLE_USED, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("5"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("5")) {
                contentMap.put(USAGE_TIPS, equipmentMap.toString());
                equipmentMap.setLength(0);
                ++i;
            }

            while (!lines.get(i).startsWith("---"))
                equipmentMap.append(lines.get(i++));

            if (lines.get(i).startsWith("---")) {
                contentMap.put(FOR_WHO, equipmentMap.toString());
                equipmentMap.setLength(0);
                resultList.add(new Equipmentt(new HashMap<>(contentMap)));
                contentMap.clear();
            }
        }
        return resultList;
    }
}
