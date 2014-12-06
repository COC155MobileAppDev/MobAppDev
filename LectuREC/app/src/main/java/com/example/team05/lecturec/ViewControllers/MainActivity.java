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

        Cursor cursorModule = getModules();

        while (cursorModule.moveToNext()) {
            Integer displaymoduleId = cursorModule.getInt(cursorModule.getColumnIndex(dbHelper.COLUMN_MODULE_ID));
            modulesView.append(" ");
            System.out.println(displaymoduleId.toString());
            modulesView.append(displaymoduleId.toString());

            String displayModule = cursorModule.getString(cursorModule.getColumnIndex(dbHelper.COLUMN_MODULE_NAME));
            modulesView.append(" ");
            modulesView.append(displayModule);
            modulesView.append("\n");
        }

        //audio filenames
        TextView audiofilenamesView = (TextView)findViewById(R.id.audiofilenamesview);

        Cursor cursorAudio = getAudio();

        while (cursorAudio.moveToNext()) {
            Integer displayAudioId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_ID));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioId.toString());
            audiofilenamesView.append(displayAudioId.toString());

            String displayAudioFilename = cursorAudio.getString(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_NAME));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioFilename);
            audiofilenamesView.append(displayAudioFilename);

            Integer displayAudioSessionId = cursorAudio.getInt(cursorAudio.getColumnIndex(dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN));
            audiofilenamesView.append(" ");
            System.out.println(displayAudioSessionId.toString());
            audiofilenamesView.append(displayAudioSessionId.toString());
            audiofilenamesView.append("\n");
        }


        //sessions
        TextView sessionsView = (TextView)findViewById(R.id.sessionview);

        Cursor cursorSession = getSessions();

        while (cursorSession.moveToNext()) {
            Integer displaySessionId = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_ID));
            sessionsView.append(" ");
            System.out.println(displaySessionId.toString());
            sessionsView.append(displaySessionId.toString());

            String displaySessionName = cursorSession.getString(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_NAME));
            sessionsView.append(" ");
            System.out.println(displaySessionName);
            sessionsView.append(displaySessionName);

            Integer displaySessionModule = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN));
            sessionsView.append(" ");
            System.out.println(displaySessionModule.toString());
            sessionsView.append(displaySessionModule.toString());

            Integer displaySessionFolder = cursorSession.getInt(cursorSession.getColumnIndex(dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN));
            sessionsView.append(" ");
            System.out.println(displaySessionFolder.toString());
            sessionsView.append(displaySessionFolder.toString());
            sessionsView.append("\n");
        }


    }

    DBHelper dbHelper;

    private Cursor getModules() {
        // Run query
        Uri uri = DBProvider.MODULE_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_MODULE_ID,
                                                dbHelper.COLUMN_MODULE_NAME
                                            };
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
        refresh(view);
    }

    public void refresh(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private Cursor getAudio() {
        // Run query
        Uri uri = DBProvider.AUDIO_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_AUDIO_ID,
                                                dbHelper.COLUMN_AUDIO_NAME,
                                                dbHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN
                                            };
        String selection = null;                //according to session_id = ? !!!!!!!
        String[] selectionArgs = null;          //according to session_id variable !!!!!!!
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void saveAudio(View view) {

        Integer make_session_id = 1;

        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_AUDIO_NAME, ((EditText)findViewById(R.id.add_audio_filename)).getText().toString() );
        values.put(DBHelper.COLUMN_AUDIO_SESSION_ID_FOREIGN, make_session_id );

        Uri uri = getContentResolver().insert(DBProvider.AUDIO_URI, values);
        retrieveAudioFilenames(view);
    }

    public void retrieveAudioFilenames(View view) {
        System.out.println("audio refresh");
        Intent intent_audio = getIntent();
        finish();
        startActivity(intent_audio);
        System.out.println("audio retrieve");
    }

    public void newSession(View view) {

        ContentValues values = new ContentValues();
        String session_name = "2014_12_05-22_30_12"; //YYYY-MM-DD HH:MM:SS or can just use 'now' format!!!
        Integer module = 4;
        Integer folder = 3;

        values.put(DBHelper.COLUMN_SESSION_NAME, session_name );
        values.put(DBHelper.COLUMN_SESSION_MODULE_ID_FOREIGN, module);
        values.put(DBHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN, folder);

        Uri uri = getContentResolver().insert(DBProvider.SESSION_URI, values);
        retrieveSessions(view);
    }

    private Cursor getSessions(){
        // Run query
        Uri uri = DBProvider.SESSION_URI;
        String[] projection = new String[] {    dbHelper.COLUMN_SESSION_ID,
                                                dbHelper.COLUMN_SESSION_NAME,
                                                dbHelper.COLUMN_SESSION_MODULE_ID_FOREIGN,
                                                dbHelper.COLUMN_SESSION_FOLDER_ID_FOREIGN
                                            };
        String selection = null;                //according to module_id = ?
        String[] selectionArgs = null;          //according to module_id variable
        String sortOrder = null;

        //return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public void retrieveSessions(View view) {
        Intent intent_session = getIntent();
        finish();
        startActivity(intent_session);
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
