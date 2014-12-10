package com.example.team05.lecturec.DataTypes;

import java.io.Serializable;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Folder implements Serializable {

    private int id;
    private String name;

    public Folder(int i, String n){

        id = i;
        name = n;

    }

    public int getID(){	return id;  }
    public String getName(){	return name;  }

}
