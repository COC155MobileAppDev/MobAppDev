package com.example.team05.lecturec.DataTypes;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Image {

    private int id;
    private String file;

    public Image(int i, String f){

        id = i;
        file = f;

    }

    public int getID(){	return id;  }
    public String getFile(){ return file;   }

}
