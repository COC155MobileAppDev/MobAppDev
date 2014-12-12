package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.DataTypes.Audio;
import com.example.team05.lecturec.DataTypes.Image;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.Session;
import com.example.team05.lecturec.DataTypes.Time;
import com.example.team05.lecturec.R;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NewSessionActivity extends Activity {


    private Module currentModule;
    private Session newSession;
    private ArrayList<Audio> newAudios;
    private ArrayList<Image> newImages;

    private MediaRecorder mediaRecorder;

    private MediaPlayer mediaPlayer;

    private File internalStoragePath;
    private File directoryFilePath;
    private File currentAudioFile;
    private String currentAudioName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);

        Bundle passedData = getIntent().getExtras();
        currentModule = (Module)passedData.getSerializable("currentModule");

        System.out.println("Session Name: " + getNewSessionName());

        newSession = new Session(-1, getNewSessionName(), 0);

        newAudios = new ArrayList<Audio>();
        newImages = new ArrayList<Image>();

    }

    private String getNewSessionName(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        return dateFormat.format(new Date());

    }

    private Time getTimeObjectOfAudioName(String audioName){

        String[] dateTimeSplit = audioName.split("_");

        String[] timeSplit = dateTimeSplit[dateTimeSplit.length - 1].split("-");

        int hours = Integer.parseInt(timeSplit[0]);
        int minutes = Integer.parseInt(timeSplit[1]);
        int seconds = Integer.parseInt(timeSplit[2]);

        Time audioStartTime = new Time(hours, minutes, seconds);

        return audioStartTime;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_session, menu);
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


    public void startNewRecording(View v){

        currentAudioName = FileManager.getCurrentDateTimeFileName();
        currentAudioFile = FileManager.getAudioFileFormat(getApplicationContext(), currentAudioName);


        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        mediaRecorder.setOutputFile(currentAudioFile.getAbsolutePath());

        try {
            mediaRecorder.prepare();
        } catch (IOException exc){
            Log.e("AUDIO RECORD IO ERROR", "prepare failed");
        }

        mediaRecorder.start();

    }

    public void stopCurrentRecording(View v){

        mediaRecorder.stop();

        MediaPlayer audioMP = MediaPlayer.create(this, Uri.parse(currentAudioFile.getAbsolutePath()));
        int duration = audioMP.getDuration();
        audioMP.stop();
        audioMP.release();
        audioMP = null;

        Time startTime = getTimeObjectOfAudioName(currentAudioName);

        Audio newAudio = new Audio(-1, currentAudioName, startTime, duration);
        newAudios.add(newAudio);


        mediaRecorder.release();

    }

    public void playLastRecording(View v){

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(currentAudioFile.getAbsolutePath()));
            mediaPlayer.prepare();
        } catch (IOException exc){
            Log.e("AUDIO PLAYBACK IO ERROR", "prepare failed");
        }

        mediaPlayer.start();


    }

    public void pauseCurrentRecording(View v){

        mediaPlayer.pause();
        mediaPlayer.release();
        mediaPlayer = null;

        System.out.println("Not doing anything");

    }

    public void saveSession(View v){

        DataManager.addNewSession(getApplicationContext(), currentModule.getID(), newSession, newAudios, newImages);

    }

}
