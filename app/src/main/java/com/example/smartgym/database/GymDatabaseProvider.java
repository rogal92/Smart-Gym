package com.example.smartgym.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class GymDatabaseProvider extends SQLiteOpenHelper {
    static final int DB_VERSION = 1;
    static final String DEBUG_TAG = "GYM DATABASE";
    static final String DB_NAME = "database.db";
    static final String GYM_TABLE = "GYM";
    static final String COACH_TABLE = "COACH";
    private static final String DB_CREATE_COACH_TABLE = """
            CREATE TABLE COACH (
            _ID INTEGER PRIMARY KEY AUTOINCREMENT,
            LOCATION TEXT NOT NULL,
            NAME TEXT NOT NULL);
            """;
    private static final String DB_CREATE_GYM_TABLE = """
            CREATE TABLE GYM (
            _ID INTEGER PRIMARY KEY AUTOINCREMENT,
            NAME TEXT NOT NULL,
            LOCATION TEXT NOT NULL);
            """;

    public GymDatabaseProvider(Context context, String name, SQLiteDatabase.CursorFactory factory,
                               int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(DEBUG_TAG, "Creating database...");
        db.execSQL(DB_CREATE_GYM_TABLE);
        Log.d(DEBUG_TAG, "Table " + GYM_TABLE + " ver." + DB_VERSION + " created");
        db.execSQL(DB_CREATE_COACH_TABLE);
        Log.d(DEBUG_TAG, "Table " + COACH_TABLE + " ver." + DB_VERSION + " created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + GYM_TABLE);
//        onCreate(db);
    }
}
