package com.example.team05.lecturec.Controllers;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.ModelControllers.*;

import java.util.ArrayList;

/**
 * Created by Johnbastian on 10/12/2014.
 */
public class DataManager {

    private static DBProvider dbProvider = new DBProvider();

    private static Time convertTimeStringToObject(String timeString){

        String[] timeArray = timeString.split(":");

        int hours = Integer.parseInt(timeArray[0]);
        int min = Integer.parseInt(timeArray[1]);
        int sec = Integer.parseInt(timeArray[2]);

        Time time = new Time(hours, min, sec);

        return time;

    }

    //All getters
    public static ArrayList<Module> getCurrentModules(Context context){

        ArrayList<Module> currentModules = new ArrayList<Module>();

        Uri uri = DBProvider.MODULE_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_MODULE_ID,
                DBHelper.COLUMN_MODULE_NAME,
                DBHelper.COLUMN_MODULE_ARCHIVE  };

        String selection = DBHelper.COLUMN_MODULE_ARCHIVE + " = ?";
        String[] selectionArgs = new String[]{"0"};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int moduleId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_ID));

            String moduleName = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_NAME));

            boolean moduleArchive = (queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_ARCHIVE)) != 0);

            Module currentModule = new Module(moduleId, moduleName);

            ArrayList<ModuleTime> currentModuleTimes = getModuleTimes(context, currentModule.getID());
            currentModule.setModuleTimes(currentModuleTimes);

            ArrayList<Session> currentModuleSessions = getSessions(context, currentModule.getID());
            currentModule.setSessions(currentModuleSessions);

            ArrayList<Folder> currentModuleFolders = getFolders(context, currentModule.getID());
            currentModule.setFolders(currentModuleFolders);

            currentModules.add(currentModule);

        }

        queryCursor.close();

        return currentModules;

    }

    public static int getLastModuleRecord(Context context){

        int lastModuleID = -1;

        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    DBHelper.COLUMN_MODULE_ID   };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = DBHelper.COLUMN_MODULE_ID + " DESC";

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if(queryCursor.getCount() > 0){

            queryCursor.moveToFirst();

            lastModuleID = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_ID));

        }

        return lastModuleID;

    }

    public static ArrayList<Module> getArchivedModules(Context context){

        ArrayList<Module> archivedModules = new ArrayList<Module>();

        Uri uri = DBProvider.MODULE_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_MODULE_ID,
                DBHelper.COLUMN_MODULE_NAME,
                DBHelper.COLUMN_MODULE_ARCHIVE  };

        String selection = DBHelper.COLUMN_MODULE_ARCHIVE + " = ?";
        String[] selectionArgs = new String[]{"1"};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int moduleId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_ID));

            String moduleName = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_NAME));

            Module archiveModule = new Module(moduleId, moduleName);
            archiveModule.setArchiveState(true);

            ArrayList<ModuleTime> currentModuleTimes = getModuleTimes(context, archiveModule.getID());
            archiveModule.setModuleTimes(currentModuleTimes);

            ArrayList<Session> currentModuleSessions = getSessions(context, archiveModule.getID());
            archiveModule.setSessions(currentModuleSessions);

            ArrayList<Folder> currentModuleFolders = getFolders(context, archiveModule.getID());
            archiveModule.setFolders(currentModuleFolders);

            archivedModules.add(archiveModule);

        }

        return archivedModules;

    }

    public static ArrayList<ModuleTime> getModuleTimes(Context context, int moduleID){

        ArrayList<ModuleTime> moduleTimes = new ArrayList<ModuleTime>();

        Uri uri = DBProvider.MODULE_TIME_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_MODULE_TIME_ID,
                DBHelper.COLUMN_MODULE_TIME_DAY,
                DBHelper.COLUMN_MODULE_TIME_START_TIME,
                DBHelper.COLUMN_MODULE_TIME_END_TIME,
                DBHelper.COLUMN_MODULE_TIME_NOTIFICATION,
                DBHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN    };

        String selection =  DBHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleID)};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int moduleTimeId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_TIME_ID));

            int day = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_TIME_DAY));

            String stringStartTime = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_TIME_START_TIME));
            Time startTime = convertTimeStringToObject(stringStartTime);

            String stringEndTime = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_TIME_END_TIME));
            Time endTime = convertTimeStringToObject(stringEndTime);

            boolean notificationStatus = (queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_MODULE_TIME_NOTIFICATION)) != 0);

            ModuleTime currentModuleTime = new ModuleTime(moduleTimeId, day, startTime, endTime, notificationStatus);

            moduleTimes.add(currentModuleTime);

        }

        return moduleTimes;

    }

    public static ArrayList<Session> getSessions(Context context, int moduleID){

        ArrayList<Session> sessions = new ArrayList<Session>();

        Uri uri = DBProvider.SESSION_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_SESSION_ID,
                DBHelper.COLUMN_SESSION_NAME,
                DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN,
                DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN   };

        String selection = DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleID)};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int sessionId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_SESSION_ID));

            String sessionName = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_SESSION_NAME));

            int sessionFolder = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN));

            Session currentModuleSession = new Session(sessionId, sessionName, sessionFolder);

            sessions.add(currentModuleSession);

        }

        queryCursor.close();

        return sessions;

    }

    public static int getLastSessionRecord(Context context){

        int lastSessionID = -1;

        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    DBHelper.COLUMN_SESSION_ID  };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = DBHelper.COLUMN_SESSION_ID + " DESC";

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (queryCursor.getCount() > 0){

            queryCursor.moveToFirst();

            lastSessionID = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_SESSION_ID));

        }

        return lastSessionID;

    }

    //TODO finish getter for getFolders
    public static ArrayList<Folder> getFolders(Context context, int moduleID){

        ArrayList<Folder> folders = new ArrayList<Folder>();

        Uri uri = DBProvider.FOLDER_URI;
        String[] projection = new String[] {
                DBHelper.COLUMN_FOLDER_ID,
                DBHelper.COLUMN_FOLDER_NAME,
                DBHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN  };
        String selection =  DBHelper.COLUMN_FOLDER_MODULE_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(moduleID)};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()){

            int folderId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_FOLDER_ID));

            String folderName = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_FOLDER_NAME));

            Folder currentFolder = new Folder(folderId, folderName);

            folders.add(currentFolder);

        }

        return folders;

    }

    public static ArrayList<Audio> getAudios(Context context, int sessionID){

        ArrayList<Audio> audios = new ArrayList<Audio>();

        Uri uri = DBProvider.AUDIO_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_AUDIO_ID,
                DBHelper.COLUMN_AUDIO_FILE,
                DBHelper.COLUMN_AUDIO_START_TIME,
                DBHelper.COLUMN_AUDIO_DURATION,
                DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN    };

        String selection = DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(sessionID)};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int audioId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_AUDIO_ID));

            String audioFilename = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_AUDIO_FILE));

            String audioStartTimeString = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_AUDIO_START_TIME));
            Time startTime = convertTimeStringToObject(audioStartTimeString);

            int audioDuration = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_AUDIO_DURATION));

            Audio sessionAudio = new Audio(audioId, audioFilename, startTime, audioDuration);

            audios.add(sessionAudio);

        }

        return audios;

    }

    public static ArrayList<Image> getImages(Context context, int sessionID){

        ArrayList<Image> images = new ArrayList<Image>();

        Uri uri = DBProvider.IMAGE_URI;

        String[] projection = new String[] {
                DBHelper.COLUMN_IMAGE_ID,
                DBHelper.COLUMN_IMAGE_FILE,
                DBHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN    };

        String selection = DBHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN + " = ? ";
        String[] selectionArgs = new String[]{Integer.toString(sessionID)};
        String sortOrder = null;

        Cursor queryCursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        while (queryCursor.moveToNext()) {

            int imageId = queryCursor.getInt(queryCursor.getColumnIndex(DBHelper.COLUMN_IMAGE_ID));

            String imageFile = queryCursor.getString(queryCursor.getColumnIndex(DBHelper.COLUMN_IMAGE_FILE));

            Image sessionImage = new Image(imageId, imageFile);

            images.add(sessionImage);

        }

        return images;

    }




    //Module Editors
    public static void addNewModule(Context context, Module newModule, ArrayList<ModuleTime> newModuleTimes){

        System.out.println("Save NEW pressed");

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_MODULE_NAME,  newModule.getName());
        values.put(DBHelper.COLUMN_MODULE_ARCHIVE, 0);

        Uri uri = context.getContentResolver().insert(DBProvider.MODULE_URI, values);

        int addedID = getLastModuleRecord(context);

        if (addedID > 0) for (ModuleTime mt:newModuleTimes) addNewModuleTime(context, addedID, mt);


    }

    public static void editExistingModule(Context context, Module currentModule, boolean nameChange, ArrayList<ModuleTime> newModuleTimes,
                                   ArrayList<ModuleTime> editingModuleTimes, ArrayList<ModuleTime> deletingModuleTimes){


        System.out.println("Save EDIT pressed \n");

        System.out.println(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n \n",
                        currentModule.getID(),
                        currentModule.getName(),
                        currentModule.getArchiveState()));

        System.out.println("Name Change State is: " + nameChange + "");

        //TODO finish this for testing and introduce Prash's work

    }

    public static void archiveExistingModule(Context context, Module currentModule){

        System.out.println("Archive pressed");

        System.out.print(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n",
                        currentModule.getID(),
                        currentModule.getName(),
                        currentModule.getArchiveState()));

    }

    public static void deletingExistingModule(Context context, Module deletingModule){

        System.out.println("Delete pressed");

        System.out.print(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n",
                        deletingModule.getID(),
                        deletingModule.getName(),
                        deletingModule.getArchiveState()));

    }


    //ModuleTime Editors
    public static void addNewModuleTime(Context context, int moduleID, ModuleTime newModuleTime){

        ContentValues values = new ContentValues();

        int day = newModuleTime.getDay();

        String startTime = newModuleTime.getStart().convertToString();
        String endTime = newModuleTime.getEnd().convertToString();

        int notifyVal = 1;
        if (!newModuleTime.getNotificationState())  notifyVal = 0;

        values.put(DBHelper.COLUMN_MODULE_TIME_DAY, day );
        values.put(DBHelper.COLUMN_MODULE_TIME_START_TIME, startTime);
        values.put(DBHelper.COLUMN_MODULE_TIME_END_TIME, endTime );
        values.put(DBHelper.COLUMN_MODULE_TIME_NOTIFICATION, notifyVal );
        values.put(DBHelper.COLUMN_MODULE_TIME_MODULE_ID_FOREIGN, moduleID );

        Uri uri = context.getContentResolver().insert(DBProvider.MODULE_TIME_URI, values);

    }



    //Session Editors
    public static void addNewSession(Context context, int moduleID, Session newSession, ArrayList<Audio> newAudios, ArrayList<Image> newImages){

        System.out.println("newSession module ID is: " + moduleID);
        System.out.println("The new session: " + newSession.getName());

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_SESSION_NAME, newSession.getName());
        values.put(DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN, moduleID);
        values.putNull(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN);

        Uri uri = context.getContentResolver().insert(DBProvider.SESSION_URI, values);

        int addedID = getLastSessionRecord(context);

        if (addedID > 0) for (Audio audio:newAudios) addNewAudio(context, addedID, audio);

        if (addedID > 0) for (Image image:newImages) addNewImage(context, addedID, image);

    }


    //Audio Editors
    public static void addNewAudio(Context context, int sessionID, Audio newAudio){

        ContentValues values = new ContentValues();

        String audioFilename = newAudio.getFile();
        String startTimeString = newAudio.getStartTime().convertToString();
        int audioDuration = newAudio.getDuration();

        values.put(DBHelper.COLUMN_AUDIO_FILE, audioFilename );
        values.put(DBHelper.COLUMN_AUDIO_DURATION, audioDuration );
        values.put(DBHelper.COLUMN_AUDIO_START_TIME, startTimeString );
        values.put(DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN, sessionID );

        Uri uri = context.getContentResolver().insert(DBProvider.AUDIO_URI, values);

    }


    //Image Editors
    public static void addNewImage(Context context, int sessionID, Image newImage){

        ContentValues values = new ContentValues();
        String imageName = newImage.getFile();

        values.put(DBHelper.COLUMN_IMAGE_FILE, imageName );
        values.put(DBHelper.COLUMN_IMAGE_SESSION_ID_FOREIGN, sessionID);


        Uri uri = context.getContentResolver().insert(DBProvider.IMAGE_URI, values);


    }



}
