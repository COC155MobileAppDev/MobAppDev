package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

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

    private File currentAudioFile;
    private String currentAudioName;

    private File currentImageFile;
    private String currentImageName;





    private ImageView imageRecRotate;
    private Button stopButton;

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

        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        imageRecRotate = (ImageView)findViewById(R.id.recScale);
        stopButton = (Button)findViewById(R.id.stopRecordBtn);

        stopButton.setEnabled(false);

        imageRecRotate.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(animScale);


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
                stopButton.setEnabled(true);
                imageRecRotate.setEnabled(false);


            }
        });

        imageRecRotate.performClick();


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 777){

            if (resultCode == RESULT_OK) {

                Image newImage = new Image(-1, currentImageName);
                newImages.add(newImage);

            } else if (resultCode == RESULT_CANCELED) return;


        }

    }

    public void stopCurrentRecording(View v){

        imageRecRotate.clearAnimation();

        mediaRecorder.stop();

        stopButton.setPressed(true);

        imageRecRotate.setEnabled(true);


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
        finish();

    }

    public void capturePicture(View v){

        currentImageName = FileManager.getCurrentDateTimeFileName();
        currentImageFile = FileManager.getImageFileFormat(getApplicationContext(), currentImageName);

        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
        startActivityForResult(imageCaptureIntent, 777);

    }

}
