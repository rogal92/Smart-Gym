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
        int i = 0;
        if (lines.get(i).startsWith("1"))
            contentValue.append(lines.get(++i));

        while (!lines.get(i).startsWith("2"))
            contentValue.append(lines.get(++i));

        if (lines.get(i).startsWith("2")) {
            contentMap.put(NAME, contentValue.toString());
            contentValue.setLength(0);
            contentValue.append(lines.get(++i));
        }

        while (!lines.get(i).startsWith("3"))
            contentValue.append(lines.get(i));

        if (lines.get(i).startsWith("3")) {
            contentMap.put(DESCRIPTION, contentValue.toString());
            contentValue.setLength(0);
            contentValue.append(lines.get(++i));
        }

        while (!lines.get(i).startsWith("4"))
            contentValue.append(lines.get(i));

        if (lines.get(i).startsWith("4")) {
            contentMap.put(MUSCLE_USED, contentValue.toString());
            contentValue.setLength(0);
            contentValue.append(lines.get(++i));
        }

        while (!lines.get(i).startsWith("5"))
            contentValue.append(lines.get(i));

        if (lines.get(i).startsWith("5")) {
            contentMap.put(USAGE_TIPS, contentValue.toString());
            contentValue.setLength(0);
            contentValue.append(lines.get(++i));
        }

        while (!lines.get(i).startsWith("---"))
            contentValue.append(lines.get(i));

        if (lines.get(i).startsWith("---")) {
            contentMap.put(FOR_WHO, contentValue.toString());
            contentValue.setLength(0);
            contentValue.append(lines.get(++i));
            contentMap.clear();
        }
        return resultList;
    }
}
