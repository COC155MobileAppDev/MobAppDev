package com.example.team05.lecturec.DataTypes;

import java.sql.Time;

/*
  Created by Johnbastian on 01/12/2014.
 */
public class ModuleTime {

    private int id;
    private int day;
    private Time startTime;
    private Time endTime;
    private boolean notification;

    public ModuleTime(int i, int d, Time sT, Time eT, boolean n){

        id = i;
        day = d;
        startTime = sT;
        endTime = eT;
        notification = n;

    }

    public int getID(){	return id;  }
    public int getDay(){    return day; }
    public Time getStart(){	return startTime;  }
    public Time getEnd(){	return endTime;  }
    public boolean getNotificationState(){	return notification;  }

}
