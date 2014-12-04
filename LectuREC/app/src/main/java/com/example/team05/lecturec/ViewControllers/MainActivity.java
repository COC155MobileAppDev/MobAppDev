package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team05.lecturec.ModelControllers.DBHelper;
import com.example.team05.lecturec.ModelControllers.DBProvider;
import com.example.team05.lecturec.R;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        TextView modulesView = (TextView)findViewById(R.id.modulesview);

        Cursor cursor = getModules();

        while (cursor.moveToNext()) {
            String displayModule = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_MODULE_NAME));
            modulesView.append(" ");
            modulesView.append(displayModule);
            modulesView.append("\n");
        }

        //audio filenames
        TextView audiofilenamesView = (TextView)findViewById(R.id.audiofilenamesview);

        System.out.println("working1_1");

        Cursor cursor2 = getAudio();

        System.out.println("working1_2");

        while (cursor2.moveToNext()) {
            System.out.println("working1_3");
            String displayAudioFilename = cursor2.getString(cursor2.getColumnIndex(dbHelper.COLUMN_AUDIO_NAME));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioFilename);
            audiofilenamesView.append(displayAudioFilename);
            audiofilenamesView.append("\n");
        }

        System.out.println("working1_4");

    }

    DBHelper dbHelper;

    private Cursor getModules() {
        // Run query
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] { dbHelper.COLUMN_MODULE_ID, dbHelper.COLUMN_MODULE_NAME };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

    }

    public void addModule(View view) {

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_MODULE_NAME, ((EditText)findViewById(R.id.add_module_edittext)).getText().toString() );

        Uri uri = getContentResolver().insert(DBProvider.MODULE_URI, values);

    }

    public void refresh(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private Cursor getAudio() {
        System.out.println("working2_1");

        // Run query
        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] { dbHelper.COLUMN_AUDIO_ID, dbHelper.COLUMN_AUDIO_NAME };
        String selection = null;                //according to session_id = ? !!!!!!!
        String[] selectionArgs = null;          //according to session_id variable !!!!!!!
        String sortOrder = null;

        System.out.println("working2_2");

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void saveAudio(View view) {
        System.out.println("working3_1");

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_AUDIO_NAME, ((EditText)findViewById(R.id.add_audio_filename)).getText().toString() );

        System.out.println("working3_2");

        Uri uri = getContentResolver().insert(DBProvider.AUDIO_URI, values);
    }

    public void retrieveAudioFilenames(View view) {
        System.out.println("audio refresh");
        Intent intent_audio = getIntent();
        finish();
        startActivity(intent_audio);
        System.out.println("audio retrieve");
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
