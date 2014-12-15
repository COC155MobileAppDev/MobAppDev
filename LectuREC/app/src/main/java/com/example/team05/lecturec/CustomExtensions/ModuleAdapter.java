package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team05.lecturec.DataTypes.Module;

import com.example.team05.lecturec.DataTypes.Session;
import com.example.team05.lecturec.R;

import java.util.ArrayList;

/*
 Created by Johnbastian on 10/12/2014.
 */
public class ModuleAdapter extends ArrayAdapter<Module> {

    Context context;
    int layoutResourceID;
    ArrayList<Module> modules;

    public ModuleAdapter(Context c, int lrID, ArrayList<Module> modList){
        super(c, lrID, modList);

        context = c;
        layoutResourceID = lrID;
        modules = modList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ModuleRowHolder holder;
        
        Module module = modules.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        if (row == null) row = inflater.inflate(layoutResourceID, parent, false);
        else holder = (ModuleRowHolder) row.getTag();

        holder = new ModuleRowHolder();

        holder.moduleTitleLabel = (TextView)row.findViewById(R.id.moduleTitleTextView);
        holder.lastSessionLabel = (TextView)row.findViewById(R.id.lastSessionTextView);
        holder.arrowLabel = (TextView)row.findViewById(R.id.arrowTextView);
            holder.arrowLabel.setText("▶ ");

        row.setTag(holder);

        holder.moduleTitleLabel.setText(module.getName());
        ArrayList<Session> sessionArrayList = module.getSessions();
        int size = sessionArrayList.size();
        if (size > 0)
            holder.lastSessionLabel.setText("Last Session: " + sessionArrayList.get(size - 1).getName());

        holder.lastSessionLabel.setText("Last Session: " + module.getSessions().get(module.getSessions().size() - 1).getName());

        return row;

    }



    static class ModuleRowHolder{

        TextView moduleTitleLabel;
        TextView lastSessionLabel;
        TextView arrowLabel;

    }

}
