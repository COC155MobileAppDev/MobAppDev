package com.example.team05.lecturec.ViewControllers;


import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.DataTypes.Time;
import com.example.team05.lecturec.R;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener{

    private ModuleTimeFragment parentFragment;
    private ModuleTime moduleTime;
    private View callerView; //The view/element that called this fragment

    public void setParentFragmentAndSelectedModuleTime(ModuleTimeFragment mtF, ModuleTime mt){
        parentFragment = mtF;
        moduleTime = mt;
    }
    public void setCallerView(View cV){
        callerView = cV;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (view.isShown()) {

            Time time = new Time(hourOfDay, minute, 0);

            Button btn = ((Button) callerView);

            switch (btn.getId()){
                case R.id.startTimeBtn:
                    parentFragment.setModuleTimeStartTime(moduleTime, time);
                    break;
                case R.id.endTimeBtn:
                    parentFragment.setModuleTimeEndTime(moduleTime, time);
                    break;
            }

        }

    }


}
