package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.ImageFormat;
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
import android.widget.TextView;

import java.util.ArrayList;


import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.DataTypes.Folder;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.Session;
import com.example.team05.lecturec.R;

public class SelectedSessionActivity extends FragmentActivity
    implements AudioFragment.OnAudioFragmentInteractionListener,
    ImagesFragment.OnImagesFragmentInteractionListener {

    Module parentModule;
    Session selectedSession;
    Bundle passedData;

    public ArrayList<String> songs;
    Cursor songCursor;
    SeekBar sB;
    String fileName;
    MediaPlayer mP;
    AudioManager aM;

    private FragmentTabHost sessionTabHost;
    private TextView selectedModuleName;
    private TextView selectedSessionName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedsession);


        passedData = getIntent().getExtras();
        selectedSession = (Session)passedData.getSerializable("selectedSession");
        parentModule = (Module)passedData.getSerializable("parentModule");

        selectedSession.setAudios(DataManager.getAudios(getApplicationContext(), selectedSession.getID()));
        selectedSession.setImages(DataManager.getImages(getApplicationContext(), selectedSession.getID()));

        setTitle("Session: " + selectedSession.getName());
        selectedSessionName = (TextView)findViewById(R.id.selectedSessionName);
        selectedSessionName.setText(selectedSession.getName());

        selectedModuleName = (TextView)findViewById(R.id.selectedModuleName);
        selectedModuleName.setText(parentModule.getName());

        loadMusic();

        // Set up TabHost
        sessionTabHost = (FragmentTabHost)findViewById(R.id.fTabHost);

        sessionTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);

        Intent audioIntent = new Intent(getApplicationContext(), AudioFragment.class);
        audioIntent.putExtra("audioList", selectedSession.getAudios());
        sessionTabHost.addTab(sessionTabHost.newTabSpec("audio").setIndicator("Audio", null), AudioFragment.class, audioIntent.getExtras());

        Intent imageIntent = new Intent(getApplicationContext(), ImagesFragment.class);
        imageIntent.putExtra("imageList", selectedSession.getImages());
        sessionTabHost.addTab(sessionTabHost.newTabSpec("images").setIndicator("Images", null), ImagesFragment.class, imageIntent.getExtras());

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

    public void changeFolder(View v){

        FolderDialog folderDialog = new FolderDialog();
        folderDialog.setParentActivity(this);
        folderDialog.setFolderList(parentModule.getFolders());
        folderDialog.setParentModule(parentModule);

        folderDialog.show(getFragmentManager(), "folderListDialog");


    }

    public void setChangeSelectedFolder(Folder selectedFolder){

        selectedSession.setFolderID(selectedFolder.getID());

        DataManager.editExistingSessionForFolder(getApplicationContext(), selectedSession, selectedFolder.getID());

    }

    public void createNewFolder(Folder newFolder){

        DataManager.addNewFolder(getApplicationContext(), parentModule.getID(), newFolder);

        int newFolderID = DataManager.getLastFolderRecord(getApplicationContext());

        selectedSession.setFolderID(newFolderID);

        DataManager.editExistingSessionForFolder(getApplicationContext(), selectedSession, newFolderID);

        parentModule.setFolders(DataManager.getFolders(getApplicationContext(), parentModule.getID()));

    }

}
