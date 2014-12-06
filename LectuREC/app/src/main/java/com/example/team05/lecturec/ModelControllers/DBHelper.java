package com.example.team05.lecturec.ModelControllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sa on 29/11/2014.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LectuRec";

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


    //module_time table
    public static final String TABLE_MODULE_TIME = "module_time";
    public static final String COLUMN_MODULE_TIME_ID = "_module_time_id";
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
    public static final String COLUMN_FOLDER_ID = "_folder_id";
    public static final String COLUMN_FOLDER_NAME = "folder_name";
    public static final String COLUMN_FOLDER_MODULE_ID_FOREIGN = "_module_id";

    private static final String DATABASE_CREATE_FOLDER = "create table "
            + TABLE_FOLDER
            + "("
            + COLUMN_FOLDER_ID + " integer primary key autoincrement, "
            + COLUMN_FOLDER_NAME + " text not null, "
            + COLUMN_FOLDER_MODULE_ID_FOREIGN + " integer, foreign key("
                + COLUMN_FOLDER_MODULE_ID_FOREIGN + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + ") "
            + ")";


    //Session table
    public static final String TABLE_SESSION = "session";
    public static final String COLUMN_SESSION_ID = "_session_id";
    public static final String COLUMN_SESSION_NAME = "session_name";
    public static final String COLUMN_SESSION_MODULE_ID_FOREIGN = "_module_id";
    public static final String COLUMN_SESSION_FOLDER_ID_FOREIGN = "_folder_id";

    //old version
    /*private static final String DATABASE_CREATE_SESSION = "create table "
            + TABLE_SESSION
            + "("
            + COLUMN_SESSION_ID + " integer primary key autoincrement, "
            + COLUMN_SESSION_NAME + " text not null, "
            + COLUMN_SESSION_MODULE_ID_FOREIGN + " integer, foreign key("
                + COLUMN_SESSION_MODULE_ID_FOREIGN + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + "), "
            + COLUMN_SESSION_FOLDER_ID_FOREIGN + " integer, foreign key("
                + COLUMN_SESSION_MODULE_ID_FOREIGN + ") references "
                + TABLE_FOLDER + "(" + COLUMN_FOLDER_ID + ") "
            + ")";*/

    private static final String DATABASE_CREATE_SESSION = "create table "
            + TABLE_SESSION
            + "("
            + COLUMN_SESSION_ID + " integer primary key autoincrement, "
            + COLUMN_SESSION_NAME + " text not null, "
            + COLUMN_SESSION_MODULE_ID_FOREIGN + " integer, "
            + COLUMN_SESSION_FOLDER_ID_FOREIGN + " integer, "
            + "foreign key(" + COLUMN_SESSION_MODULE_ID_FOREIGN
                + ") references "
                + TABLE_MODULES + "(" + COLUMN_MODULE_ID + "), "
            + "foreign key(" + COLUMN_SESSION_MODULE_ID_FOREIGN
                + ") references "
                + TABLE_FOLDER + "(" + COLUMN_FOLDER_ID + ") "
            + ")";


    //audio table
    public static final String TABLE_AUDIO = "audio";
    public static final String COLUMN_AUDIO_ID = "_audio_id";
    public static final String COLUMN_AUDIO_NAME = "audio_name";
    public static final String COLUMN_AUDIO_FILE = "audio_file";
    public static final String COLUMN_AUDIO_DURATION = "audio_duration";
    public static final String COLUMN_AUDIO_START_TIME = "audio_start_time";
    public static final String COLUMN_AUDIO_SESSION_ID_FOREIGN = "_session_id";

    private static final String DATABASE_CREATE_AUDIO = "create table "
            + TABLE_AUDIO
            + "("
            + COLUMN_AUDIO_ID + " integer primary key autoincrement, "
            + COLUMN_AUDIO_NAME + " text not null, "
            + COLUMN_AUDIO_FILE + " text, "
            + COLUMN_AUDIO_DURATION + " text, "
            + COLUMN_AUDIO_START_TIME + " text, "
            + COLUMN_AUDIO_SESSION_ID_FOREIGN + " integer, foreign key("
                + COLUMN_AUDIO_SESSION_ID_FOREIGN + ") references "
                + TABLE_SESSION + "(" + COLUMN_SESSION_ID + ") "
            + ")";


    // image table
    public static final String TABLE_IMAGE = "image";
    public static final String COLUMN_IMAGE_ID = "_image_id";
    public static final String COLUMN_IMAGE_FILE = "image_file";
    public static final String COLUMN_IMAGE_SESSION_ID_FOREIGN = "_session_id";

    private static final String DATABASE_CREATE_IMAGE = "create table "
            + TABLE_IMAGE
            + "("
            + COLUMN_IMAGE_ID + " integer primary key autoincrement, "
            + COLUMN_IMAGE_FILE + " text, "
            + COLUMN_IMAGE_SESSION_ID_FOREIGN + " integer, foreign key("
                + COLUMN_IMAGE_SESSION_ID_FOREIGN + ") references "
                + TABLE_SESSION + "(" + COLUMN_SESSION_ID + ") "
            + ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_MODULES);
        db.execSQL(DATABASE_CREATE_MODULE_TIME);
        db.execSQL(DATABASE_CREATE_FOLDER);
        db.execSQL(DATABASE_CREATE_SESSION);
        db.execSQL(DATABASE_CREATE_AUDIO);
        db.execSQL(DATABASE_CREATE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULE_TIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOLDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);

        onCreate(db);
    }

}
