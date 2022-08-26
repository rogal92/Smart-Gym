package com.example.smartgym.database;

import static android.database.sqlite.SQLiteDatabase.CursorFactory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.smartgym.dao.Coach;
import com.example.smartgym.dao.Equipment;
import com.example.smartgym.dao.Gym;

import java.util.List;

//PROTOTYPE
//TODO CHANGE TO ENUMS
public class GymDatabase {
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_COACHES = "COACHES";
    public static final String KEY_EQUIPMENT = "EQUIPMENT";
    public static final String KEY_LOCATION = "LOCATION";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final String DESCRIPTION_OPTIONS = "TEXT NOT NULL";
    public static final String COACHES_OPTIONS = "INTEGER FOREIGN KEY";
    public static final String EQUIPMENT_OPTIONS = "INTEGER FOREIGN KEY";
    public static final String LOCATION_OPTIONS = "TEXT NOT NULL";
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int COACHES_COLUMN = 2;
    public static final int EQUIPMENT_COLUMN = 3;
    private static final int DB_VERSION = 1;
    private static final String DEBUG_TAG = "GYM DATABASE";
    private static final String DB_NAME = "database.db";
    private static final String GYM_TABLE = "GYM";
    private static final String COACH_TABLE = "COACH";
    private static final String EQUIPMENT_TABLE = "EQUIPMENT";
    private static final String DB_CREATE_COACH_TABLE =
            "CREATE TABLE " + COACH_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS +
                    ");";
    private static final String DB_CREATE_EQUIPMENT_TABLE =
            "CREATE TABLE " + EQUIPMENT_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS +
                    KEY_DESCRIPTION + " " + DESCRIPTION_OPTIONS +
                    ");";
    private static final String DB_CREATE_GYM_TABLE =
            "CREATE TABLE " + GYM_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_LOCATION + " " + LOCATION_OPTIONS +
                    ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + GYM_TABLE;
    private SQLiteDatabase db;
    private Context context;
    private DatabaseService dbService;

    public GymDatabase(Context context) {
        this.context = context;
    }

    public GymDatabase open() {
        dbService = new DatabaseService(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbService.getWritableDatabase();
        } catch (SQLException e) {
            db = dbService.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbService.close();
    }

    private static class DatabaseService extends SQLiteOpenHelper {

        public DatabaseService(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(DEBUG_TAG, "Creating database...");
            db.execSQL(DB_CREATE_GYM_TABLE);
            Log.d(DEBUG_TAG, "Table " + GYM_TABLE + " ver." + DB_VERSION + " created");
            db.execSQL(DB_CREATE_COACH_TABLE);
            Log.d(DEBUG_TAG, "Table " + COACH_TABLE + " ver." + DB_VERSION + " created");
            db.execSQL(DB_CREATE_EQUIPMENT_TABLE);
            Log.d(DEBUG_TAG, "Table " + EQUIPMENT_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            //moving old version of db on the device to the new one after update
        }
    }

    /*TODO
        This method should receive in argument the whole data about the gym that user is
        currently in. Location info should be provided by beacon and the data should be provided
        by gym (what equipment do they have and the locations of it from beacons)
        This will be saved to smartphone memory and should be done only once.
        This will be hardcoded for now.
     */

    public long insertAllGymData() {
        return 0;
    }

    public long insertGym() {
        ContentValues gymValues = new ContentValues();
        Gym atlantic = new Gym("Atlantic", "ul. Pakerska 69, Kraków");
        gymValues.put(KEY_NAME, atlantic.getName());
        gymValues.put(KEY_LOCATION, atlantic.getLocation());

        return db.insert(GYM_TABLE, null, gymValues);
    }


    public long insertCoaches(String gymName, String gymLocation) {
        ContentValues coachValues = new ContentValues();
        Cursor currentGym = db.rawQuery("SELECT ID " +
                        "FROM GYM " +
                        "WHERE NAME = ?" +
                        "AND LOCATION = ?",
                new String[]{gymName, gymLocation});


        Coach fiedorBodyBuilder = new Coach(1, "Michael Fiedor", currentGym.getLong(1));
        List<Coach> coachList = List.of(fiedorBodyBuilder);
        coachList.forEach(coach -> coachValues.put(KEY_NAME, coach.getName()));
        return db.insert(COACH_TABLE, null, coachValues);
    }

    public long insertEquipment() {
        ContentValues equipmentValues = new ContentValues();
        List<Equipment> equipmentList = List.of(barbell);
        Equipment barbell = new Equipment(1, "Barbell", "Do workout dont be a pussy",
                "location to video or photo?");
        equipmentList.forEach(equipment -> {
            equipmentValues.put(KEY_NAME, equipment.getName());
            equipmentValues.put(KEY_DESCRIPTION, equipment.getDescription());
        });
        return db.insert(EQUIPMENT_TABLE, null, equipmentValues);
    }
}
