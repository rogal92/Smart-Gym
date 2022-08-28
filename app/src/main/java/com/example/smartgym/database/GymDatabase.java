package com.example.smartgym.database;

import static com.example.smartgym.database.GymDatabaseProvider.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.smartgym.dao.Coach;
import com.example.smartgym.dao.Equipment;
import com.example.smartgym.dao.Gym;

import java.util.List;

//TODO CHANGE TO ENUMS
public class GymDatabase {
    public static final String KEY_NAME = "NAME";
    public static final String KEY_DESCRIPTION = "DESCRIPTION";
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int COACHES_COLUMN = 2;
    public static final int EQUIPMENT_COLUMN = 3;
    private SQLiteDatabase db;
    private Context context;
    private GymDatabaseProvider dbCreator;

    public GymDatabase(Context context) {
        this.context = context;
    }

    public GymDatabase open() {
        dbCreator = new GymDatabaseProvider(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbCreator.getWritableDatabase();
        } catch (SQLException e) {
            db = dbCreator.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbCreator.close();
    }

    /*TODO
        This method should receive in argument the whole data about the gym that user is
        currently in. Location info should be provided by beacon and the data should be provided
        by gym (what equipment do they have and the locations of it from beacons)
        This will be saved to smartphone memory and should be done only once.
        This will be hardcoded for now.
     */
    public void insertAllGymData() {
        insertGym();
        insertCoaches("Atlantic", "ul. Pakerska 69, Kraków");
        insertEquipment();
    }

    private long insertGym() {
        Log.d(DEBUG_TAG, "Saving Gym to database..");
        ContentValues gymValues = new ContentValues();
        Gym atlantic = new Gym("Atlantic", "ul. Pakerska 69, Kraków");
        gymValues.put("NAME", atlantic.getName());
        gymValues.put("LOCATION", atlantic.getLocation());

        return db.insert(GYM_TABLE, null, gymValues);
    }

    private long insertCoaches(String gymName, String gymLocation) {
        Log.d(DEBUG_TAG, "Saving Coaches to device database...");
        ContentValues coachValues = new ContentValues();
        Cursor currentGym = db.rawQuery("""
                SELECT ID 
                FROM GYM
                WHERE NAME = ?
                AND LOCATION = ?""",
                new String[]{gymName, gymLocation});
        Coach fiedorBodyBuilder = new Coach(1, "Michael Fiedor", currentGym.getLong(1));
        List<Coach> coachList = List.of(fiedorBodyBuilder);
        coachList.forEach(coach -> coachValues.put(KEY_NAME, coach.getName()));
        return db.insert(COACH_TABLE, null, coachValues);
    }

    private long insertEquipment() {
        Log.d(DEBUG_TAG, "Saving Equipment to device database...");
        ContentValues equipmentValues = new ContentValues();
        Equipment barbell = new Equipment(1, "Barbell", "Do workout, don't be a pussy",
                "location to video or photo?");
        List<Equipment> equipmentList = List.of(barbell);
        equipmentList.forEach(equipment -> {
            equipmentValues.put(KEY_NAME, equipment.getName());
            equipmentValues.put(KEY_DESCRIPTION, equipment.getDescription());
        });
        return db.insert(EQUIPMENT_TABLE, null, equipmentValues);
    }
}
