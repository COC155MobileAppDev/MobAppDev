package com.example.team05.lecturec.DataTypes;

import java.util.ArrayList;
import java.util.Iterator;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Module {

    private int id;
    private String name;
    private boolean archived;
    private ArrayList<ModuleTime> moduleTimes;

    private Session[] sessions;


    public Module(int i, String n, boolean a){

        id = i;
        name = n;
        archived = a;

    }

    public int getID(){	return id;  }
    public String getName(){	return name;  }
    public boolean getArchiveState(){	return archived;  }


    public void setName(String n){ name = n; }

    public void addModuleTime(ModuleTime mt){ moduleTimes.add(mt); }
    public void removeModuleTime(int mtID){
        
        for (Iterator<ModuleTime> iterator = moduleTimes.iterator(); iterator.hasNext();){

            ModuleTime mt = iterator.next();
            if (mt.getID() == mtID) iterator.remove();

        }

    }
}
