package com.example.sa.lecturectest2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView modulesView = (TextView) findViewById(R.id.modulesview);

        Cursor cursor = getModules();

        System.out.println("working4");

        while (cursor.moveToNext()) {
            String displayModuleID = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MODULE_ID));
            String displayModule = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MODULE_NAME));
            modulesView.append(displayModuleID);
            modulesView.append(" ");
            modulesView.append(displayModule);
            modulesView.append("\n");
        }

        System.out.println("working5");
    }

    LectuRecDBHelper dbHelper;

    private Cursor getModules() {
        // Run query
        Uri uri = LectuRecProvider.MODULE_URI;
        String[] projection = new String[] { dbHelper.COLUMN_MODULE_ID, dbHelper.COLUMN_MODULE_NAME };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        System.out.println("working3");

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

    }

    public void addModule(View view) { //View view???????????????????

        System.out.println("working1_1");
        ContentValues values = new ContentValues();

        System.out.println("working1_2");
        values.put(LectuRecDBHelper.COLUMN_MODULE_NAME, ((EditText)findViewById(R.id.add_module_edittext)).getText().toString() );

        System.out.println("working1_3");
        Uri uri = getContentResolver().insert(LectuRecProvider.MODULE_URI, values);

        System.out.println("working1_4");
        //Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

        System.out.println("working2");
    }

    public void refresh(View view) {
        System.out.println("please");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        System.out.println("Work please");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
