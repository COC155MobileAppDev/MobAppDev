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
import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.R;

import java.util.ArrayList;
import java.util.Calendar;

public class NewmoduleActivity extends FragmentActivity
        implements ModuletimeFragment.OnModuletimeFragmentInteractionListener {

    private boolean newMode;

    private FragmentTabHost timeTabHost;

    private Module currentModule;
    private ArrayList<ModuleTime> allMTs = new ArrayList<ModuleTime>();

    private ArrayList<ModuleTime> monMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> tueMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> wedMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> thuMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> friMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> satMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> sunMTs = new ArrayList<ModuleTime>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmodule);

        ArrayList<Module> mList = ModuleDummyTesting.getModuleList();
        setupModuleTimeStores(mList.get(0));

        setupTabsWithFragments();

        //TODO find a way to store all edited mTimes and pull them back into this activity object store instance

    }





    public void setupModuleTimeStores(Module cm){

        currentModule = cm;

        System.out.println("mList Hi " + currentModule.getName());

        allMTs = currentModule.getModuleTimes();

        for (ModuleTime mt: allMTs){

            switch (mt.getDay()){
                case 0:
                    monMTs.add(mt);
                    break;
                case 1:
                    tueMTs.add(mt);
                    break;
                case 2:
                    wedMTs.add(mt);
                    break;
                case 3:
                    thuMTs.add(mt);
                    break;
                case 4:
                    friMTs.add(mt);
                    break;
                case 5:
                    satMTs.add(mt);
                    break;
                case 6:
                    sunMTs.add(mt);
                    break;
            }

        }

    }

    public void setupTabsWithFragments(){

        timeTabHost = (FragmentTabHost)findViewById(R.id.timeTabHost);
        timeTabHost.setup(this, getSupportFragmentManager(), R.id.timeTabContent);


        Intent intent = new Intent(getApplicationContext(), ModuletimeFragment.class);


        intent.putExtra("moduleTimes", monMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Mon").setIndicator("Mon", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", tueMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Tue").setIndicator("Tue", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", wedMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Wed").setIndicator("Wed", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", thuMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Thu").setIndicator("Thu", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", friMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Fri").setIndicator("Fri", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", satMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sat").setIndicator("Sat", null), ModuletimeFragment.class, intent.getExtras());

        intent.putExtra("moduleTimes", sunMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sun").setIndicator("Sun", null), ModuletimeFragment.class, intent.getExtras());

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
