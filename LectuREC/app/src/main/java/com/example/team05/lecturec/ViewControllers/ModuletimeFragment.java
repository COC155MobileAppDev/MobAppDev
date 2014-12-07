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
import com.example.team05.lecturec.R;

import java.util.ArrayList;


public class ModuletimeFragment extends Fragment {

    private OnModuletimeFragmentInteractionListener mListener;

    private FrameLayout mtFragmentLayout;
    private ListView lView;

    private ArrayList<ModuleTime> moduleTimes;

    public static ModuletimeFragment newInstance(String param1, String param2) {
        ModuletimeFragment fragment = new ModuletimeFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ModuletimeFragment() {
        // Required empty public constructor
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

        ModuleTimeAdapter mtAdapter = new ModuleTimeAdapter(getActivity().getApplicationContext(), R.layout.listview_row_moduletime, moduleTimes);
        mtAdapter.setFragmentManager(getFragmentManager());


        View mtListHeader = (View)inflater.inflate(R.layout.listview_header_moduletime, null);
        lView.addHeaderView(mtListHeader);

        lView.setAdapter(mtAdapter);

        // Inflate the layout for this fragment
        return mtFragmentLayout;
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
