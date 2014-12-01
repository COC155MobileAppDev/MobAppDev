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
            + COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + " integer foreign key)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates modules table
        db.execSQL(DATABASE_CREATE_MODULES);
        db.execSQL(DATABASE_CREATE_MODULE_TIME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULES);

        //creates modules table
        onCreate(db);
    }

}
