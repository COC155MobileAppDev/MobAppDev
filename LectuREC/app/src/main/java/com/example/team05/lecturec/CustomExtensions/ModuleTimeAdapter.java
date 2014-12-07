package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;

import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.R;
import com.example.team05.lecturec.ViewControllers.TimePickerFragment;

import java.util.ArrayList;

/*
 Created by Johnbastian on 06/12/2014.
 */
public class ModuleTimeAdapter extends ArrayAdapter<ModuleTime> {

    FragmentManager fragmentManager;

    Context context;
    int layoutResourceID;
    ArrayList<ModuleTime> moduleTimes;

    public ModuleTimeAdapter(Context c, int lrID, ArrayList<ModuleTime> mTimes){
        super(c, lrID, mTimes);

        context = c;
        layoutResourceID = lrID;
        moduleTimes = mTimes;

    }

    public void setFragmentManager(FragmentManager fm){
        fragmentManager = fm;
    }

    @Override
    public View getView(int position, View view, final ViewGroup parent){

        View row = view;
        RowHolder holder;


        if (row == null){



            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceID, parent, false);


            holder = new RowHolder();
            holder.startTimeButton = (Button)row.findViewById(R.id.startTimeBtn);

            holder.startTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerFragment newFragment = new TimePickerFragment();
                    newFragment.setCallerView(v);

                    newFragment.show(fragmentManager, "timePicker");
                }
            });

            holder.endTimeButton = (Button)row.findViewById(R.id.endTimeBtn);
            holder.endTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerFragment newFragment = new TimePickerFragment();
                    newFragment.setCallerView(v);

                    newFragment.show(fragmentManager, "timePicker");

                }
            });


            holder.notifySwitch = (Switch)row.findViewById(R.id.notifySwitch);

            row.setTag(holder);

        }else {

            holder = (RowHolder)row.getTag();

        }

        ModuleTime moduleTime = moduleTimes.get(position);


        String hours;
        String minutes;

        hours = Integer.toString(moduleTime.getStart().getHours());
        minutes = Integer.toString(moduleTime.getStart().getMinutes());

        holder.startTimeButton.setText(hours + ":" + minutes);

        hours = Integer.toString(moduleTime.getEnd().getHours());
        minutes = Integer.toString(moduleTime.getEnd().getMinutes());

        holder.endTimeButton.setText(hours + ":" + minutes);

        holder.notifySwitch.setChecked(moduleTime.getNotificationState());


        return row;

    }


    static class RowHolder{

        Button startTimeButton;
        Button endTimeButton;
        Switch notifySwitch;

    }



}
