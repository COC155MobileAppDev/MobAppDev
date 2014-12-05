package com.example.team05.lecturec.DataTypes;

/*
 Created by Johnbastian on 01/12/2014.
*/

public class Audio {

    private int id;
    private String name;
    //private data file;	- need to find out appropriate object/data type
    private Time startTime;
    private int duration; //in seconds

    public Audio(int i, String n, Time sT, int d){

        id = i;
        name = n;
        startTime = sT;
        duration = d;

    }

    public int getID(){	return id;  }
    public String getName(){	return name;  }
    public Time getStartTime() {	return startTime;	}
    public int getDuration() { return duration;	}

}
