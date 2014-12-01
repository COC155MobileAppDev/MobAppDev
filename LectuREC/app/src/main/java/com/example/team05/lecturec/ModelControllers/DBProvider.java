package com.example.team05.lecturec.ModelControllers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Sa on 29/11/2014.
 */
public class DBProvider extends ContentProvider {

    private static final String AUTH = "com.example.team05.lecturec";
    public static final Uri MODULE_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_MODULES);
    //to access more than one table, make another URI so like AUDIO_URI etc
    //and also it then uses the URI matcher to match to that table/URI

    final static int MODULES = 1;

    SQLiteDatabase db;
    DBHelper dbHelper;

    private static final UriMatcher uriMatcher;

    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_MODULES, MODULES);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();

        if(uriMatcher.match(uri) == MODULES) {
            db.insert(dbHelper.TABLE_MODULES, null, values);
        }

        db.close();

        getContext().getContentResolver().notifyChange(uri, null);

        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sb = new SQLiteQueryBuilder();

        if(uriMatcher.match(uri) == MODULES) {
            sb.setTables(dbHelper.TABLE_MODULES);
        }

        db = dbHelper.getWritableDatabase(); // don't actually if it should be writeable() or readable()

        Cursor cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;

        // other way //

        /*
        Cursor cursor;

        db = dbHelper.getReadableDatabase();
        cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
        */
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int rowsDeleted = 0;

        if(uriMatcher.match(uri) == MODULES) {
            rowsDeleted = db.delete(dbHelper.TABLE_MODULES, selection, selectionArgs);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int rowsUpdated = 0;

        if(uriMatcher.match(uri) == MODULES) {
            rowsUpdated  = db.update(dbHelper.TABLE_MODULES, values, selection, selectionArgs);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

}
