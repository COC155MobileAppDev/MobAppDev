package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
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


public class AudioFragment extends Fragment {



    private OnAudioFragmentInteractionListener mListener;

    private FrameLayout fragmentLayout;
    ListView audioListview;

    private ArrayList<String> archiveNames;

    private ArrayList<String> songList;


    private MediaPlayer mediaPlayer;



    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        songList = ((SelectedsessionActivity)getActivity()).getSongList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentLayout = (FrameLayout) inflater.inflate(
                R.layout.fragment_audio, container, false);

        // Generate sample data into string arrays


        // Locate the ListView in listview.xml
        audioListview = (ListView) fragmentLayout.findViewById(R.id.listview);

        /*
        archiveNames = new ArrayList<String>();
        archiveNames.add("Amy");
        archiveNames.add("Amy");
        archiveNames.add("Amy");
        archiveNames.add("Amy");

        */

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((getActivity()).getApplicationContext(), android.R.layout.simple_list_item_activated_1, songList);

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
