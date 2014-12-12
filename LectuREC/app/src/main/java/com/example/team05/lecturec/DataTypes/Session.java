package com.example.team05.lecturec.DataTypes;

import java.io.Serializable;
import java.util.ArrayList;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Session implements Serializable {

    private int id;
    private String name;
    private int folderID;

    private ArrayList<Audio> audios;
    private ArrayList<Image> images;

    public Session(int i, String n, int fID){

        id = i;
        name = n;
        folderID = fID;

    }
    //Setters
    public void setAudios(ArrayList<Audio> aList){    audios = aList;    }
    public void setImages(ArrayList<Image> iList){    images = iList; }


    //Getters
    public int getID(){	return id;  }
    public String getName(){	return name;  }
    public int getFolderID(){	return folderID;  }

    public ArrayList<Audio> getAudios(){    return audios;  }
    public ArrayList<Image> getImages(){    return images;  }

}
