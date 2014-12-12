package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;



import com.example.team05.lecturec.R;

public class SelectedSessionActivity extends FragmentActivity
    implements AudioFragment.OnAudioFragmentInteractionListener,
    ImagesFragment.OnImagesFragmentInteractionListener {

    public ArrayList<String> songs;
    Cursor songCursor;
    SeekBar sB;
    String fileName;
    MediaPlayer mP;
    AudioManager aM;

    private FragmentTabHost sessionTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedsession);


        loadMusic();

        // Set up TabHost
        sessionTabHost = (FragmentTabHost)findViewById(R.id.fTabHost);

        sessionTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);

        sessionTabHost.addTab(sessionTabHost.newTabSpec("audio").setIndicator("Audio", null), AudioFragment.class, null);
        sessionTabHost.addTab(sessionTabHost.newTabSpec("images").setIndicator("Images", null), ImagesFragment.class, null);

    }

    @SuppressWarnings("deprecation")
    private void loadMusic() {
        String[] data = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DISPLAY_NAME};
        songCursor = this.managedQuery(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, data, null, null, null);
        songs = new ArrayList<String>();
        if(songCursor != null){
            while(songCursor.moveToNext()) {
                songs.add(songCursor.getString(1).toString());
            }
        }

    }

    public ArrayList<String> getSongList(){
        return songs;
    }

    @Override
    public void OnAudioFragmentInteractionListener(Uri uri) {
        //Do sommin
    }


    @Override
    public void OnImagesFragmentInteractionListener(Uri uri) {
        //Do sommin
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
