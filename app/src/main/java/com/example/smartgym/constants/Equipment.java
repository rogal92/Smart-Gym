package com.example.smartgym.constants;

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

public class Equipment {
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
        StringBuilder contentValue = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("1")) continue;
            while (!lines.get(i).startsWith("1.END")) contentValue.append(lines.get(i));
            if (lines.get(i).startsWith("2")) {
                updateMap(contentMap, NAME, contentValue);
                continue;
            }
            while (!lines.get(i).startsWith("2.END")) contentValue.append(lines.get(i));
            if (lines.get(i).startsWith("3")) {
                updateMap(contentMap, DESCRIPTION, contentValue);
                continue;
            }
            while (!lines.get(i).startsWith("3.END")) contentValue.append(lines.get(i));
            if (lines.get(i).startsWith("4")) {
                updateMap(contentMap, MUSCLE_USED, contentValue);
                continue;
            }
            while (!lines.get(i).startsWith("4.END")) contentValue.append(lines.get(i));
            if (lines.get(i).startsWith("5")) {
                updateMap(contentMap, USAGE_TIPS, contentValue);
                continue;
            }
            while (!lines.get(i).startsWith("5.END")) contentValue.append(lines.get(i));
            if (lines.get(i).startsWith("---")) {
                updateMap(contentMap, FOR_WHO, contentValue);
                resultList.add(new Equipmentt(contentMap));
                contentMap.clear();
            }
        }
        return resultList;
    }

    private static void updateMap(Map<String, String> contentMap, String key,
                                  StringBuilder contentValue) {
        contentMap.put(key, contentValue.toString());
        contentValue.setLength(0);
    }
}
