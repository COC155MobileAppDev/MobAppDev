package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.team05.lecturec.Controllers.ModuleDummyTesting;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.util.ArrayList;
import java.util.List;

public class ModuleFragment extends Fragment {



    private ArrayList<Module> mList;
    private ArrayList<String> moduleNames;
    private ListView moduleListview;
    private FrameLayout fragmentLayout;

    private OnModuleFragmentInteractionListener mListener;



    public ModuleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moduleNames = new ArrayList<String>();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mList = ModuleDummyTesting.getModuleList();


        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_module, container, false);

        moduleListview = (ListView)fragmentLayout.findViewById(R.id.listModule);

        for (Module m: mList){
            moduleNames.add(m.getName());

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((getActivity()).getApplicationContext(), android.R.layout.simple_list_item_1, moduleNames);

        moduleListview.setAdapter(arrayAdapter);



        // Inflate the layout for this fragment
        return fragmentLayout;


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnModuleFragmentInteractionListener) activity;
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
    public interface OnModuleFragmentInteractionListener {
        // TODO: Update argument type and name
        public void OnModuleFragmentInteractionListener(Uri uri);
    }

}
