package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;

import com.example.team05.lecturec.DataTypes.*;

import java.util.ArrayList;

/**
 * Created by Johnbastian on 13/12/2014.
 */
public class ModuleSessionsAdapter extends ArrayAdapter {

    Context context;
    int layoutResourceID;
    ArrayList<Folder> folders;

    public ModuleSessionsAdapter(Context context, int lrID, ArrayList<Folder> folderArrayList) {
        super(context, lrID, folderArrayList);

    }



}
