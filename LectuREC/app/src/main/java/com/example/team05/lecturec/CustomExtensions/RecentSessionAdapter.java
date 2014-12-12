package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.util.ArrayList;

/**
 * Created by Johnbastian on 12/12/2014.
 */
public class RecentSessionAdapter extends ArrayAdapter<Session> {

    Context context;
    int layoutResourceID;
    ArrayList<Session> sessions;

    public RecentSessionAdapter(Context c, int lrID, ArrayList<Session> sessList){
        super(c, lrID, sessList);

        context = c;
        layoutResourceID = lrID;
        sessions = sessList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        RecentSessionRowHolder holder;

        Session session = sessions.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        if (row == null) row = inflater.inflate(layoutResourceID, parent, false);
        else holder = (RecentSessionRowHolder) row.getTag();

        holder = new RecentSessionRowHolder();

        holder.recentSessionNameTextView = (TextView)row.findViewById(R.id.recentSessionNameTextView);
        holder.recentSessionNameTextView.setText(session.getName());

        holder.recentFolderNameTextView = (TextView)row.findViewById(R.id.recentFolderNameTextView);
        holder.recentFolderNameTextView.setText("root");

        row.setTag(holder);


        return row;

    }

    static class RecentSessionRowHolder{

        TextView recentSessionNameTextView;
        TextView recentFolderNameTextView;

    }

}
