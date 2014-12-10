package com.example.team05.lecturec.Controllers;

import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.ModuleTime;

import java.util.ArrayList;

/**
 * Created by Johnbastian on 10/12/2014.
 */
public class DataManager {

    public static void addNewModule(Module newModule, ArrayList<ModuleTime> newModuleTimes){

        System.out.println("Save NEW pressed");



    }

    public static void editExistingModule(Module currentModule, boolean nameChange, ArrayList<ModuleTime> newModuleTimes,
                                   ArrayList<ModuleTime> editingModuleTimes, ArrayList<ModuleTime> deletingModuleTimes){


        System.out.println("Save EDIT pressed \n");

        System.out.println(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n \n",
                        currentModule.getID(),
                        currentModule.getName(),
                        currentModule.getArchiveState()));

        System.out.println("Name Change State is: " + nameChange + "");

        //TODO finish this for testing and introduce Prash's work

    }

    public static void archiveExistingModule(Module currentModule){

        System.out.println("Archive pressed");

        System.out.print(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n",
                        currentModule.getID(),
                        currentModule.getName(),
                        currentModule.getArchiveState()));

    }

    public static void deletingExistingModule(Module deletingModule){

        System.out.println("Delete pressed");

        System.out.print(
                String.format("Current module is: \n "
                                + "x       ID: %d \n "
                                + "x       Name: %s \n "
                                + "x       Archive: %b \n",
                        deletingModule.getID(),
                        deletingModule.getName(),
                        deletingModule.getArchiveState()));

    }



}
