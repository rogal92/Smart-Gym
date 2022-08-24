package com.example.smartgym.database;

//PROTOTYPE
//TODO CHANGE TO ENUMS
public class GymDatabase {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_COACHES = "coaches";
    public static final String KEY_USERS = "users";
    public static final String KEY_EQUIPMENT = "equipment";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String NAME_OPTIONS = "TEXT NOT NULL";
    public static final String COACHES_OPTIONS = "INTEGER FOREIGN KEY";
    public static final String USERS_OPTIONS = "INTEGER FOREIGN KEY";
    public static final String EQUIPMENT_OPTIONS = "INTEGER FOREIGN KEY";
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int COACHES_COLUMN = 2;
    public static final int USERS_COLUMN = 3;
    public static final int EQUIPMENT_COLUMN = 4;
    private static final int DB_VERSION = 1;
    private static final String DEBUG_TAG = "Gym Database";
    private static final String DB_NAME = "database.db";
    private static final String GYM_TABLE = "Gym";
    private static final String DB_CREATE_TODO_TABLE =
            "CREATE TABLE " + GYM_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_NAME + " " + NAME_OPTIONS + ", " +
                    KEY_COACHES + " " + COACHES_OPTIONS +
                    KEY_USERS + " " + USERS_OPTIONS +
                    KEY_EQUIPMENT + " " + EQUIPMENT_OPTIONS +
                    ");";
    private static final String DROP_TODO_TABLE =
            "DROP TABLE IF EXISTS " + GYM_TABLE;
}
