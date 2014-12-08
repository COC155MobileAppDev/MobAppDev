package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.team05.lecturec.Controllers.ModuleDummyTesting;
import com.example.team05.lecturec.CustomExtensions.ModuleTimeAdapter;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.DataTypes.Time;
import com.example.team05.lecturec.R;

import java.util.ArrayList;


public class ModuletimeFragment extends Fragment
        implements View.OnClickListener {

    private OnModuletimeFragmentInteractionListener mListener;

    ModuleTimeAdapter moduleTimeAdapter;

    private FrameLayout mtFragmentLayout;
    private ListView lView;

    private ArrayList<ModuleTime> moduleTimes;


    public ModuletimeFragment() {
        // Required empty public constructor
    }

    public static ModuletimeFragment newInstance(String param1, String param2) {
        ModuletimeFragment fragment = new ModuletimeFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        */

        Bundle bundle = getArguments();

        moduleTimes = (ArrayList<ModuleTime>)bundle.get("moduleTimes");

        System.out.println("Number of mTimes = " + moduleTimes.size());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mtFragmentLayout = (FrameLayout)inflater.inflate(R.layout.fragment_moduletime, container, false);


        lView = (ListView)mtFragmentLayout.findViewById(R.id.moduleTimeListView);

        moduleTimeAdapter = new ModuleTimeAdapter(getActivity().getApplicationContext(), R.layout.listview_row_moduletime, moduleTimes);
        moduleTimeAdapter.setFragmentManager(getFragmentManager());
        moduleTimeAdapter.setParentFragment(this);


        View mtListHeader = (View)inflater.inflate(R.layout.listview_header_moduletime, null);
        lView.addHeaderView(mtListHeader);

        lView.setAdapter(moduleTimeAdapter);

        // Inflate the layout for this fragment
        return mtFragmentLayout;
    }

    @Override
    public void onClick(View v){



    }


    public void setModuleTimeStartTime(ModuleTime selectedModuleTime, Time time){

        System.out.println("T1");

        int index = moduleTimes.indexOf(selectedModuleTime);
        //System.out.println("index of selected module in parent list is: " + index);

        //System.out.println("New time = " + time.getHours() +":" + time.getMinutes());

        moduleTimes.get(index).setStartTime(time);

        moduleTimeAdapter.notifyDataSetChanged();

    }

    public void setModuleTimeEndTime(ModuleTime selectedModuleTime, Time time){

        System.out.println("T2");

        int index = moduleTimes.indexOf(selectedModuleTime);

        moduleTimes.get(index).setEndTime(time);

        moduleTimeAdapter.notifyDataSetChanged();

    }

    public void setModuleTimeNotifyState(ModuleTime selectedModuleTime, boolean checked){

        System.out.println("T3");

        int index = moduleTimes.indexOf(selectedModuleTime);
        System.out.println("index of notify selected module in parent list is: " + index);

        moduleTimes.get(index).setNotification(checked);

        moduleTimeAdapter.notifyDataSetChanged();

    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onModuletimeFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnModuletimeFragmentInteractionListener) activity;
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


    public interface OnModuletimeFragmentInteractionListener {
        public void onModuletimeFragmentInteraction(Uri uri);

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

    }


}
