package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.team05.lecturec.DataTypes.Audio;

import java.util.ArrayList;

/**
 * Created by fernandoalvan on 12/12/14.
 */
public class AudioAdapter extends ArrayAdapter<Audio> {

    Context context;
    int layoutResourceID;
    ArrayList<Audio> audios;

    public AudioAdapter(Context context, int resource) {
        super(context, resource);
    }
}
