package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.team05.lecturec.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModuleFragment.OnModuleFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModuleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModuleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView arrayAdapter;
    private String[] stringArray ;
    private ArrayAdapter tweetItemArrayAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnModuleFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModuleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModuleFragment newInstance(String param1, String param2) {
        ModuleFragment fragment = new ModuleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ModuleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        stringArray = new String[10];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;
        }
        /*
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , stringArray);
        arrayAdapter = (ListView) findViewById(R.id.listViewId);
        arrayAdapter.setAdapter(tweetItemArrayAdapter);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module, container, false);



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.OnModuleFragmentInteractionListener(uri);
        }
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
