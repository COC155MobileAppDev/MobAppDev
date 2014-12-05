package com.example.team05.lecturec.Controllers;

import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.DataTypes.ModuleTime;
import com.example.team05.lecturec.DataTypes.Time;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Johnbastian on 05/12/2014.
 */
public class ModuleDummyTesting {

    public static ArrayList<Module> getModuleList(){
        return dummyList("Module ", false);
    }

    public static ArrayList<Module> getArchiveList(){
        return dummyList("Archive ", true);
    }


    private static ArrayList<Module> dummyList(String mType, boolean aState){

        ArrayList<Module> moduleArrayList = new ArrayList<Module>();

        for (int c = 0; c < 10; c++){
            
            Module module = new Module(c, (mType + c));
            module.setArchiveState(aState);

            ArrayList<ModuleTime> mTimes = new ArrayList<ModuleTime>();

            for (int c2 = (c*2); c2 < ((c*2) + 1); c2++){
                
                int randomDay = (new Random()).nextInt(6);
                int randomTime = (new Random()).nextInt(9) + 9;

                Time sTime = new Time(randomTime, 0, 0);
                Time eTime = new Time(randomTime+1, 0, 0);

                ModuleTime moduleTime = new ModuleTime(c2, randomDay, sTime, eTime, true);

                mTimes.add(moduleTime);

            }

            module.setModuleTimes(mTimes);

            moduleArrayList.add(module);

        }

        return moduleArrayList;
        
    }

}
