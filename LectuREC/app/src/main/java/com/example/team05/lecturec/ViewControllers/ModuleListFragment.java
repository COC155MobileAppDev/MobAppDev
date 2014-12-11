package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import android.support.v4.app.Fragment;

import com.example.team05.lecturec.Controllers.ModuleDummyTesting;
import com.example.team05.lecturec.CustomExtensions.ModuleAdapter;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.io.Serializable;
import java.util.ArrayList;

import static android.widget.AdapterView.*;


public class ModuleListFragment extends Fragment {



    private ArrayList<Module> currentModules;
    private ListView moduleListview;
    private FrameLayout fragmentLayout;

    private OnModuleListFragmentInteractionListener mListener;



    public ModuleListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentModules= ModuleDummyTesting.getModuleList();


        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_module, container, false);

        moduleListview = (ListView)fragmentLayout.findViewById(R.id.listModule);


        ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity().getApplicationContext(), R.layout.listview_row_module, currentModules);
        moduleListview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Module selectedModule = currentModules.get(position);

                Intent selectedModuleIntent = new Intent(getActivity(), ModuleActivity.class);
                selectedModuleIntent.putExtra("selectedModule", (Serializable)selectedModule);

                System.out.println("clicked" + currentModules.get(position).getName());

                startActivity(selectedModuleIntent);

            }
        });
        moduleListview.setAdapter(moduleAdapter);


        // Inflate the layout for this fragment
        return fragmentLayout;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnModuleListFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnModuleListFragmentInteractionListener {
        // TODO: Update argument type and name
        public void OnModuleListFragmentInteractionListener(Uri uri);
    }

}
