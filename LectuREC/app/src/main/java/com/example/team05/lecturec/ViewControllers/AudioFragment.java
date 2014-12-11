package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.team05.lecturec.R;
import android.widget.AbsListView.MultiChoiceModeListener;

import java.util.ArrayList;
import java.util.List;

public class AudioFragment extends Fragment {

;

    private OnAudioFragmentInteractionListener mListener;

    private FrameLayout fragmentLayout;
    ListView audioListview;

    private ArrayList<String> archiveNames;

    String[] population;




    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_audio, container, false);

        // Generate sample data into string arrays


        population = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };

        // Locate the ListView in listview_main.xml
        audioListview = (ListView) fragmentLayout.findViewById(R.id.listview);

        archiveNames = new ArrayList<String>();
        archiveNames.add("Amy");
        archiveNames.add("Amy");
        archiveNames.add("Amy");
        archiveNames.add("Amy");



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((getActivity()).getApplicationContext(), android.R.layout.simple_list_item_activated_1, population);

        audioListview.setAdapter(arrayAdapter);
        audioListview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // Capture ListView item click
        audioListview.setMultiChoiceModeListener(new MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = audioListview.getCheckedItemCount();
                // Set the CAB title according to total checked items
                actionMode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                //arrayAdapter.toggleSelection(position);
            }



            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;




            }


            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

                actionMode.getMenuInflater().inflate(R.menu.menu_selectedsession, menu);
                return true;
                //return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        // Inflate the layout for this fragment
        return fragmentLayout;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAudioFragmentInteractionListener) activity;
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

    public interface OnAudioFragmentInteractionListener {
        // TODO: Update argument type and name
        public void OnAudioFragmentInteractionListener(Uri uri);
    }


}
