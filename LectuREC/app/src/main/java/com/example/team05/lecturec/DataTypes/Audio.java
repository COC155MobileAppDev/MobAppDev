package com.example.team05.lecturec.DataTypes;

/*
 Created by Johnbastian on 01/12/2014.
*/

public class Audio {

    private int id;
    private String file;
    private Time startTime;
    private int duration;

    public Audio(int i, String f, Time sT, int d){

        id = i;
        file = f;
        startTime = sT;
        duration = d;

    }

    public int getID(){	return id;  }
    public String getFile(){	return file;  }
    public Time getStartTime() {	return startTime;	}
    public int getDuration() { return duration;	}

}
