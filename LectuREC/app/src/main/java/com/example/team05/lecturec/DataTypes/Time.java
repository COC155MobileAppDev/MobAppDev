package com.example.team05.lecturec.DataTypes;

import java.io.Serializable;

/**
 * Created by Johnbastian on 05/12/2014.
 */
public class Time implements Serializable {

    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hh, int mm, int ss){

        setHours(hh);
        setMinutes(mm);
        setSeconds(ss);

    }

    // TODO Proper validation for time setting
    //Setters
    public void setHours(int hh) {
        hours = hh;
    }

    public void setMinutes(int mm) {
        minutes = mm;
    }

    public void setSeconds(int ss) {
        seconds = ss;
    }



    //Getters
    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
