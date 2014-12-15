package com.example.team05.lecturec.ViewControllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.example.team05.lecturec.Controllers.DataManager;
import com.example.team05.lecturec.DataTypes.Folder;
import com.example.team05.lecturec.DataTypes.Module;
import com.example.team05.lecturec.R;

import java.util.ArrayList;

/**
 * Created by Johnbastian on 15/12/2014.
 */
public class FolderDialog extends DialogFragment {

    private SelectedSessionActivity parentActivity;
    private Module parentModule;
    private ArrayList<Folder> folders = new ArrayList<Folder>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder folderListBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        ArrayList<String> folderNamesALS = new ArrayList<String>();
        for (Folder f:folders) folderNamesALS.add(f.getName());

        System.out.println("# of folders is: " + folders.size());

        final CharSequence[] folderNames = folderNamesALS.toArray(new CharSequence[folderNamesALS.size()]);

        folderListBuilder.setTitle("Please Select a Folder");
        folderListBuilder.setItems(folderNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                parentActivity.setChangeSelectedFolder(folders.get(which));
            }
        });

        folderListBuilder.setPositiveButton("Create New Folder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog.Builder newFolderBuilder = new AlertDialog.Builder(getActivity());

                newFolderBuilder.setTitle("New Folder");
                newFolderBuilder.setMessage("Please Enter Folder Name");

                final EditText nameInput = new EditText(getActivity());
                newFolderBuilder.setView(nameInput);

                newFolderBuilder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String newFolderName = nameInput.getText().toString();

                        Folder newFolder = new Folder(-1, newFolderName);

                        passNewFolder(newFolder);

                    }
                });

                newFolderBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {    dismiss();  }
                });

                newFolderBuilder.show();
            }
        });

        folderListBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {    dismiss();   }
        });

        return folderListBuilder.create();

    }

    public void setParentActivity(SelectedSessionActivity ssa){ parentActivity = ssa;   }

    public void setParentModule(Module pm){ parentModule = pm;  }

    public void setFolderList(ArrayList<Folder> folderList){    folders = folderList;   }

    private void passNewFolder(Folder newFolder){

        parentActivity.createNewFolder(newFolder);

    }


}
