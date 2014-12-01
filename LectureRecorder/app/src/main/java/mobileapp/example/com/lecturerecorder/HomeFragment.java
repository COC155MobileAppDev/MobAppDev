package mobileapp.example.com.lecturerecorder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fernandoalvan on 26/11/14.
 */



public class HomeFragment extends Fragment {

    private FragmentTabHost mTabHost;

    //Mandatory Constructor
    public HomeFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("modules").setIndicator("MODULES"),
                ModulesFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("archive").setIndicator("ARCHIVE"),
                ArchiveFragment.class, null);


        return rootView;
    }


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





}


