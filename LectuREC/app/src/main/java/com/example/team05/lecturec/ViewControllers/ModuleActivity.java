package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.CustomExtensions.RecentSessionAdapter;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ModuleActivity extends Activity {

    private Bundle passedData;

    private Module selectedModule;

    RecentSessionAdapter recentSessionAdapter;
    private ListView recentSessionsListView;
    private ArrayList<Session> recentSessions;
    private ArrayList<Folder> moduleFolders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        passedData = getIntent().getExtras();


        try {
            selectedModule = (Module)passedData.getSerializable("selectedModule");

        } catch (NullPointerException exc){
            passedData = savedInstanceState.getBundle("currentBundle");
            selectedModule = (Module)savedInstanceState.getSerializable("selectedModule");
        }

        setTitle(selectedModule.getName());

        recentSessionsListView = (ListView)findViewById(R.id.recentSessionsListView);

        recentSessions = new ArrayList<Session>();
        moduleFolders = new ArrayList<Folder>();
        populateRecentSessionsList();
        populateRecentSessionsListView();

    }

    private void populateRecentSessionsList(){

        ArrayList<Session> allModuleSessions = selectedModule.getSessions();

        int countSize = 10;
        if (allModuleSessions.size() <= countSize) countSize = allModuleSessions.size();

        for (int counter = (countSize - 1); counter > -1; counter--) recentSessions.add(allModuleSessions.get(counter));

    }

    private void populateRecentSessionsListView(){

        View rsListHeader = (View)getLayoutInflater().inflate(R.layout.listview_header_recentsession, null);
        recentSessionsListView.addHeaderView(rsListHeader);

        recentSessionAdapter =
                new RecentSessionAdapter(getApplicationContext(), R.layout.listview_row_recentsession, recentSessions);

        recentSessionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    int pos = position - 1;

                    Session selectedSession = recentSessions.get(pos);

                    Intent selectedSessionIntent = new Intent(ModuleActivity.this, SelectedSessionActivity.class);
                    selectedSessionIntent.putExtra("selectedSession", (Serializable)selectedSession);

                    System.out.println("clicked " + recentSessions.get(pos).getName());

                    startActivity(selectedSessionIntent);

                }

            }

        });

        recentSessionsListView.setAdapter(recentSessionAdapter);



    }

    @Override
    public void onResume(){
        super.onResume();

        if (recentSessionAdapter != null){

            ArrayList<Module> refreshModuleList = DataManager.getCurrentModules(getApplicationContext());

            for (Module findModule: refreshModuleList) if (findModule.getID() == selectedModule.getID()) selectedModule = findModule;

            recentSessions.clear();
            populateRecentSessionsList();

            recentSessionAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putBundle("currentBundle", passedData);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_module, menu);
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

    public void startNewSessionButton(View v){

        Intent newSessionIntent = new Intent(this, NewSessionActivity.class);
        newSessionIntent.putExtra("currentModule", (Serializable)selectedModule);
        startActivity(newSessionIntent);

    }


    public void editModuleButton(View v){

        Intent editModuleIntent = new Intent(this, NewModuleActivity.class);
        editModuleIntent.putExtra("newMode", false);
        editModuleIntent.putExtra("currentModule", (Serializable)selectedModule);
        startActivity(editModuleIntent);

    }

    public void moduleSessionsButton(View v){

        Intent moduleSessionsIntent = new Intent(this, ModuleSessionsActivity.class);
        moduleSessionsIntent.putExtra("selectedModule", (Serializable)selectedModule);
        startActivity(moduleSessionsIntent);

    }

}
