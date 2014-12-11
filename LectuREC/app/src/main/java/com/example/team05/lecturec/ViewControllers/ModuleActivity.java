package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.io.Serializable;

public class ModuleActivity extends Activity {

    Module selectedModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        Bundle passedData = getIntent().getExtras();
        selectedModule = (Module)passedData.getSerializable("selectedModule");

        ((TextView)findViewById(R.id.moduleActivityTextView)).setText(selectedModule.getName());

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


}
