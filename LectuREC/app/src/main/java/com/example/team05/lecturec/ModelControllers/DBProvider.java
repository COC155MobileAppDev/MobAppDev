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
    public static final Uri MODULE_TIME_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_MODULE_TIME);
    public static final Uri FOLDER_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_FOLDER);
    public static final Uri SESSION_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_SESSION);
    public static final Uri AUDIO_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_AUDIO);
    public static final Uri IMAGE_URI = Uri.parse("content://"+AUTH+"/"+ DBHelper.TABLE_IMAGE);

    final static int MODULES = 1;
    final static int MODULE_TIME = 2;
    final static int FOLDER = 3;
    final static int SESSION = 4;
    final static int AUDIO = 5;
    final static int IMAGE = 6;

    SQLiteDatabase db;
    DBHelper dbHelper;

    private static final UriMatcher uriMatcher;

    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_MODULES, MODULES);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_MODULE_TIME, MODULE_TIME);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_FOLDER, FOLDER);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_SESSION, SESSION);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_AUDIO, AUDIO);
        uriMatcher.addURI(AUTH, DBHelper.TABLE_IMAGE, IMAGE);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {
        System.out.println("hello for modules!!!!!");
        db = dbHelper.getWritableDatabase();

        // old version
        /*
        if(uriMatcher.match(uri) == MODULES) {
            System.out.println("greetings for modules!!!!!");
            db.insert(dbHelper.TABLE_MODULES, null, values);
        }
        */

        switch (uriMatcher.match(uri)) {
            case MODULES:
                db.insert(dbHelper.TABLE_MODULES, null, values);
                break;
            case MODULE_TIME:
                db.insert(dbHelper.TABLE_MODULE_TIME, null, values);
                break;
            case FOLDER:
                db.insert(dbHelper.TABLE_FOLDER, null, values);
                break;
            case SESSION:
                db.insert(dbHelper.TABLE_SESSION, null, values);
                break;
            case AUDIO:
                db.insert(dbHelper.TABLE_AUDIO, null, values);
                break;
            case IMAGE:
                db.insert(dbHelper.TABLE_IMAGE, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        db.close();

        getContext().getContentResolver().notifyChange(uri, null);

        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //first version
        /*
        SQLiteQueryBuilder sb = new SQLiteQueryBuilder();

        if(uriMatcher.match(uri) == MODULES) {
            sb.setTables(dbHelper.TABLE_MODULES);
        }

        db = dbHelper.getWritableDatabase(); // don't actually if it should be writeable() or readable()

        Cursor cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
        */

        // other way //

        /*
        Cursor cursor;

        db = dbHelper.getReadableDatabase();
        cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
        */

        //using switch statements
        /* ORIGINAL VERSION !!!
        Cursor cursor;

        db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case MODULES:
                cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MODULE_TIME:
                cursor = db.query(dbHelper.TABLE_MODULE_TIME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case FOLDER:
                cursor = db.query(dbHelper.TABLE_FOLDER, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SESSION:
                cursor = db.query(dbHelper.TABLE_SESSION, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case AUDIO:
                cursor = db.query(dbHelper.TABLE_AUDIO, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case IMAGE:
                cursor = db.query(dbHelper.TABLE_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
        */

        // other way if original version doesn't work !!!//
        SQLiteQueryBuilder sb = new SQLiteQueryBuilder();

        Cursor cursor;

        switch (uriMatcher.match(uri)) {
            case MODULES:
                sb.setTables(dbHelper.TABLE_MODULES);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_MODULES, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MODULE_TIME:
                sb.setTables(dbHelper.TABLE_MODULE_TIME);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_MODULE_TIME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case FOLDER:
                sb.setTables(dbHelper.TABLE_FOLDER);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_FOLDER, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SESSION:
                sb.setTables(dbHelper.TABLE_SESSION);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_SESSION, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case AUDIO:
                sb.setTables(dbHelper.TABLE_AUDIO);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_AUDIO, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case IMAGE:
                sb.setTables(dbHelper.TABLE_IMAGE);
                db = dbHelper.getWritableDatabase();
                cursor = db.query(dbHelper.TABLE_IMAGE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int rowsDeleted = 0;

        //older version
        /*
        if(uriMatcher.match(uri) == MODULES) {
            rowsDeleted = db.delete(dbHelper.TABLE_MODULES, selection, selectionArgs);
        }
        */

        switch (uriMatcher.match(uri)) {
            case MODULES:
                rowsDeleted = db.delete(dbHelper.TABLE_MODULES, selection, selectionArgs);
                break;
            case MODULE_TIME:
                rowsDeleted = db.delete(dbHelper.TABLE_MODULE_TIME, selection, selectionArgs);
                break;
            case FOLDER:
                rowsDeleted = db.delete(dbHelper.TABLE_FOLDER, selection, selectionArgs);
                break;
            case SESSION:
                rowsDeleted = db.delete(dbHelper.TABLE_SESSION, selection, selectionArgs);
                break;
            case AUDIO:
                rowsDeleted = db.delete(dbHelper.TABLE_AUDIO, selection, selectionArgs);
                break;
            case IMAGE:
                rowsDeleted = db.delete(dbHelper.TABLE_IMAGE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();

        int rowsUpdated = 0;

        //older version
        /*
        if(uriMatcher.match(uri) == MODULES) {
            rowsUpdated  = db.update(dbHelper.TABLE_MODULES, values, selection, selectionArgs);
        }
        */


        switch (uriMatcher.match(uri)) {
            case MODULES:
                rowsUpdated  = db.update(dbHelper.TABLE_MODULES, values, selection, selectionArgs);
                break;
            case MODULE_TIME:
                rowsUpdated  = db.update(dbHelper.TABLE_MODULE_TIME, values, selection, selectionArgs);
                break;
            case FOLDER:
                rowsUpdated  = db.update(dbHelper.TABLE_FOLDER, values, selection, selectionArgs);
                break;
            case SESSION:
                rowsUpdated  = db.update(dbHelper.TABLE_SESSION, values, selection, selectionArgs);
                break;
            case AUDIO:
                rowsUpdated  = db.update(dbHelper.TABLE_AUDIO, values, selection, selectionArgs);
                break;
            case IMAGE:
                rowsUpdated  = db.update(dbHelper.TABLE_IMAGE, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

}
