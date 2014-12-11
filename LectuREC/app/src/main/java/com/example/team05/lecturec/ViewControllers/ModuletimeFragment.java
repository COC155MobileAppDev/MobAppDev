package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.team05.lecturec.CustomExtensions.ModuleTimeAdapter;
import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.DataTypes.Time;
import com.example.team05.lecturec.R;

import java.util.ArrayList;


public class ModuleTimeFragment extends Fragment {

    private OnModuletimeFragmentInteractionListener mListener;

    private NewModuleActivity parentActivity;

    int day;
    ModuleTimeAdapter moduleTimeAdapter;

    private FrameLayout mtFragmentLayout;
    private ListView lView;
    private Button addNewTimeButton;

    private int newModuleTimeCounter = 0;

    private ArrayList<ModuleTime> moduleTimes;


    public ModuleTimeFragment() {
        // Required empty public constructor
    }

    public static ModuleTimeFragment newInstance(String param1, String param2) {
        ModuleTimeFragment fragment = new ModuleTimeFragment();
        Bundle args = new Bundle();
        //put stuff in bundle if needed
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (NewModuleActivity)getActivity();

        Bundle bundle = getArguments();

        day = bundle.getInt("day");
        moduleTimes = (ArrayList<ModuleTime>)bundle.get("moduleTimes");

        System.out.println("Day val: " + day);
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
        View mtListFooter = (View)inflater.inflate(R.layout.listview_footer_moduletime, null);
        lView.addFooterView(mtListFooter);

        lView.setAdapter(moduleTimeAdapter);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("called");

                switch (v.getId()) {
                    case R.id.addNewTimeBtn:
                        addNewModuleTime(v);
                        break;
                }


            }
        };

        addNewTimeButton = (Button)mtFragmentLayout.findViewById(R.id.addNewTimeBtn);
        addNewTimeButton.setOnClickListener(onClickListener);


        // Inflate the layout for this fragment
        return mtFragmentLayout;
    }



    //Add and delete module time managing methods
    private void addNewModuleTime(View v){

        System.out.println("addNewModuleTime called");

        //System.out.println("old val of newModuleCounter: " + );

        Time defaultTime = new Time(0, 0, 0);

        ModuleTime newModuleTime =
                new ModuleTime(parentActivity.getNewModuleTimeCounter(), day, defaultTime, defaultTime, true );

        //System.out.println("new val of newModuleCounter: " + newModuleTimeCounter);

        moduleTimes.add(newModuleTime);

        parentActivity.addToNewList(newModuleTime);

        moduleTimeAdapter.notifyDataSetChanged();


    }

    public void setModuleTimeDeletion(ModuleTime selectedModuleTime){

        int index = moduleTimes.indexOf(selectedModuleTime);

        parentActivity.addToDeleteList(moduleTimes.get(index));

        moduleTimes.remove(index);

        moduleTimeAdapter.notifyDataSetChanged();

    }



    //Data changing methods
    public void setModuleTimeStartTime(ModuleTime selectedModuleTime, Time time){

        System.out.println("T1");

        int index = moduleTimes.indexOf(selectedModuleTime);

        moduleTimes.get(index).setStartTime(time);

        parentActivity.addToEditList(moduleTimes.get(index));

        moduleTimeAdapter.notifyDataSetChanged();

    }

    public void setModuleTimeEndTime(ModuleTime selectedModuleTime, Time time){

        System.out.println("T2");

        int index = moduleTimes.indexOf(selectedModuleTime);

        moduleTimes.get(index).setEndTime(time);

        parentActivity.addToEditList(moduleTimes.get(index));

        moduleTimeAdapter.notifyDataSetChanged();

    }

    public void setModuleTimeNotifyState(ModuleTime selectedModuleTime, boolean checked){

        System.out.println("T3");

        int index = moduleTimes.indexOf(selectedModuleTime);

        moduleTimes.get(index).setNotification(checked);

        parentActivity.addToEditList(moduleTimes.get(index));

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
