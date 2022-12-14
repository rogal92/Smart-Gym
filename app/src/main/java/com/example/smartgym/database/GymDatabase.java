package com.example.smartgym.database;

import static com.example.smartgym.database.GymDatabaseProvider.COACH_TABLE;
import static com.example.smartgym.database.GymDatabaseProvider.DB_NAME;
import static com.example.smartgym.database.GymDatabaseProvider.DB_VERSION;
import static com.example.smartgym.database.GymDatabaseProvider.DEBUG_TAG;
import static com.example.smartgym.database.GymDatabaseProvider.GYM_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.smartgym.R;
import com.example.smartgym.dao.Coach;
import com.example.smartgym.dao.Gym;
import com.example.smartgym.dao.GymLocation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            insertAllGymData();
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
        by gym (what equipment.txt do they have and the locations of it from beacons)
        This will be saved to smartphone memory and should be done only once.
        This will be hardcoded for now.
     */
    public void insertAllGymData() {
        insertGym();
        insertCoaches("Atlantic", "ul. Pakerska 69, Krak??w");
    }

    public int[] getGymLogos() {
        return new int[]{R.drawable.atlantic, R.drawable.gym};

    }

    public List<Gym> getAvailableGyms() {
        //temporary hardcoded, will be fetched from external db
        Gym atlantic = new Gym(1, "Atlantic", new GymLocation(
                "Ma??opolska",
                "Krak??w",
                "34-500",
                "Pakerska",
                69),
                R.drawable.atlantic);
        Gym calypso = new Gym(2, "Calypso", new GymLocation(
                "Ma??opolska",
                "Krak??w",
                "34-500",
                "Tam Ko??o Galerii",
                69),
                R.drawable.gym);
        List<Gym> gymList = List.of(calypso, atlantic,calypso, atlantic,calypso, atlantic,calypso, atlantic,calypso, atlantic,calypso, atlantic,calypso, atlantic,calypso, atlantic);
        return gymList;
    }

    private long insertGym() {
        Log.d(DEBUG_TAG, "Saving Gym to database..");
        ContentValues gymValues = new ContentValues();
        Gym atlantic = new Gym(1, "Atlantic", new GymLocation(
                "Ma??opolska",
                "Krak??w",
                "34-500",
                "Pakerska",
                69),
                R.drawable.atlantic);
        gymValues.put("NAME", atlantic.getName());
        gymValues.put("LOCATION", atlantic.getGymLocation().toString());

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
        boolean isCursorOnGymRow = currentGym.getCount() == 1 && currentGym.moveToFirst();
        Coach fiedorBodyBuilder = new Coach(1, "Michael Fiedor",
                isCursorOnGymRow ? currentGym.getLong(1) : null); //TODO check the result of this and handle situation when gym is is not found
        List<Coach> coachList = List.of(fiedorBodyBuilder);
        coachList.forEach(coach -> coachValues.put(KEY_NAME, coach.getName()));
        return db.insert(COACH_TABLE, null, coachValues);
    }
}
