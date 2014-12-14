package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.CustomExtensions.AudioAdapter;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class AudioFragment extends Fragment {



    private OnAudioFragmentInteractionListener mListener;

    private FrameLayout fragmentLayout;
    private ListView audioListView;

    private ArrayList<Audio> audios;
    private File currentAudioFile;


    private MediaPlayer mediaPlayer;



    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle passedBundle = getArguments();
        audios = (ArrayList<Audio>)passedBundle.get("audioList");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        fragmentLayout = (FrameLayout) inflater.inflate(R.layout.fragment_audio, container, false);

        // Locate the ListView in listview.xml
        audioListView = (ListView) fragmentLayout.findViewById(R.id.audioListView);

        AudioAdapter audioAdapter = new AudioAdapter(getActivity().getApplicationContext(), R.layout.listview_row_audio, audios);

        audioListView.setAdapter(audioAdapter);
        audioListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // Capture ListView item click
        audioListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = audioListView.getCheckedItemCount();
                // Set the CAB title according to total checked items
                actionMode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                //arrayAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                //Toast.makeText((getActivity()).getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                return true;
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

        audioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                currentAudioFile = FileManager.getAudioFileFormat(getActivity().getApplicationContext(), audios.get(position).getFile());

                Toast.makeText((getActivity()).getApplicationContext(),"Playing - " + audios.get(position).getFile(), Toast.LENGTH_SHORT).show();

                mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), Uri.fromFile(currentAudioFile));
                mediaPlayer.start();

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
