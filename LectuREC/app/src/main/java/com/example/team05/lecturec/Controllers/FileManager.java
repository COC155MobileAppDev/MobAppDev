package com.example.team05.lecturec.Controllers;

import android.content.Context;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Johnbastian on 12/12/2014.
 */
public class FileManager {

    private static final String AUDIO_FOLDER = "/Audios";
    private static final String IMAGE_FOLDER = "/Images";

    //Get New File Name
    public static String getCurrentDateTimeFileName(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        return dateFormat.format(new Date());

    }

    //Get File Directory with Name for Audio
    public static File getAudioFileFormat(Context context, String fieName){

        File audioFile = getFileName(context, AUDIO_FOLDER, "Recording_",  fieName, ".3gp");

        System.out.println("audioFile is: " + audioFile.getAbsolutePath());

        return audioFile;

    }

    //Get File Directory with Name for Image
    public static File getImageFileFormat(Context context,  String fieName){

        File imageFile = getFileName(context, IMAGE_FOLDER, "Image_", fieName, ".jpg");

        System.out.println("imageFile is: " + imageFile.getAbsolutePath());

        return imageFile;

    }

    //Compose Full File Directory with File
    private static File getFileName(Context context, String folderPath, String preTextFileName, String fileName, String fileNameType){

        File internalStoragePath = context.getFilesDir();

        File folderFilePath = new File(internalStoragePath.getAbsolutePath() + folderPath);

        if (!folderFilePath.exists()) folderFilePath.mkdir();

        File currentFile = new File(folderFilePath.getAbsolutePath() + "/" + preTextFileName + fileName + fileNameType);

        return currentFile;

    }





}
