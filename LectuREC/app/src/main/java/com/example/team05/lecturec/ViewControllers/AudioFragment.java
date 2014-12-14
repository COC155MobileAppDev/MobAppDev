package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.CustomExtensions.AudioAdapter;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class AudioFragment extends Fragment {



    private OnAudioFragmentInteractionListener mListener;

    private FrameLayout fragmentLayout;
    private ListView audioListView;

    private ArrayList<Audio> audios;
    private File currentAudioFile;


    public TextView songName,startTimeField,endTimeField;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private ImageButton playButton, pauseButton, rewindButton, forwardButton;
    public static int oneTimeOnly = 0;
    private boolean isPaused = true;



    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle passedBundle = getArguments();
        audios = (ArrayList<Audio>)passedBundle.get("audioList");


    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            startTimeField.setText(String.format("%d min, %d sec",
                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        fragmentLayout = (FrameLayout) inflater.inflate(R.layout.fragment_audio, container, false);




        songName = (TextView)fragmentLayout.findViewById(R.id.songName);
        startTimeField =(TextView)fragmentLayout.findViewById(R.id.startTimeTxt);
        endTimeField =(TextView)fragmentLayout.findViewById(R.id.endTimeTxt);
        seekbar = (SeekBar)fragmentLayout.findViewById(R.id.seekBar1);
        playButton = (ImageButton)fragmentLayout.findViewById(R.id.playButton);
        pauseButton = (ImageButton)fragmentLayout.findViewById(R.id.pauseButton);
        rewindButton = (ImageButton) fragmentLayout.findViewById(R.id.rewindButton);
        forwardButton = (ImageButton) fragmentLayout.findViewById(R.id.forwardButton);

        seekbar.setClickable(false);
        playButton.setEnabled(false);
        pauseButton.setEnabled(false);

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
                isPaused = false;
                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                if(oneTimeOnly == 0){
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                endTimeField.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                                toMinutes((long) finalTime)))
                );
                startTimeField.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                                toMinutes((long) startTime)))
                );
                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                pauseButton.setEnabled(true);
                playButton.setEnabled(false);

            }
        });


        playButton.setOnClickListener(onClickListener);
        pauseButton.setOnClickListener(onClickListener);
        rewindButton.setOnClickListener(onClickListener);
        forwardButton.setOnClickListener(onClickListener);


        // Inflate the layout for this fragment
        return fragmentLayout;
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.playButton:
                    onPlayButtonClick();
                    break;
                case R.id.pauseButton:
                    onPauseButtonClick();
                    break;
                case R.id.rewindButton:
                    onRewindButtonClick();
                    break;
                case R.id.forwardButton:
                    onForwardButtonClick();
                    break;

            }
        }
    };



    public void onPlayButtonClick(){
        if (isPaused){
            mediaPlayer.start();
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            isPaused = false;
        }

    }

    public void onPauseButtonClick(){
        Toast.makeText(getActivity().getApplicationContext(), "Pausing sound",
                Toast.LENGTH_SHORT).show();

        mediaPlayer.pause();
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
        isPaused = true;

    }

    public void onRewindButtonClick(){
        int temp = (int)startTime;
        if((temp-backwardTime)>0){
            startTime = startTime - backwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(),
                    "Cannot jump backward 5 seconds",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void onForwardButtonClick(){
        int temp = (int)startTime;
        if((temp+forwardTime)<=finalTime){
            startTime = startTime + forwardTime;
            mediaPlayer.seekTo((int) startTime);
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(),
                    "Cannot jump forward 5 seconds",
                    Toast.LENGTH_SHORT).show();
        }
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
