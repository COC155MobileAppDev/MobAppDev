package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.DataTypes.*;
import com.example.team05.lecturec.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Johnbastian on 12/12/2014.
 */
public class ImageAdapater extends ArrayAdapter{

    Context context;
    int layoutResourceID;
    ArrayList<Image> images;

    public ImageAdapater(Context c, int lrID, ArrayList<Image> imgList){
        super(c, lrID, imgList);

        context = c;
        layoutResourceID = lrID;
        images = imgList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        View square = view;
        ImageSquareHolder holder;

        Image currentImage = images.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        if(square == null) square = inflater.inflate(layoutResourceID, parent, false);
        else holder = (ImageSquareHolder) square.getTag();

        holder = new ImageSquareHolder();

        holder.imageSquareDisplayView = (ImageView)square.findViewById(R.id.imageSquareDisplayView);
        holder.imageTitleTextView = (TextView)square.findViewById(R.id.imageTitleTextView);

        square.setTag(holder);

        File imagePath = FileManager.getImageFileFormat(getContext(), currentImage.getFile());

        holder.imageSquareDisplayView.setImageURI(Uri.fromFile(imagePath));
        holder.imageTitleTextView.setText(currentImage.getFile());

        return square;
    }

    static class ImageSquareHolder{

        ImageView imageSquareDisplayView;
        TextView imageTitleTextView;

    }



}
