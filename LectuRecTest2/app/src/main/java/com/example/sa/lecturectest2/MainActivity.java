package com.example.sa.lecturectest2;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView modulesView = (TextView) findViewById(R.id.modulesview);

        Cursor cursor = getModules();

        while (cursor.moveToNext()) {
            String displayModule = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MODULE));
            modulesView.append(displayModule);
            modulesView.append("\n");
        }
    }

    LectuRecDBHelper dbHelper;

    private Cursor getModules() {
        // Run query
        Uri uri = LectuRecProvider.MODULE_URI;
        String[] projection = new String[] { dbHelper.COLUMN_ID, dbHelper.COLUMN_MODULE };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
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
