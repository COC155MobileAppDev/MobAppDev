package com.example.sa.lecturectest2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
  Created by Sa on 29/11/2014.
 */
public class LectuRecDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LecturerRecorder";

    public static final String TABLE_MODULES = "modules";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MODULE = "module";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_MODULES
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MODULE + " text not null);";


    public LectuRecDBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates modules table
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULES);

        //creates modules table
        onCreate(db);
    }

}
