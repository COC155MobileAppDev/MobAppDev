package com.example.sa.lecturectest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sa on 29/11/2014.
 */
public class LectuRecDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LecturerRecorder";

    //modules table
    public static final String TABLE_MODULES = "modules";
    public static final String COLUMN_MODULE_ID = "_module_id";
    public static final String COLUMN_MODULE_NAME = "module";
    public static final String COLUMN_MODULE_ARCHIVE = "archive";

    private static final String DATABASE_CREATE_MODULES = "create table "
            + TABLE_MODULES
            + "("
            + COLUMN_MODULE_ID + " integer primary key autoincrement, "
            + COLUMN_MODULE_NAME + " text not null, "
            + COLUMN_MODULE_ARCHIVE + " integer)";


    //private static final String DATABASE_DROP_MODULES = "DROP TABLE IF EXISTS " + TABLE_MODULES;

    /*
    //module_time
    public static final String TABLE_MODULE_TIME = "module_time";
    public static final String COLUMN_MODULE_TIME_ID = "_id";
    public static final String COLUMN_MODULE_TIME_DAY = "module_time_day";
    public static final String COLUMN_MODULE_TIME_START_TIME = "start_time";
    public static final String COLUMN_MODULE_TIME_END_TIME = "end_time";
    public static final String COLUMN_MODULE_TIME_NOTIFICATION = "notification";
    public static final String COLUMN_MODULE_TIME_MODULE_ID_FOREIGN = "_module_id";

    private static final String DATABASE_CREATE_MODULE_TIME = "create table "
            + TABLE_MODULE_TIME
            + "("
            + COLUMN_MODULE_TIME_ID + " integer primary key autoincrement, "
            + COLUMN_MODULE_TIME_DAY + " integer, "
            + COLUMN_MODULE_TIME_START_TIME + " text not null, "
            + COLUMN_MODULE_TIME_END_TIME + " text not null, "
            + COLUMN_MODULE_TIME_NOTIFICATION + " integer, "
            + COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + " integer, foreign key("
                + COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + ") "
            + ")";


    //folder table
    public static final String TABLE_FOLDER = "folder";
    public static final String COLUMN_FOLDER_ID = "_id";
    public static final String COLUMN_FOLDER_NAME = "folder_name";
    public static final String COLUMN_FOLDER_MODULE_ID_FOREIGN = "_module_id";

    private static final String DATABASE_CREATE_FOLDER = "create table "
            + TABLE_FOLDER
            + "("
            + COLUMN_FOLDER_ID + " integer primary key autoincrement, "
            + COLUMN_FOLDER_NAME + " text not null, "
            + COLUMN_FOLDER_MODULE_ID_FOREIGN + "integer, foreign key("
                + COLUMN_FOLDER_MODULE_ID_FOREIGN + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + ") "
            + ")";


    //Session table
    public static final String TABLE_SESSION = "session";
    public static final String COLUMN_SESSION_ID = "_id";
    public static final String COLUMN_SESSION_NAME = "session_name";
    public static final String COLUMN_SESSION_MODULE_ID_FOREIGN = "_module_id";
    public static final String COLUMN_SESSION_FOLDER_ID_FOREIGN = "_folder_id";

    private static final String DATABASE_CREATE_SESSION = "create table "
            + TABLE_SESSION
            + "("
            + COLUMN_SESSION_ID + "integer primary key autoincrement, "
            + COLUMN_SESSION_NAME + " text not null, "
            + COLUMN_SESSION_MODULE_ID_FOREIGN + "integer, foreign key("
                + COLUMN_SESSION_MODULE_ID_FOREIGN + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + "), "
            + COLUMN_SESSION_FOLDER_ID_FOREIGN + "integer, foreign key("
                + COLUMN_SESSION_MODULE_ID_FOREIGN + ") references "
                + TABLE_FOLDER + "(" + COLUMN_FOLDER_ID + ") "
            + ")";


    //audio table
    public static final String TABLE_AUDIO = "audio";
    public static final String COLUMN_AUDIO_ID = "_id";
    public static final String COLUMN_AUDIO_NAME = "audio_name";
    //public static final String COLUMN_AUDIO_
    */


    public LectuRecDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL(DATABASE_DROP_MODULES);
        db.execSQL(DATABASE_CREATE_MODULES);
        //db.execSQL(DATABASE_CREATE_MODULE_TIME);
        //db.execSQL(DATABASE_CREATE_FOLDER);
        //db.execSQL(DATABASE_CREATE_SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULES);

        //creates modules table
        onCreate(db);
    }

}
