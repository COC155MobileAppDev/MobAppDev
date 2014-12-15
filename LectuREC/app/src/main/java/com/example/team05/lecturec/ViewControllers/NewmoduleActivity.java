package com.example.team05.lecturec.ViewControllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Toast;


import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.R;

import java.io.Serializable;
import java.util.ArrayList;

public class NewModuleActivity extends FragmentActivity
        implements ModuleTimeFragment.OnModuleTimeFragmentInteractionListener {

    private static int EDIT_MODULE_DELETED_RESULT_CODE = 500;
    private static int EDIT_MODULE_ARCHIVED_RESULT_CODE = 600;

    private boolean newMode;

    private FragmentTabHost timeTabHost;

    private EditText moduleEditField;
    private TextView moduleNameDisplay;

    private Button deleteModuleButton;
    private Button archiveModuleButton;
    private Button saveModuleButton;


    private Module currentModule;
    private boolean moduleNameChange = false;
    private ArrayList<ModuleTime> originalMTs = new ArrayList<ModuleTime>();

    private ArrayList<ModuleTime> monMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> tueMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> wedMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> thuMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> friMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> satMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> sunMTs = new ArrayList<ModuleTime>();

    private ArrayList<ModuleTime> newMTs = new ArrayList<ModuleTime>();
    private int newModuleTimeCounter = 0;

    private ArrayList<ModuleTime> editedMTs = new ArrayList<ModuleTime>();
    private ArrayList<ModuleTime> deletingMTs = new ArrayList<ModuleTime>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmodule);

        Bundle passedData = getIntent().getExtras();
        newMode = passedData.getBoolean("newMode", true);


        moduleNameDisplay = (TextView)findViewById(R.id.moduleNameDisplay);


        deleteModuleButton = (Button)findViewById(R.id.deleteModuleBtn);
        archiveModuleButton = (Button)findViewById(R.id.archiveModuleBtn);
        saveModuleButton = (Button)findViewById(R.id.saveModuleBtn);


        if (newMode) {

            currentModule = new Module(-1, "");


            setTitle("New Module");

            deleteModuleButton.setVisibility(View.GONE);
            archiveModuleButton.setVisibility(View.GONE);

            LinearLayout.LayoutParams saveBtnLayoutParam = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

            saveModuleButton.setLayoutParams(saveBtnLayoutParam);

        } else {

            currentModule = (Module)passedData.getSerializable("currentModule");

            setTitle("Edit \"" + currentModule.getName() + "\" Module");

        }

        setupModuleEditField();

        setupModuleTimeStores();

        setupTabsWithFragments();

    }

    private void setupModuleEditField(){

        moduleEditField = (EditText)findViewById(R.id.moduleEditField);
        moduleEditField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                moduleNameDisplay.setText(s);
                currentModule.setName(s.toString());
                moduleNameChange = true;
                System.out.println("run");

            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        if (newMode) moduleEditField.setText("");
        else moduleEditField.setText(currentModule.getName());
        moduleNameChange = false;

    }

    //Populating ArrayList<ModuleTime> by day
    private void setupModuleTimeStores(){

        System.out.println("mList Hi " + currentModule.getName());

        originalMTs = currentModule.getModuleTimes();

        for (ModuleTime mt: originalMTs){

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

    //Setting up tabbed fragments and relative data
    private void setupTabsWithFragments(){

        timeTabHost = (FragmentTabHost)findViewById(R.id.timeTabHost);
        timeTabHost.setup(this, getSupportFragmentManager(), R.id.timeTabContent);


        Intent intent = new Intent(getApplicationContext(), ModuleTimeFragment.class);

        intent.putExtra("day", 0);
        intent.putExtra("moduleTimes", monMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Mon").setIndicator("Mon", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 1);
        intent.putExtra("moduleTimes", tueMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Tue").setIndicator("Tue", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 2);
        intent.putExtra("moduleTimes", wedMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Wed").setIndicator("Wed", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 3);
        intent.putExtra("moduleTimes", thuMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Thu").setIndicator("Thu", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 4);
        intent.putExtra("moduleTimes", friMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Fri").setIndicator("Fri", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 5);
        intent.putExtra("moduleTimes", satMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sat").setIndicator("Sat", null), ModuleTimeFragment.class, intent.getExtras());

        intent.putExtra("day", 6);
        intent.putExtra("moduleTimes", sunMTs);
        timeTabHost.addTab(timeTabHost.newTabSpec("Sun").setIndicator("Sun", null), ModuleTimeFragment.class, intent.getExtras());

    }



    //Counter value to temp set id for new moduleTime objects (always negative)
    public int getNewModuleTimeCounter() {
        return --newModuleTimeCounter;
    }


    @Override
    public void onModuleTimeFragmentInteraction(Uri uri) {

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


    //UI Event handlers
    public void onModuleSaveClick(View v){

        if (!moduleEditField.getText().toString().isEmpty()) {

            if (newMode){

                DataManager.addNewModule(getApplicationContext(), currentModule, newMTs);

                int newModuleID = DataManager.getLastModuleRecord(getApplicationContext());
                for (Module mt:DataManager.getCurrentModules(getApplicationContext())) if (mt.getID() == newModuleID) currentModule = mt;

                Intent newModuleResultIntent = new Intent();
                newModuleResultIntent.putExtra("newModule", (Serializable)currentModule);

                setResult(RESULT_OK, newModuleResultIntent);

                finish();

            } else {

                DataManager.editExistingModule(getApplicationContext(), currentModule, newMTs, editedMTs, deletingMTs);

                setResult(RESULT_OK);
                finish();

            }

        } else Toast.makeText(getApplicationContext(), "Please Enter a Module Name", Toast.LENGTH_LONG).show();

    }

    public void onModuleArchiveClick(View v){

        currentModule.setArchiveState(true);
        DataManager.editExistingModule(getApplicationContext(), currentModule, newMTs, editedMTs, deletingMTs);

        setResult(EDIT_MODULE_ARCHIVED_RESULT_CODE);
        finish();

    }

    public void onModuleDeleteClick(View v){

        DataManager.deletingExistingModule(getApplicationContext(), currentModule);

        setResult(EDIT_MODULE_DELETED_RESULT_CODE);
        finish();

    }



    //Adding to MTs to add, edit, and deleting, such that on save, all these MT lists are passed to Data Manager
    public void addToNewList(ModuleTime mt){    newMTs.add(mt); }

    public void addToEditList(ModuleTime mt){

        System.out.println("addToEditList called");

        int mtID = mt.getID();

        int index = -1;

        if (mtID < 0){

            for (ModuleTime findMT:newMTs) if (findMT.getID() == mtID) index = newMTs.indexOf(findMT);

            System.out.println(String.format("New MT with id: %d and index of %d", mtID, index));

            if (index >= 0) newMTs.set(index, mt);

        } else if (mtID > 0){

            for (ModuleTime findMT:editedMTs) if (findMT.getID() == mtID) index = editedMTs.indexOf(findMT);

            System.out.println(String.format("Edit MT with id: %d and index of %d", mtID, index));

            if (index >= 0) editedMTs.set(index, mt);
            else editedMTs.add(mt);

        }

        for (ModuleTime eMT:editedMTs)
            System.out.println("mt in edit list id is: " + eMT.getID());

    }

    public void addToDeleteList(ModuleTime deletingMT){

        int index = -1;

        if (deletingMT.getID() > 0) {

            for (ModuleTime mtFinder:editedMTs)
                if (mtFinder.getID() == deletingMT.getID()) index = editedMTs.indexOf(mtFinder);


            if (index >= 0)
                editedMTs.remove(index);


            originalMTs.remove(deletingMT);

            deletingMTs.add(deletingMT);


        } else if (deletingMT.getID() < 0){

            for (ModuleTime mtFinder:newMTs)
                if (mtFinder.getID() == deletingMT.getID()) index = newMTs.indexOf(mtFinder);

            if (index >= 0)
                newMTs.remove(index);

        }

        System.out.println("The deleting ID is: " + deletingMT.getID());

        for (ModuleTime dMT:deletingMTs)
            System.out.println("mt in deleting list id is: " + dMT.getID());

    }



}
