package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentTabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.team05.lecturec.R;

import java.util.Calendar;

public class NewmoduleActivity extends FragmentActivity
        implements ModuletimeFragment.OnModuletimeFragmentInteractionListener {


    private FragmentTabHost timeTabHost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmodule);


        timeTabHost = (FragmentTabHost)findViewById(R.id.timeTabHost);

        timeTabHost.setup(this, getSupportFragmentManager(), R.id.timeTabContent);

        timeTabHost.addTab(timeTabHost.newTabSpec("Mon").setIndicator("Mon", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Tue").setIndicator("Tue", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Wed").setIndicator("Wed", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Thu").setIndicator("Thu", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Fri").setIndicator("Fri", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sat").setIndicator("Sat", null), ModuletimeFragment.class, null);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sun").setIndicator("Sun", null), ModuletimeFragment.class, null);


    }



    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }




    @Override
    public void onModuletimeFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newmodule, menu);
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
