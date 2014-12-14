package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Johnbastian on 13/12/2014.
 */
public class ModuleSessionsAdapter extends BaseExpandableListAdapter {

    Context context;
    Module parentModule;
    ArrayList<Folder> folders;
    ArrayList<Session> sessions;

    ArrayList<FolderSession> folderSessions;

    public ModuleSessionsAdapter(Context c, Module pm, ArrayList<Folder> folderArrayList, ArrayList<Session> sessionArrayList) {

        context = c;
        parentModule = pm;
        folders = folderArrayList;
        sessions = sessionArrayList;

        folderSessions = populateFolderSession();

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View row = convertView;
        FolderRowHolder holder;

        Folder currentFolder = folderSessions.get(groupPosition).parentFolder;

        LayoutInflater inflater = LayoutInflater.from(context);

        if (row == null) row = inflater.inflate(R.layout.listview_grouprow_modulesession, parent, false);
        else holder = (FolderRowHolder)row.getTag();

        holder = new FolderRowHolder();

        holder.moduleSessionGroupTextView = (TextView)row.findViewById(R.id.moduleSessionGroupTextView);
        holder.moduleSessionGroupTextView.setText(currentFolder.getName());

        return row;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View row = convertView;
        SessionRowHolder holder;

        Session currentSession = folderSessions.get(groupPosition).childSessions.get(childPosition);

        LayoutInflater inflater = LayoutInflater.from(context);

        if (row == null) row = inflater.inflate(R.layout.listview_childrow_modulesession, parent, false);
        else holder = (SessionRowHolder)row.getTag();

        holder = new SessionRowHolder();

        holder.moduleSessionChildTextView = (TextView)row.findViewById(R.id.moduleSessionChildTextView);
        holder.moduleSessionChildTextView.setText(currentSession.getName());

        return row;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return folderSessions.get(groupPosition).parentFolder.getName();
    }

    @Override
    public long getGroupId(int groupPosition) {
        //return folderSessions.get(groupPosition).parentFolder.getID();
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return folderSessions.size();
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return folderSessions.get(groupPosition).childSessions.get(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        System.out.println("gCC groupPosition is: " + groupPosition);
        System.out.println("gCC folderSess group count is: " + folderSessions.size());


        return folderSessions.get(groupPosition).childSessions.size();
    }

    @Override
    public boolean isChildSelectable (int groupPosition, int childPosition){
        return true;
    }




    //Custom Temporary Data Type for this Adapter
    static class FolderSession{

        Folder parentFolder;
        ArrayList<Session> childSessions;

    }

    private ArrayList<FolderSession> populateFolderSession(){

        ArrayList<FolderSession> newFolderSessions = new ArrayList<FolderSession>();

        Folder rootFolder = new Folder(-1, parentModule.getName() + " (root)");

        FolderSession rootFolderSession = new FolderSession();
        rootFolderSession.parentFolder = rootFolder;
        rootFolderSession.childSessions = new ArrayList<Session>();

        for (Session ss:sessions) if (ss.getFolderID() == 0) rootFolderSession.childSessions.add(ss);

        newFolderSessions.add(rootFolderSession);

        System.out.println("folders count is: " + folders.size());

        for (Folder groupFolder:folders){

            FolderSession newFolderSession = new FolderSession();
            newFolderSession.parentFolder = groupFolder;
            newFolderSession.childSessions = new ArrayList<Session>();


            for (Session childSession:sessions)
                if (groupFolder.getID() == childSession.getFolderID())
                    newFolderSession.childSessions.add(childSession);


            newFolderSessions.add(newFolderSession);

        }

        System.out.println("pFS newFolderSessions count is: " + newFolderSessions.size());


        return newFolderSessions;

    }




    static class FolderRowHolder{
        TextView moduleSessionGroupTextView;
    }

    static class SessionRowHolder{
        TextView moduleSessionChildTextView;
    }



}
