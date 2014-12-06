package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
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

import com.example.team05.lecturec.Controllers.ModuleDummyTesting;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.util.ArrayList;
import java.util.Calendar;

public class NewmoduleActivity extends FragmentActivity
        implements ModuletimeFragment.OnModuletimeFragmentInteractionListener {


    private FragmentTabHost timeTabHost;

    private Module currentModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmodule);

        ArrayList<Module> mList = ModuleDummyTesting.getModuleList();

        currentModule = mList.get(0);

        System.out.println("mList Hi " + currentModule.getName());





        timeTabHost = (FragmentTabHost)findViewById(R.id.timeTabHost);
        timeTabHost.setup(this, getSupportFragmentManager(), R.id.timeTabContent);


        Intent intent = new Intent(getApplicationContext(), ModuletimeFragment.class);


        intent.putExtra("day", "Mon");
        timeTabHost.addTab(timeTabHost.newTabSpec("Mon").setIndicator("Mon", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Tue");
        timeTabHost.addTab(timeTabHost.newTabSpec("Tue").setIndicator("Tue", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Wed");
        timeTabHost.addTab(timeTabHost.newTabSpec("Wed").setIndicator("Wed", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Thu");
        timeTabHost.addTab(timeTabHost.newTabSpec("Thu").setIndicator("Thu", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Fri");
        timeTabHost.addTab(timeTabHost.newTabSpec("Fri").setIndicator("Fri", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Sat");
        timeTabHost.addTab(timeTabHost.newTabSpec("Sat").setIndicator("Sat", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("day", "Sun");
        timeTabHost.addTab(timeTabHost.newTabSpec("Sun").setIndicator("Sun", null), ModuletimeFragment.class, intent.getExtras());


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
