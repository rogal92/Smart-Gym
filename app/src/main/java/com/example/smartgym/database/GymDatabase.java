package com.example.smartgym.database;

import static android.database.sqlite.SQLiteDatabase.*;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//PROTOTYPE
//TODO CHANGE TO ENUMS
public class GymDatabase {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COACHES = "coaches";
    public static final String KEY_EQUIPMENT = "equipment";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final String COACHES_OPTIONS = "INTEGER FOREIGN KEY";
    public static final String EQUIPMENT_OPTIONS = "INTEGER FOREIGN KEY";
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int COACHES_COLUMN = 2;
    public static final int EQUIPMENT_COLUMN = 3;
    private static final int DB_VERSION = 1;
    private static final String DEBUG_TAG = "Gym Database";
    private static final String DB_NAME = "database.db";
    private static final String GYM_TABLE = "Gym";
    private static final String DB_CREATE_GYM_TABLE =
            "CREATE TABLE " + GYM_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_COACHES + " " + COACHES_OPTIONS +
                    KEY_EQUIPMENT + " " + EQUIPMENT_OPTIONS +
                    ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + GYM_TABLE;
    private SQLiteDatabase db;
    private Context context;
    private DatabaseService dbService;

    public  GymDatabase(Context context) {
        this.context = context;
    }

    public GymDatabase open (){
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

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            //moving old version of db on the device to the new one after update
        }
    }
}
