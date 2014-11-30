package mobileapp.example.com.lecturerecorder;



import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    // Fragment TabHost as mTabHost
    //private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        HomeFragment fragmenttab = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragmenttab).commit();
        /*
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);


        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
                ArchiveFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
                AboutFragment.class, null);

        */

        /*
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("modules");
        tabSpec.setContent(R.id.tabModules);
        tabSpec.setIndicator("Modules");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("archive");
        tabSpec.setContent(R.id.tabArchive);
        tabSpec.setIndicator("Archive");
        tabHost.addTab(tabSpec);
        */


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }






    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        // starting position  = 0
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;



        switch (position){
            case 0:
                //Home
                mTitle = getString(R.string.title_section1);

                Toast.makeText(getApplicationContext(), "Home Fragment", Toast.LENGTH_SHORT).show();
                fragment = new HomeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container2, fragment)
                        .commit();
                break;

            case 1:
                //Modules
                mTitle = getString(R.string.title_section2);
                Toast.makeText(getApplicationContext(), "Modules Fragment", Toast.LENGTH_SHORT).show();
                fragment = new ModulesFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container2, fragment)
                        .commit();
                break;

            case 2:
                //Archive
                mTitle = getString(R.string.title_section3);
                Toast.makeText(getApplicationContext(), "Archive Fragment", Toast.LENGTH_SHORT).show();
                fragment = new ArchiveFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container2, fragment)
                        .commit();
                break;


            case 3:
                //Share App
                //mTitle = getString(R.string.title_section4);
                //Toast.makeText(getApplicationContext(), "Share App Fragment", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out LectuREC app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                break;

            case 4:
                //About
                //mTitle = getString(R.string.title_section5);
                Toast.makeText(getApplicationContext(), "About Fragment", Toast.LENGTH_SHORT).show();
                openAbout();
                //fragment = new AboutFragment();
                break;


        }

        /*
        fragmentManager.beginTransaction()
                .replace(R.id.container2, fragment)
                .commit();
        */

    }




    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
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
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                Toast.makeText(getApplicationContext(), "Search pressed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                openSettings();
                Toast.makeText(getApplicationContext(), "Settings pressed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    /**
     * Launching new activity
     * */

     private void openSettings() {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);
    }

    private void openAbout() {
        Intent i = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(i);

    }




}
