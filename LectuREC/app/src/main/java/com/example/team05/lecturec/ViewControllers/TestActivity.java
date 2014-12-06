package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
            modulesView.append("\n");
        }

        //audio filenames
        TextView audiofilenamesView = (TextView)findViewById(R.id.audiofilenamesview);

        Cursor cursorAudio = getAudio();

        while (cursorAudio.moveToNext()) {
            Integer displayAudioId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_ID));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioId.toString());
            audiofilenamesView.append(displayAudioId.toString());

            String displayAudioFilename = cursorAudio.getString(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_NAME));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioFilename);
            audiofilenamesView.append(displayAudioFilename);

            Integer displayAudioSessionId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioSessionId.toString());
            audiofilenamesView.append(displayAudioSessionId.toString());
            audiofilenamesView.append("\n");
        }


        //sessions
        TextView sessionsView = (TextView)findViewById(R.id.sessionview);

        Cursor cursorSession = getSessions();

        while (cursorSession.moveToNext()) {
            Integer displaySessionId = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_ID));
            sessionsView.append(" ");
            System.out.println(displaySessionId.toString());
            sessionsView.append(displaySessionId.toString());

            String displaySessionName = cursorSession.getString(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_NAME));
            sessionsView.append(" ");
            System.out.println(displaySessionName);
            sessionsView.append(displaySessionName);

            Integer displaySessionModule = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN));
            sessionsView.append(" ");
            System.out.println(displaySessionModule.toString());
            sessionsView.append(displaySessionModule.toString());

            Integer displaySessionFolder = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN));
            sessionsView.append(" ");
            System.out.println(displaySessionFolder.toString());
            sessionsView.append(displaySessionFolder.toString());
            sessionsView.append("\n");
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

        //module_times
        TextView moduletimesView = (TextView)findViewById(R.id.moduletimeview);

        Cursor cursorModuleTime = getModuleTimes();

        while (cursorModuleTime.moveToNext()) {
            Integer displayModuleTimeId = cursorModuleTime.getInt(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_ID));
            foldersView.append(" ");
            foldersView.append(displayModuleTimeId.toString());

            String displayDay = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_DAY));
            foldersView.append(" ");
            foldersView.append(displayDay);

            String displayStartTime = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_START_TIME));
            foldersView.append(" ");
            foldersView.append(displayStartTime);

            String displayEndTime = cursorModuleTime.getString(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_END_TIME));
            foldersView.append(" ");
            foldersView.append(displayEndTime);

            Integer displayModuleTimeModule = cursorModuleTime.getInt(cursorModuleTime.getColumnIndex(dbHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN));
            foldersView.append(" ");
            foldersView.append(displayModuleTimeModule.toString());
            foldersView.append("\n");
        }


    }


    DBHelper dbHelper;

    private Cursor getModules() {
        // Run query
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID,
                dbHelper.COLUMN_MODULE_NAME
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

    }

    public void addModule(View view) {

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_MODULE_NAME, ((EditText)findViewById(R.id.add_module_edittext)).getText().toString() );

        Uri uri = getContentResolver().insert(DBProvider.MODULE_URI, values);
        refresh(view);
    }

    public void refresh(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private Cursor getAudio() {
        // Run query
        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_AUDIO_ID,
                dbHelper.COLUMN_AUDIO_NAME,
                dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN
        };
        String selection = null;                //according to session_id = ? !!!!!!!
        String[] selectionArgs = null;          //according to session_id variable !!!!!!!
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void saveAudio(View view) {

        Integer audio_session_id = 1;

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_AUDIO_NAME, ((EditText)findViewById(R.id.add_audio_filename)).getText().toString() );
        values.put(DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN, audio_session_id );

        Uri uri = getContentResolver().insert(DBProvider.AUDIO_URI, values);
        retrieveAudioFilenames(view);
    }

    public void retrieveAudioFilenames(View view) {
        Intent intent_audio = getIntent();
        finish();
        startActivity(intent_audio);
    }

    public void newSession(View view) {

        ContentValues values = new ContentValues();
        String session_name = "audio-2014_12_05-22_30_12"; //YYYY-MM-DD HH:MM:SS or can just use 'now' format!!!
        Integer module = 4;
        Integer folder = 3;

        values.put(DBHelper.COLUMN_SESSION_NAME, session_name );
        values.put(DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN, module);
        values.put(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, folder);

        Uri uri = getContentResolver().insert(DBProvider.SESSION_URI, values);
        retrieveSessions(view);
    }

    private Cursor getSessions(){
        // Run query
        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                dbHelper.COLUMN_SESSION_NAME,
                dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN,
                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN
        };
        String selection = null;                //according to module_id = ?
        String[] selectionArgs = null;          //according to module_id variable
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void retrieveSessions(View view) {
        Intent intent_session = getIntent();
        finish();
        startActivity(intent_session);
    }

    public void captureImage(View view) {

        ContentValues values = new ContentValues();
        Integer image_session_id = 1;
        String image_name = "image-2014_12_06-01_14_22";

        values.put(DBHelper.COLUMN_IMAGE_FILE, image_name );
        values.put(DBHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN, image_session_id );

        Uri uri = getContentResolver().insert(DBProvider.IMAGE_URI, values);
        retrieveImages(view);
    }

    private Cursor getImages() {
        // Run query
        Uri uri = DBProvider.IMAGE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_IMAGE_ID,
                dbHelper.COLUMN_IMAGE_FILE,
                dbHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN
        };
        String selection = null;                //according to session_id = ?
        String[] selectionArgs = null;          //according to session_id variable
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void retrieveImages(View view) {
        Intent intent_image = getIntent();
        finish();
        startActivity(intent_image);
    }

    public void createFolder(View view) {
        ContentValues values = new ContentValues();
        Integer folder_module_id = 2;

        values.put(DBHelper.COLUMN_FOLDER_NAME, ((EditText)findViewById(R.id.create_folder_edittext)).getText().toString() );
        values.put(DBHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN, folder_module_id );

        Uri uri = getContentResolver().insert(DBProvider.FOLDER_URI, values);
        retrieveFolders(view);
    }

    private Cursor getFolders() {
        // Run query
        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_FOLDER_ID,
                dbHelper.COLUMN_FOLDER_NAME,
                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN
        };
        String selection = null;                //according to module_id = ?
        String[] selectionArgs = null;          //according to module_id variable
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void retrieveFolders(View view) {
        Intent intent_folder = getIntent();
        finish();
        startActivity(intent_folder);
    }

    public void addModuleTime(View view) {
        ContentValues values = new ContentValues();
        Integer module_time_module_id = 3;
        Integer notification = 1;
        Integer day = 2;
        String start_time = "11:00:00"; //HH:MM:SS
        String end_time = "13:00:00"; //HH:MM:SS

        values.put(DBHelper.COLUMN_MODULE_TIME_DAY, day );
        values.put(DBHelper.COLUMN_MODULE_TIME_START_TIME, start_time );
        values.put(DBHelper.COLUMN_MODULE_TIME_END_TIME, end_time );
        values.put(DBHelper.COLUMN_MODULE_TIME_NOTIFICATION, notification );
        values.put(DBHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN, module_time_module_id );

        Uri uri = getContentResolver().insert(DBProvider.MODULE_TIME_URI, values);
        retrieveModuleTimes(view);
    }

    private Cursor getModuleTimes() {
        // Run query
        Uri uri = DBProvider.MODULE_TIME_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_TIME_ID,
                dbHelper.COLUMN_MODULE_TIME_DAY,
                dbHelper.COLUMN_MODULE_TIME_START_TIME,
                dbHelper.COLUMN_MODULE_TIME_END_TIME,
                dbHelper.COLUMN_MODULE_TIME_NOTIFICATION,
                dbHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN
        };
        String selection = null;                //according to module_id = ?
        String[] selectionArgs = null;          //according to module_id variable
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void retrieveModuleTimes(View view) {
        Intent intent_module_time = getIntent();
        finish();
        startActivity(intent_module_time);
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
