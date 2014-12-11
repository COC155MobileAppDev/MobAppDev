package com.example.team05.lecturec.DataTypes;

import java.io.Serializable;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Session implements Serializable {

    private int id;
    private String name;
    private int folderID;

    private Audio[] audios;
    private Image[] images;

    public Session(int i, String n, int fID){

        id = i;
        name = n;
        folderID = fID;

    }

    public int getID(){	return id;  }
    public String getName(){	return name;  }
    public int getFolderID(){	return folderID;  }

}
