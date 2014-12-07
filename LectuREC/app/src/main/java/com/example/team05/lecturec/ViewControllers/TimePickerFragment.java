package com.example.team05.lecturec.ViewControllers;


import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.team05.lecturec.R;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener{

    private View callerView; //The view/element that called this fragment

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
        // Do something with the time chosen by the user
       // txtTime.setText(hourOfDay+":"+minute);

        Button btn = ((Button) callerView);

        btn.setText(hourOfDay + " : " + minute);

    }
}
