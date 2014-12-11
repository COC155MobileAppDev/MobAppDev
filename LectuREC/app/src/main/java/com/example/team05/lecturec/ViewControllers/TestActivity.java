package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team05.lecturec.ModelControllers.DBHelper;
import com.example.team05.lecturec.ModelControllers.DBProvider;

import com.example.team05.lecturec.R;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //modules
        TextView modulesView = (TextView)findViewById(R.id.modulesview);

        Cursor cursorModule = getModules();

        while (cursorModule.moveToNext()) {
            Integer displaymoduleId = cursorModule.getInt(cursorModule.getColumnIndex(dbHelper.COLUMN_MODULE_ID));
            modulesView.append(" ");
            System.out.println(displaymoduleId.toString());
            modulesView.append(displaymoduleId.toString());

            String displayModule = cursorModule.getString(cursorModule.getColumnIndex(dbHelper.COLUMN_MODULE_NAME));
            modulesView.append(" ");
            modulesView.append(displayModule);

            Integer displayModuleArchive = cursorModule.getInt(cursorModule.getColumnIndex(dbHelper.COLUMN_MODULE_ARCHIVE));
            modulesView.append(" ");
            modulesView.append(displayModuleArchive.toString());
            modulesView.append("\n");
        }


        //module_times
        TextView moduletimesView = (TextView)findViewById(R.id.moduletimeview);

        Cursor cursorModuleTime = getModuleTimes();

        while (cursorModuleTime.moveToNext()) {
            Integer displayModuleTimeId = cursorModuleTime.getInt(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));
            moduletimesView.append(" ");
            moduletimesView.append(displayModuleTimeId.toString());

            /*
            String displayDay = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_DAY));
            moduletimesView.append(" ");
            moduletimesView.append(displayDay);
            */

            String displayStartTime = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_START_TIME));
            moduletimesView.append(" ");
            moduletimesView.append(displayStartTime);

            String displayEndTime = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_END_TIME));
            moduletimesView.append(" ");
            moduletimesView.append(displayEndTime);

            Integer displayNotificationStatus = cursorModuleTime.getInt(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION));
            moduletimesView.append(" ");
            moduletimesView.append(displayNotificationStatus.toString());

            Integer displayModuleTimeModuleId = cursorModuleTime.getInt(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN));
            moduletimesView.append(" ");
            moduletimesView.append(displayModuleTimeModuleId.toString());

            moduletimesView.append("\n");
        }


        //sessions
        TextView sessionsView = (TextView)findViewById(R.id.sessionview);

        Cursor cursorSession = getSessions();

        while (cursorSession.moveToNext()) {
            Integer displaySessionId = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_ID));
            sessionsView.append(" ");
            sessionsView.append(displaySessionId.toString());

            String displaySessionName = cursorSession.getString(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_NAME));
            sessionsView.append(" ");
            sessionsView.append(displaySessionName);


            Integer displaySessionModule = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN));
            sessionsView.append(" ");
            sessionsView.append(displaySessionModule.toString());


            Integer displaySessionFolder = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN));
            sessionsView.append(" ");
            sessionsView.append(displaySessionFolder.toString());


            sessionsView.append("\n");
        }


        //audio filenames
        TextView audiofilenamesView = (TextView)findViewById(R.id.audiofilenamesview);

        Cursor cursorAudio = getAudio();

        while (cursorAudio.moveToNext()) {
            Integer displayAudioId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_ID));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioId.toString());
            audiofilenamesView.append(displayAudioId.toString());

            String displayAudioFilename = cursorAudio.getString(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_FILE));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioFilename);
            audiofilenamesView.append(displayAudioFilename);

            Integer displayAudioSessionId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioSessionId.toString());
            audiofilenamesView.append(displayAudioSessionId.toString());

            audiofilenamesView.append("\n");
        }


        //images
        TextView imagesView = (TextView)findViewById(R.id.imageview);

        Cursor cursorImage = getImages();

        while (cursorImage.moveToNext()) {
            Integer displayImageId = cursorImage.getInt(cursorImage.getColumnIndex(dbHelper.COLUMN_IMAGE_ID));
            imagesView.append(" ");
            imagesView.append(displayImageId.toString());

            String displayImageFile = cursorImage.getString(cursorImage.getColumnIndex(dbHelper.COLUMN_IMAGE_FILE));
            imagesView.append(" ");
            imagesView.append(displayImageFile);


            Integer displayImageSession = cursorImage.getInt(cursorImage.getColumnIndex(dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN));
            imagesView.append(" ");
            imagesView.append(displayImageSession.toString());


            imagesView.append("\n");
        }


        //folders
        TextView foldersView = (TextView)findViewById(R.id.foldersview);

        Cursor cursorFolder = getFolders();

        while (cursorFolder.moveToNext()) {
            Integer displayFolderId = cursorFolder.getInt(cursorFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));
            foldersView.append(" ");
            foldersView.append(displayFolderId.toString());

            String displayFolderName = cursorFolder.getString(cursorFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_NAME));
            foldersView.append(" ");
            foldersView.append(displayFolderName);

            Integer displayFolderModule = cursorFolder.getInt(cursorFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN));
            foldersView.append(" ");
            foldersView.append(displayFolderModule.toString());


            foldersView.append("\n");
        }

    }


    DBHelper dbHelper;

    //MODULES
    private Cursor getModules() {

        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID,
                                                dbHelper.COLUMN_MODULE_NAME,
                                                dbHelper.COLUMN_MODULE_ARCHIVE  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getModulesById(Integer moduleId) {

        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID,
                                                dbHelper.COLUMN_MODULE_NAME,
                                                dbHelper.COLUMN_MODULE_ARCHIVE  };
        String selection = dbHelper.COLUMN_MODULE_ID + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonModuleById(View view) {
        //modules by module id
        TextView modulesByIdView = (TextView)findViewById(R.id.modulesbyidview);
        modulesByIdView.setText("");

        //Cursor cursorModuleById = getModulesById(2);
        Cursor cursorModuleById = getModulesById(Integer.parseInt(((EditText)findViewById(R.id.module_id_edittext)).getText().toString())); //static module_id

        while (cursorModuleById.moveToNext()) {
            Integer displaymoduleId = cursorModuleById.getInt(cursorModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_ID));
            modulesByIdView.append(" ");
            modulesByIdView.append(displaymoduleId.toString());

            String displayModule = cursorModuleById.getString(cursorModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_NAME));
            modulesByIdView.append(" ");
            modulesByIdView.append(displayModule);

            Integer displayModuleArchive = cursorModuleById.getInt(cursorModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_ARCHIVE));
            modulesByIdView.append(" ");
            modulesByIdView.append(displayModuleArchive.toString());
            modulesByIdView.append("\n");
        }
    }

    public void addModule(View view) {

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_MODULE_NAME, ((EditText)findViewById(R.id.add_module_edittext)).getText().toString() );
        values.put(DBHelper.COLUMN_MODULE_ARCHIVE, 0 );

        Uri uri = getContentResolver().insert(DBProvider.MODULE_URI, values);
        retrieveModules(view);
    }

    public void deleteButtonModule(View view) {
        deleteModule(0);
        retrieveModules(view);
    }

    public void deleteModule(Integer deleteId) {

        Cursor cursorModuleDelete;

        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID   };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_MODULE_ID + " DESC";

        cursorModuleDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorModuleDelete.getCount() != 0) {

            cursorModuleDelete.moveToFirst();

            Integer lastModuleid = cursorModuleDelete.getInt(cursorModuleDelete.getColumnIndex(dbHelper.COLUMN_MODULE_ID));

            deleteModuleWithModuleTimes(lastModuleid);

            String selection_2 = DBHelper.COLUMN_MODULE_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastModuleid)};

            int rowDeleteModule = getContentResolver().delete(DBProvider.MODULE_URI, selection_2, selectionArgs_2);

        }
    }

    public void deleteModuleWithModuleTimes(Integer moduleId) {

        Cursor cursorModuleWithModuleTimes;

        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID   };
        String selection = dbHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;

        cursorModuleWithModuleTimes = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (cursorModuleWithModuleTimes.moveToNext()) {

            Integer eachModuleTimeid = cursorModuleWithModuleTimes.getInt(cursorModuleWithModuleTimes.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));

            String selection_2 = DBHelper.COLUMN_MODULE_TIME_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(eachModuleTimeid)};

            int rowDeleteEachModuleTime = getContentResolver().delete(DBProvider.MODULE_TIME_URI, selection_2, selectionArgs_2);
        }

    }

    public void editButtonModule(View view) {
        editModule("E", 0, 1); //these value are NOT used
        retrieveModules(view);
    }

    public void editModule(String module, Integer aState, Integer moduleId) {

        Cursor cursorEditModule;

        //using in real app
        /*
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID  };
        String selection = dbHelper.COLUMN_MODULE_ID + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;
        */

        //using my dummy data
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID,
                                                dbHelper.COLUMN_MODULE_NAME,
                                                dbHelper.COLUMN_MODULE_ARCHIVE  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_MODULE_ID + " DESC";

        cursorEditModule = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorEditModule.getCount() != 0) {

            cursorEditModule.moveToFirst();

            Integer editModuleid = cursorEditModule.getInt(cursorEditModule.getColumnIndex(dbHelper.COLUMN_MODULE_ID));

            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_MODULE_NAME, ((EditText)findViewById(R.id.edit_module_edittext)).getText().toString() ); // module parameter
            values.put(dbHelper.COLUMN_MODULE_ARCHIVE, Integer.parseInt(((EditText)findViewById(R.id.is_archive_edittext)).getText().toString()) ); // module parameter

            String selection_2 = dbHelper.COLUMN_MODULE_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(editModuleid)};

            int rowEditModule = getContentResolver().update(DBProvider.MODULE_URI, values, selection_2, selectionArgs_2);
        }

    }

    public void retrieveModules(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    //moduletimes
    public void addModuleTime(View view) {
        ContentValues values = new ContentValues();
        //Integer module_time_module_id = 3;
        //Integer notification = 1;
        Integer day = 2;
        //String start_time = "11:00:00"; //HH:MM:SS
        //String end_time = "13:00:00"; //HH:MM:SS

        values.put(DBHelper.COLUMN_MODULE_TIME_DAY, day );
        values.put(DBHelper.COLUMN_MODULE_TIME_START_TIME, ((EditText)findViewById(R.id.add_module_time_start_edittext)).getText().toString() );
        values.put(DBHelper.COLUMN_MODULE_TIME_END_TIME, ((EditText)findViewById(R.id.add_module_time_end_edittext)).getText().toString() );
        values.put(DBHelper.COLUMN_MODULE_TIME_NOTIFICATION, Integer.parseInt(((EditText)findViewById(R.id.add_module_time_notification_edittext)).getText().toString()) );
        values.put(DBHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_module_time_module_id_edittext)).getText().toString()) );

        Uri uri = getContentResolver().insert(DBProvider.MODULE_TIME_URI, values);
        retrieveModuleTimes(view);
    }

    private Cursor getModuleTimes() {

        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID,
                                                dbHelper.COLUMN_MODULE_TIME_DAY,
                                                dbHelper.COLUMN_MODULE_TIME_START_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_END_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_NOTIFICATION,
                                                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN    };
        String selection = null;                //module_id = ?
        String[] selectionArgs = null;          //module_id
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getModuleTimesByModuleId(Integer moduleId) {

        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID,
                                                dbHelper.COLUMN_MODULE_TIME_DAY,
                                                dbHelper.COLUMN_MODULE_TIME_START_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_END_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_NOTIFICATION,
                                                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN    };
        String selection =  dbHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonModuleTimeModuleById(View view) {
        //module times by module id
        TextView moduleTimeModulesByIdView = (TextView)findViewById(R.id.moduletimemodulebyidview);
        moduleTimeModulesByIdView.setText("");

        Cursor cursorModuleTimeModuleById = getModuleTimesByModuleId(Integer.parseInt(((EditText)findViewById(R.id.module_time_module_id_edittext)).getText().toString())); //static module_id

        while (cursorModuleTimeModuleById.moveToNext()) {
            Integer displayModuleTimeModuleId = cursorModuleTimeModuleById.getInt(cursorModuleTimeModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));
            moduleTimeModulesByIdView.append(" ");
            moduleTimeModulesByIdView.append(displayModuleTimeModuleId.toString());

            String displayModuleTimeStartTime = cursorModuleTimeModuleById.getString(cursorModuleTimeModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_START_TIME));
            moduleTimeModulesByIdView.append(" ");
            moduleTimeModulesByIdView.append(displayModuleTimeStartTime);

            String displayModuleTimeEndTime = cursorModuleTimeModuleById.getString(cursorModuleTimeModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_END_TIME));
            moduleTimeModulesByIdView.append(" ");
            moduleTimeModulesByIdView.append(displayModuleTimeEndTime);

            Integer displayNotificationStatus = cursorModuleTimeModuleById.getInt(cursorModuleTimeModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION));
            moduleTimeModulesByIdView.append(" ");
            moduleTimeModulesByIdView.append(displayNotificationStatus.toString());

            Integer displayModuleTimeByModuleId = cursorModuleTimeModuleById.getInt(cursorModuleTimeModuleById.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN));
            moduleTimeModulesByIdView.append(" ");
            moduleTimeModulesByIdView.append(displayModuleTimeByModuleId.toString());

            moduleTimeModulesByIdView.append("\n");
        }
    }

    public void deleteButtonModuleTime(View view) {
        deleteModuleTime(0);
        retrieveModuleTimes(view);
    }

    public void deleteModuleTime(Integer deleteId) {

        Cursor cursorModuleTimeDelete;

        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_MODULE_TIME_ID + " DESC";

        cursorModuleTimeDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorModuleTimeDelete.getCount() != 0) {

            cursorModuleTimeDelete.moveToFirst();

            Integer lastModuleTimeid = cursorModuleTimeDelete.getInt(cursorModuleTimeDelete.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));

            String selection_2 = DBHelper.COLUMN_MODULE_TIME_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastModuleTimeid)};

            int rowDeleteModuleTime = getContentResolver().delete(DBProvider.MODULE_TIME_URI, selection_2, selectionArgs_2);

        }
    }

    public void editButtonModuleTime(View view) {
        editModuleTime(2, "15:00", "16:00", 0);
        retrieveModuleTimes(view);
    }

    public void editModuleTime(Integer moduleTimeId, String sTime, String eTime, Integer notification) {

        Cursor cursorEditModuleTime;

        //using in real app and change the fields accordingly to the table
        /*
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID  };
        String selection = dbHelper.COLUMN_MODULE_ID + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;
        */

        //using my dummy data
        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID,
                                                dbHelper.COLUMN_MODULE_TIME_START_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_END_TIME,
                                                dbHelper.COLUMN_MODULE_TIME_NOTIFICATION    };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_MODULE_TIME_ID + " DESC";

        cursorEditModuleTime = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorEditModuleTime.getCount() != 0) {

            cursorEditModuleTime.moveToFirst();

            Integer editModuleTimeid = cursorEditModuleTime.getInt(cursorEditModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));

            String startEditText = ((EditText)findViewById(R.id.edit_module_time_start_edittext)).getText().toString();
            String endEditText = ((EditText)findViewById(R.id.edit_module_time_end_edittext)).getText().toString();
            Integer notificationText = Integer.parseInt(((EditText)findViewById(R.id.edit_module_time_notification_edittext)).getText().toString() );

            ContentValues values = new ContentValues();

            if ((startEditText.matches("")) && (endEditText.matches("")) && (notificationText == 0)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 0 );
            } else
            if ((startEditText.matches("")) && (endEditText.matches("")) && (notificationText == 1)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 0 );
            } else
            if (!(startEditText.matches("")) && (endEditText.matches("")) && (notificationText == 0)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_START_TIME, startEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 0 );
            } else
            if ((startEditText.matches("")) && !(endEditText.matches("")) && (notificationText == 0)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_END_TIME, endEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 0 );
            } else
            if (!(startEditText.matches("")) && (endEditText.matches("")) && (notificationText == 1)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_START_TIME, startEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 1 );
            } else
            if ((startEditText.matches("")) && !(endEditText.matches("")) && (notificationText == 1)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_END_TIME, endEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 1 );
            } else
            if (!(startEditText.matches("")) && !(endEditText.matches("")) && (notificationText == 0)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_START_TIME, startEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_END_TIME, endEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 0 );
            } else
            if (!(startEditText.matches("")) && !(endEditText.matches("")) && (notificationText == 1)) {
                values.put(dbHelper.COLUMN_MODULE_TIME_START_TIME, startEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_END_TIME, endEditText );
                values.put(dbHelper.COLUMN_MODULE_TIME_NOTIFICATION, 1 );
            }

            String selection_2 = dbHelper.COLUMN_MODULE_TIME_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(editModuleTimeid)};

            int rowEditModuleTime = getContentResolver().update(DBProvider.MODULE_TIME_URI, values, selection_2, selectionArgs_2);
        }

    }

    public void retrieveModuleTimes(View view) {
        Intent intent_module_time = getIntent();
        finish();
        startActivity(intent_module_time);
    }


    //session
    public void newSession(View view) {

        ContentValues values = new ContentValues();
        String session_name = "session-2014_12_05-22_30_12"; //YYYY-MM-DD HH:MM:SS or can just use 'now' format!!!
        //Integer module = 4;
        //Integer folder = 3;

        values.put(DBHelper.COLUMN_SESSION_NAME, session_name );
        values.put(DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_session_module_id)).getText().toString()));

        if (((EditText)findViewById(R.id.add_session_folder_id)).getText().toString().matches("")) {
            values.putNull(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN);
        } else {
            values.put(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_session_folder_id)).getText().toString()));
        }

        //values.put(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_session_folder_id)).getText().toString()));
        //values.putNull(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN); //take null values

        Uri uri = getContentResolver().insert(DBProvider.SESSION_URI, values);
        retrieveSessions(view);
    }

    private Cursor getSessions(){
        // Run query
        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                dbHelper.COLUMN_SESSION_NAME,
                dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN,
                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN   };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getSessionsByModuleId(Integer moduleId) {
        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                dbHelper.COLUMN_SESSION_NAME,
                dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN,
                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN   };
        String selection = dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonSessionByModuleId(View view) {

        TextView sessionbyModuleIdView = (TextView)findViewById(R.id.sessionsmoduleidview);
        sessionbyModuleIdView.setText("");

        Cursor cursorSessionbyModuleId = getSessionsByModuleId(Integer.parseInt(((EditText)findViewById(R.id.get_session_module_id_edittext)).getText().toString()) );

        while (cursorSessionbyModuleId.moveToNext()) {
            Integer displaySessionId = cursorSessionbyModuleId.getInt(cursorSessionbyModuleId.getColumnIndex(dbHelper.COLUMN_SESSION_ID));
            sessionbyModuleIdView.append(" ");
            sessionbyModuleIdView.append(displaySessionId.toString());

            String displaySessionName = cursorSessionbyModuleId.getString(cursorSessionbyModuleId.getColumnIndex(dbHelper.COLUMN_SESSION_NAME));
            sessionbyModuleIdView.append(" ");
            sessionbyModuleIdView.append(displaySessionName);

            Integer displaySessionModuleId = cursorSessionbyModuleId.getInt(cursorSessionbyModuleId.getColumnIndex(dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN));
            sessionbyModuleIdView.append(" ");
            sessionbyModuleIdView.append(displaySessionModuleId.toString());

            Integer displaySessionFolderId = cursorSessionbyModuleId.getInt(cursorSessionbyModuleId.getColumnIndex(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN));
            sessionbyModuleIdView.append(" ");
            sessionbyModuleIdView.append(displaySessionFolderId.toString());

            sessionbyModuleIdView.append("\n");
        }

    }

    public void deleteButtonSession(View view) {
        deleteSession(0);
        retrieveSessions(view);
    }

    public void deleteSession(Integer deleteId) {

        Cursor cursorSessionDelete;

        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_SESSION_ID + " DESC";

        cursorSessionDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorSessionDelete.getCount() != 0) {

            cursorSessionDelete.moveToFirst();

            Integer lastSessionid = cursorSessionDelete.getInt(cursorSessionDelete.getColumnIndex(dbHelper.COLUMN_SESSION_ID));

            String selection_2 = DBHelper.COLUMN_SESSION_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastSessionid)};

            int rowDeleteSession = getContentResolver().delete(DBProvider.SESSION_URI, selection_2, selectionArgs_2);

        }
    }

    public void editButtonSession(View view) {
        editSession(2, 2);
        retrieveSessions(view);
    }

    public void editSession(Integer sessionId, Integer folderId) {

        Cursor cursorEditSession;

        //using in real app and change the fields accordingly to the table
        /*
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID  };
        String selection = dbHelper.COLUMN_MODULE_ID + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;
        */

        //using my dummy data
        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                dbHelper.COLUMN_SESSION_NAME,
                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN   };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_SESSION_ID + " DESC";

        cursorEditSession = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorEditSession.getCount() != 0) {

            cursorEditSession.moveToFirst();

            Integer editSessionid = cursorEditSession.getInt(cursorEditSession.getColumnIndex(dbHelper.COLUMN_SESSION_ID));

            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.edit_session_edittext)).getText().toString()) ); //edittext is folder selection number
            String selection_2 = dbHelper.COLUMN_SESSION_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(editSessionid)};

            int rowEditSession = getContentResolver().update(DBProvider.SESSION_URI, values, selection_2, selectionArgs_2);
        }

    }

    public void retrieveSessions(View view) {
        Intent intent_session = getIntent();
        finish();
        startActivity(intent_session);
    }


    //audio
    private Cursor getAudio() {

        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_AUDIO_ID,
                                                dbHelper.COLUMN_AUDIO_FILE,
                                                dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN    };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getAudioBySessionId(Integer sessionId) {
        // Run query
        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_AUDIO_ID,
                                                dbHelper.COLUMN_AUDIO_FILE,
                                                dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN    };
        String selection = dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(sessionId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonAudioBySessionId(View view) {

        TextView audiofilenamesbySessionIdView = (TextView)findViewById(R.id.audiofilenamesbysessionidview);
        audiofilenamesbySessionIdView.setText("");

        Cursor cursorAudiobySessionId = getAudioBySessionId(Integer.parseInt(((EditText)findViewById(R.id.get_audio_filename_session_id)).getText().toString()) );

        while (cursorAudiobySessionId.moveToNext()) {
            Integer displayAudioId = cursorAudiobySessionId.getInt(cursorAudiobySessionId.getColumnIndex(dbHelper.COLUMN_AUDIO_ID));
            audiofilenamesbySessionIdView.append(" ");
            audiofilenamesbySessionIdView.append(displayAudioId.toString());

            String displayAudioFilename = cursorAudiobySessionId.getString(cursorAudiobySessionId.getColumnIndex(dbHelper.COLUMN_AUDIO_FILE));
            audiofilenamesbySessionIdView.append(" ");
            audiofilenamesbySessionIdView.append(displayAudioFilename);

            Integer displayAudioSessionId = cursorAudiobySessionId.getInt(cursorAudiobySessionId.getColumnIndex(dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN));
            audiofilenamesbySessionIdView.append(" ");
            audiofilenamesbySessionIdView.append(displayAudioSessionId.toString());

            audiofilenamesbySessionIdView.append("\n");
        }

    }

    public void saveAudio(View view) {

        ContentValues values = new ContentValues();
        String audio_filename = "audio-2014_12_05-22_30_12"; //YYYY-MM-DD HH:MM:SS or can just use 'now' format!!!
        //Integer audio_session_id = 1;

        values.put(DBHelper.COLUMN_AUDIO_FILE, audio_filename );
        values.put(DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_audio_filename_session_id)).getText().toString()) );

        Uri uri = getContentResolver().insert(DBProvider.AUDIO_URI, values);
        retrieveAudioFilenames(view);
    }

    public void deleteButtonAudio(View view) {
        deleteAudio(0);
        retrieveAudioFilenames(view);
    }

    public void deleteAudio(Integer deleteId) {

        Cursor cursorAudioDelete;

        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_AUDIO_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_AUDIO_ID + " DESC";

        cursorAudioDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorAudioDelete.getCount() != 0) {

            cursorAudioDelete.moveToFirst();

            Integer lastAudioid = cursorAudioDelete.getInt(cursorAudioDelete.getColumnIndex(dbHelper.COLUMN_AUDIO_ID));

            String selection_2 = DBHelper.COLUMN_AUDIO_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastAudioid)};

            int rowDeleteAudio = getContentResolver().delete(DBProvider.AUDIO_URI, selection_2, selectionArgs_2);

        }
    }

    public void retrieveAudioFilenames(View view) {
        Intent intent_audio = getIntent();
        finish();
        startActivity(intent_audio);
    }


    //image
    public void captureImage(View view) {

        ContentValues values = new ContentValues();
        //Integer image_session_id = 1;
        String image_name = "image-2014_12_06-01_14_22";

        values.put(DBHelper.COLUMN_IMAGE_FILE, image_name );
        values.put(DBHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.add_image_filename_session_id)).getText().toString()) );


        Uri uri = getContentResolver().insert(DBProvider.IMAGE_URI, values);
        retrieveImages(view);
    }

    private Cursor getImages() {
        // Run query
        Uri uri = DBProvider.IMAGE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_IMAGE_ID,
                                                dbHelper.COLUMN_IMAGE_FILE,
                                                dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN    };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getImagesBySessionId(Integer sessionId) {
        Uri uri = DBProvider.IMAGE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_IMAGE_ID,
                                                dbHelper.COLUMN_IMAGE_FILE,
                                                dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN    };
        String selection = dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(sessionId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonImageBySessionId(View view) {

        TextView imagefilebySessionIdView = (TextView)findViewById(R.id.imagefilenamesbysessionidview);
        imagefilebySessionIdView.setText("");

        Cursor cursorImagebySessionId = getImagesBySessionId(Integer.parseInt(((EditText) findViewById(R.id.get_image_filename_session_id)).getText().toString()));

        while (cursorImagebySessionId.moveToNext()) {
            Integer displayImageId = cursorImagebySessionId.getInt(cursorImagebySessionId.getColumnIndex(dbHelper.COLUMN_IMAGE_ID));
            imagefilebySessionIdView.append(" ");
            imagefilebySessionIdView.append(displayImageId.toString());

            String displayImageFilename = cursorImagebySessionId.getString(cursorImagebySessionId.getColumnIndex(dbHelper.COLUMN_IMAGE_FILE));
            imagefilebySessionIdView.append(" ");
            imagefilebySessionIdView.append(displayImageFilename);

            Integer displayImageSessionId = cursorImagebySessionId.getInt(cursorImagebySessionId.getColumnIndex(dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN));
            imagefilebySessionIdView.append(" ");
            imagefilebySessionIdView.append(displayImageSessionId.toString());

            imagefilebySessionIdView.append("\n");
        }

    }

    public void deleteButtonImage(View view) {
        deleteImage(0);
        retrieveImages(view);
    }

    public void deleteImage(Integer deleteId) {

        Cursor cursorImageDelete;

        Uri uri = DBProvider.IMAGE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_IMAGE_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_IMAGE_ID + " DESC";

        cursorImageDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorImageDelete.getCount() != 0) {

            cursorImageDelete.moveToFirst();

            Integer lastImageid = cursorImageDelete.getInt(cursorImageDelete.getColumnIndex(dbHelper.COLUMN_IMAGE_ID));

            String selection_2 = DBHelper.COLUMN_IMAGE_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastImageid)};

            int rowDeleteImage = getContentResolver().delete(DBProvider.IMAGE_URI, selection_2, selectionArgs_2);

        }
    }

    public void retrieveImages(View view) {
        Intent intent_image = getIntent();
        finish();
        startActivity(intent_image);
    }


    //folder
    public void createFolder(View view) {
        ContentValues values = new ContentValues();
        //Integer folder_module_id = 2;

        values.put(DBHelper.COLUMN_FOLDER_NAME, ((EditText)findViewById(R.id.create_folder_edittext)).getText().toString() );
        values.put(DBHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN, Integer.parseInt(((EditText)findViewById(R.id.create_folder_module_id_edittext)).getText().toString()) );

        Uri uri = getContentResolver().insert(DBProvider.FOLDER_URI, values);
        retrieveFolders(view);
    }

    private Cursor getFolders() {
        // Run query
        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID,
                                                dbHelper.COLUMN_FOLDER_NAME,
                                                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN    };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFoldersByModuleId(Integer moduleId) {

        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID,
                                                dbHelper.COLUMN_FOLDER_NAME,
                                                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN  };
        String selection =  dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;

        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void getButtonFolderModuleById(View view) {
        //folders by module id
        TextView foldersModulesByIdView = (TextView)findViewById(R.id.foldersmoduleidview);
        foldersModulesByIdView.setText("");

        Cursor cursorFolderModuleById = getFoldersByModuleId(Integer.parseInt(((EditText)findViewById(R.id.get_folder_module_id_edittext)).getText().toString()) ); //static module_id

        while (cursorFolderModuleById.moveToNext()) {
            Integer displayFolderId = cursorFolderModuleById.getInt(cursorFolderModuleById.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));
            foldersModulesByIdView.append(" ");
            foldersModulesByIdView.append(displayFolderId.toString());

            String displayFolderName = cursorFolderModuleById.getString(cursorFolderModuleById.getColumnIndex(dbHelper.COLUMN_FOLDER_NAME));
            foldersModulesByIdView.append(" ");
            foldersModulesByIdView.append(displayFolderName);

            Integer displayFolderModuleId = cursorFolderModuleById.getInt(cursorFolderModuleById.getColumnIndex(dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN));
            foldersModulesByIdView.append(" ");
            foldersModulesByIdView.append(displayFolderModuleId.toString());

            foldersModulesByIdView.append("\n");
        }
    }

    //dont need if using the deleteButtonFolderWithSessions and deleteButtonFolderWithoutSessions buttons !!!!!!!
    /*
    public void deleteButtonFolder(View view) {
        deleteFolder(0);
        retrieveFolders(view);
    }
    */

    public void deleteButtonFolderWithSessions(View view) {
        deleteFolderWithSessions(2);
        deleteFolder(2);
        retrieveFolders(view);
        retrieveSessions(view);
    }

    public void deleteButtonFolderWithoutSessions(View view) {
        deleteFolderWithoutSessions(3, 1);
        deleteFolder(3);
        retrieveFolders(view);
        retrieveSessions(view);
    }

    public void deleteFolder(Integer deleteId) {

        //testing purposes
        Cursor cursorFolderDelete;

        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_FOLDER_ID + " DESC";

        cursorFolderDelete = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorFolderDelete.getCount() != 0) {

            cursorFolderDelete.moveToFirst();

            Integer lastFolderid = cursorFolderDelete.getInt(cursorFolderDelete.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));

            String selection_2 = DBHelper.COLUMN_FOLDER_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastFolderid)};

            int rowDeleteFolder = getContentResolver().delete(DBProvider.FOLDER_URI, selection_2, selectionArgs_2);
        }

        // using static parameters!!
        /*
        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID  };
        String selection = DBHelper.COLUMN_FOLDER_ID + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(deleteId)};

        int rowDeleteFolder = getContentResolver().delete(DBProvider.FOLDER_URI, selection, selectionArgs);
        */
    }

    public void deleteFolderWithSessions(Integer folderId) {

        //using static paramaters
        /*
        Cursor cursorFolderWithSessions;

        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID  };
        String selection = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(folderId)};
        String sortOrder = null;

        cursorFolderWithSessions = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (cursorFolderWithSessions.moveToNext()) {

            Integer eachSessionid = cursorFolderWithSessions.getInt(cursorFolderWithSessions.getColumnIndex(dbHelper.COLUMN_SESSION_ID));

            String selection_2 = DBHelper.COLUMN_SESSION_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(eachSessionid)};

            int rowDeleteEachSession = getContentResolver().delete(DBProvider.SESSION_URI, selection_2, selectionArgs_2);
        }
        */

        //get last folder
        Cursor cursorLastFolder;

        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_FOLDER_ID + " DESC";

        cursorLastFolder = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        Cursor cursorLastFolderSessions;

        //get all sessions in the last folder
        if (cursorLastFolder.getCount() != 0) {

            cursorLastFolder.moveToFirst();

            Integer lastFolderid = cursorLastFolder.getInt(cursorLastFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));

            Uri uri_2 = DBProvider.SESSION_URI;
            String[] projection_2 = new String[] {   dbHelper.COLUMN_SESSION_ID   };
            String selection_2 = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastFolderid)};
            String sortOrder_2 = null;

            cursorLastFolderSessions = getContentResolver().query(uri_2, projection_2, selection_2, selectionArgs_2, sortOrder_2);

            //delete sessions in last folder
            while (cursorLastFolderSessions.moveToNext()) {

                Integer eachSessionid = cursorLastFolderSessions.getInt(cursorLastFolderSessions.getColumnIndex(dbHelper.COLUMN_SESSION_ID));

                String selection_3 = DBHelper.COLUMN_SESSION_ID + " = ?";
                String[] selectionArgs_3 = new String[]{Integer.toString(eachSessionid)};

                int rowDeleteEachSession = getContentResolver().delete(DBProvider.SESSION_URI, selection_3, selectionArgs_3);
            }

        }

    }


    public void deleteFolderWithoutSessions(Integer oldFolderId, Integer newFolderId) {

        // using static paramaters
        /*
        Cursor cursorFolderWithoutSessions;

        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                                                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN
                                            };
        String selection = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(oldFolderId)};
        String sortOrder = null;

        cursorFolderWithoutSessions = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (cursorFolderWithoutSessions.moveToNext()) {
            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, newFolderId ); //NULL not accepting !!!!! JB.
            String selection_2 = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(oldFolderId)}; //oldFolderId

            int rowEditFolderOfSession = getContentResolver().update(DBProvider.SESSION_URI, values, selection_2, selectionArgs_2);
        }
        */


        //get last folder
        Cursor cursorWithoutSessionLastFolder;

        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_FOLDER_ID + " DESC";

        cursorWithoutSessionLastFolder = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        Cursor cursorWithoutSessionLastFolderSessions;

        //get all sessions in the last folder
        if (cursorWithoutSessionLastFolder.getCount() != 0) {

            cursorWithoutSessionLastFolder.moveToFirst();

            Integer lastFolderid = cursorWithoutSessionLastFolder.getInt(cursorWithoutSessionLastFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));

            Uri uri_2 = DBProvider.SESSION_URI;
            String[] projection_2 = new String[]{dbHelper.COLUMN_SESSION_ID};
            String selection_2 = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(lastFolderid)};
            String sortOrder_2 = null;

            cursorWithoutSessionLastFolderSessions = getContentResolver().query(uri_2, projection_2, selection_2, selectionArgs_2, sortOrder_2);

            //move sessions to different folder(null) before deleting last folder
            while (cursorWithoutSessionLastFolderSessions.moveToNext()) {

                ContentValues values_3 = new ContentValues();
                values_3.putNull(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN);
                String selection_3 = dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN + " = ?";
                String[] selectionArgs_3 = new String[]{Integer.toString(lastFolderid)};

                int rowMoveSessions = getContentResolver().update(DBProvider.SESSION_URI, values_3, selection_3, selectionArgs_3);
            }

        }


    }

    public void editButtonFolder(View view) {
        editFolder(2, "F");
        retrieveFolders(view);
    }

    public void editFolder(Integer folderId, String folderName) {

        Cursor cursorEditFolder;

        //using in real app and change the fields accordingly to the table
        /*
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID  };
        String selection = dbHelper.COLUMN_MODULE_ID + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(moduleId)};
        String sortOrder = null;
        */

        //using my dummy data
        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID,
                                                dbHelper.COLUMN_FOLDER_NAME   };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = dbHelper.COLUMN_FOLDER_ID + " DESC";

        cursorEditFolder = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursorEditFolder.getCount() != 0) {

            cursorEditFolder.moveToFirst();

            Integer editFolderid = cursorEditFolder.getInt(cursorEditFolder.getColumnIndex(dbHelper.COLUMN_FOLDER_ID));

            ContentValues values = new ContentValues();
            values.put(dbHelper.COLUMN_FOLDER_NAME, ((EditText)findViewById(R.id.edit_folder_edittext)).getText().toString() );
            String selection_2 = dbHelper.COLUMN_FOLDER_ID + " = ?";
            String[] selectionArgs_2 = new String[]{Integer.toString(editFolderid)};

            int rowEditFolder = getContentResolver().update(DBProvider.FOLDER_URI, values, selection_2, selectionArgs_2);
        }

    }

    public void retrieveFolders(View view) {
        Intent intent_folder = getIntent();
        finish();
        startActivity(intent_folder);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
