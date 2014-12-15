package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.widget.Toast;

import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.Controllers.ModuleDummyTesting;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        ModuleListFragment.OnModuleListFragmentInteractionListener,
        ArchiveListFragment.OnArchiveListFragmentInteractionListener {

    // Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    //Used to store the last screen title. For use in {@link #restoreActionBar()}.
    private CharSequence mTitle;

    private FragmentTabHost fTabHost;

    private boolean newModuleCreated = false;

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


        // Set up TabHost
        fTabHost = (FragmentTabHost)findViewById(R.id.fTabHost);

        fTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);

        fTabHost.addTab(fTabHost.newTabSpec("modules").setIndicator("Modules", null), ModuleListFragment.class, null);
        fTabHost.addTab(fTabHost.newTabSpec("archives").setIndicator("Archives", null), ArchiveListFragment.class, null);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (newModuleCreated){

            Module newModule = null;

            int newModuleID = DataManager.getLastModuleRecord(getApplicationContext());
            for (Module mod:DataManager.getCurrentModules(getApplicationContext())) if (mod.getID() == newModuleID) newModule = mod;

            if (newModule != null) {

                Intent selectedModuleIntent = new Intent(this, ModuleActivity.class);
                selectedModuleIntent.putExtra("selectedModule", (Serializable) newModule);
                startActivity(selectedModuleIntent);
            }

        }

    }

    public void setNewModuleCreated(){  newModuleCreated = true;    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("Called");

        if (resultCode == RESULT_OK && requestCode == 100){
            if (data.hasExtra("newModule")){

                Module selectedModule = (Module)data.getSerializableExtra("newModule");

                Intent selectedModuleIntent = new Intent(this, ModuleActivity.class);
                selectedModuleIntent.putExtra("selectedModule", (Serializable) selectedModule);
                startActivity(selectedModuleIntent);
            }
        }

    }

    @Override
    public void OnModuleListFragmentInteractionListener(Uri uri) {  }

    @Override
    public void OnArchiveListFragmentInteractionListener(Uri uri) { }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "Home: " + getString(R.string.moduleMenu);
                break;
            case 2:
                mTitle = getString(R.string.newModuleMenu);
                Intent newmoduleIntent = new Intent(this, NewModuleActivity.class);
                newmoduleIntent.putExtra("newMode", true);
                ArrayList<Module> mList = ModuleDummyTesting.getModuleList();
                newmoduleIntent.putExtra("currentModule", (Serializable)mList.get(0));
                startActivityForResult(newmoduleIntent, 100);
                break;
            case 3:
                mTitle = "Home:    " + getString(R.string.archiveMenu);
                break;
            case 4:
                mTitle = getString(R.string.shareMenu);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case 5:
                mTitle = getString(R.string.aboutMenu);
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case 6:
                Intent testIntent = new Intent(this, TestActivity.class);
                startActivity(testIntent);
                break;
            case 7:
                Intent ssIntent = new Intent(this, SelectedSessionActivity.class);
                startActivity(ssIntent);
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

            Intent aboutIntent = new Intent(this, SettingsActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //A placeholder fragment containing a simple view.
    public static class PlaceholderFragment extends Fragment {
        //The fragment argument representing the section number for this fragment.
        private static final String ARG_SECTION_NUMBER = "section_number";

        // Returns a new instance of this fragment for the given section number.
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        /*
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
        */

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

}
