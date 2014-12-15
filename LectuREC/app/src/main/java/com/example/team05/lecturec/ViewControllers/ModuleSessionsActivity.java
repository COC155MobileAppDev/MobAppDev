package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.team05.lecturec.CustomExtensions.ModuleSessionsAdapter;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.io.Serializable;

public class ModuleSessionsActivity extends Activity {

    private Module selectedModule;

    private ExpandableListView msExpandableListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_sessions);

        Bundle passedData = getIntent().getExtras();
        selectedModule = (Module)passedData.getSerializable("selectedModule");

        msExpandableListView = (ExpandableListView)findViewById(R.id.moduleSessionsExpandableListView);

        final ModuleSessionsAdapter moduleSessionsAdapter = new ModuleSessionsAdapter(getApplicationContext(), selectedModule, selectedModule.getFolders(), selectedModule.getSessions());

        msExpandableListView.setAdapter(moduleSessionsAdapter);

        int msELVsize = msExpandableListView.getCount();

        for (int counter = 0; counter < msELVsize; counter++ ) msExpandableListView.expandGroup(counter);


        msExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){

                Session selectedSession = (Session)moduleSessionsAdapter.getChild(groupPosition, childPosition);

                Intent selectedSessionIntent = new Intent(ModuleSessionsActivity.this, SelectedSessionActivity.class);
                selectedSessionIntent.putExtra("selectedSession", (Serializable)selectedSession);

                startActivity(selectedSessionIntent);

                return true;
            }

        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_module_sessions, menu);
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
