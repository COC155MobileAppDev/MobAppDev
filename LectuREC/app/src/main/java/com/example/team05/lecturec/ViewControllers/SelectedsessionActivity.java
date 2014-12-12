package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;

import com.example.team05.lecturec.R;

public class SelectedSessionActivity extends FragmentActivity
    implements AudioFragment.OnAudioFragmentInteractionListener,
    ImagesFragment.OnImagesFragmentInteractionListener {



    private FragmentTabHost sessionTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedsession);



        // Set up TabHost
        sessionTabHost = (FragmentTabHost)findViewById(R.id.fTabHost);

        sessionTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);

        sessionTabHost.addTab(sessionTabHost.newTabSpec("audio").setIndicator("Audio", null), AudioFragment.class, null);
        sessionTabHost.addTab(sessionTabHost.newTabSpec("images").setIndicator("Images", null), ImagesFragment.class, null);

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
