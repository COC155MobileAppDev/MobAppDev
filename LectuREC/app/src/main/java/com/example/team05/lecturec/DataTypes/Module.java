package com.example.team05.lecturec.DataTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/*
 Created by Johnbastian on 01/12/2014.
 */
public class Module implements Serializable {

    private int id;
    private String name;
    private boolean archived;
    private ArrayList<ModuleTime> moduleTimes = new ArrayList<ModuleTime>();

    private ArrayList<Session> sessions = new ArrayList<Session>();
    private ArrayList<Folder> folders = new ArrayList<Folder>();


    public Module(int i, String n){

        id = i;
        name = n;
        archived = false;

        System.out.println(name);

    }

    //Setters
    public void setName(String n){  name = n;   }
    public void setArchiveState(Boolean state){ archived = state;   }
    public void setModuleTimes(ArrayList<ModuleTime> mTimes) {  moduleTimes = mTimes;   }
    public void setSessions(ArrayList<Session> sessionArrayList){   sessions = sessionArrayList;    }
    public void setFolders(ArrayList<Folder> folderArrayList){  folders = folderArrayList;  }

    //Getters
    public int getID(){	return id;  }
    public String getName(){	return name;  }
    public boolean getArchiveState(){	return archived;  }
    public ArrayList<ModuleTime> getModuleTimes() { return moduleTimes; }
    public ArrayList<Session> getSessions(){ return sessions;   }
    public ArrayList<Folder> getFolders(){  return folders; }



    //ModuleTime Editors
    public void addModuleTime(ModuleTime mt){
        moduleTimes.add(mt);
    }

    public void removeModuleTime(int mtID){
        
        for (Iterator<ModuleTime> iterator = moduleTimes.iterator(); iterator.hasNext();){

            ModuleTime mt = iterator.next();
            if (mt.getID() == mtID) iterator.remove();

        }

    }


    //Session Editors
    public void addSession(Session session){
        sessions.add(session);
    }

    public void removeSession(int sID){

        for (Iterator<Session> iterator = sessions.iterator(); iterator.hasNext();){

            Session ss = iterator.next();
            if (ss.getID() == sID) iterator.remove();

        }

    }


    //Folder Editors
    public void addFolder(Folder folder){
        folders.add(folder);
    }


}
