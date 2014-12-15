package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;


import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.CustomExtensions.ModuleAdapter;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.io.Serializable;
import java.util.ArrayList;

import static android.widget.AdapterView.*;


public class ArchiveListFragment extends Fragment {


    private ArrayList<Module> archivedModules;
    private ListView archiveListView;
    private FrameLayout fragmentLayout;

    private OnArchiveListFragmentInteractionListener aListener;

    public static ArchiveListFragment newInstance(String param1, String param2) {
        ArchiveListFragment fragment = new ArchiveListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ArchiveListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        archivedModules = DataManager.getArchivedModules(getActivity().getApplicationContext());


        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_archive, container, false);

        archiveListView = (ListView)fragmentLayout.findViewById(R.id.listArchive);


        ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity().getApplicationContext(), R.layout.listview_row_module, archivedModules);
        archiveListView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent moduleSessionsIntent = new Intent(getActivity(), ModuleSessionsActivity.class);
                moduleSessionsIntent.putExtra("selectedModule", (Serializable)archivedModules.get(position));
                startActivity(moduleSessionsIntent);

            }
        });
        archiveListView.setAdapter(moduleAdapter);

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (aListener != null) {
            aListener.OnArchiveListFragmentInteractionListener(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            aListener = (OnArchiveListFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        aListener = null;
    }

    public interface OnArchiveListFragmentInteractionListener {
        // TODO: Update argument type and name
        public void OnArchiveListFragmentInteractionListener(Uri uri);
    }

}
