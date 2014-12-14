package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by fernandoalvan on 12/12/14.
 */
public class AudioAdapter extends ArrayAdapter<Audio> {

    Context context;
    int layoutResourceID;
    ArrayList<Audio> audios;

    public AudioAdapter(Context c, int lrID, ArrayList<Audio> audioList){
        super(c, lrID, audioList);

        context = c;
        layoutResourceID = lrID;
        audios = audioList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        View row = view;
        AudioRowHolder holder;

        Audio currentAudio = audios.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        if(row == null) row = inflater.inflate(layoutResourceID, parent, false);
        else holder = (AudioRowHolder) row.getTag();

        holder = new AudioRowHolder();

        holder.audioNameTextView = (TextView)row.findViewById(R.id.audioNameTextView);
        holder.audioDurationTextView = (TextView)row.findViewById(R.id.audioDurationTextView);

        row.setTag(holder);

        holder.audioNameTextView.setText(currentAudio.getFile());

        int currentDuration = currentAudio.getDuration();
        String durationString =
                String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(currentDuration),
                        (TimeUnit.MILLISECONDS.toMinutes(currentDuration) - (TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toMinutes(currentDuration)))),
                        (TimeUnit.MILLISECONDS.toSeconds(currentDuration) - (TimeUnit.MINUTES.toMinutes(TimeUnit.MILLISECONDS.toMinutes(currentDuration))))
                );

        holder.audioDurationTextView.setText(durationString);

        return row;
    }

    static class AudioRowHolder{

        TextView audioNameTextView;
        TextView audioDurationTextView;

    }

}
