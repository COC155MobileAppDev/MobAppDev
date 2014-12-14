package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
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
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Johnbastian on 12/12/2014.
 */
public class ImageAdapter extends ArrayAdapter{

    Context context;
    int layoutResourceID;
    ArrayList<Image> images;

    public ImageAdapter(Context c, int lrID, ArrayList<Image> imgList){
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

        holder.imageSquareDisplayView = (SquareDisplayImageView)square.findViewById(R.id.imageSquareDisplayView);
        holder.imageTitleTextView = (TextView)square.findViewById(R.id.imageTitleTextView);

        square.setTag(holder);

        //TODO Make task ASYNC
        File imagePath = FileManager.getImageFileFormat(getContext(), currentImage.getFile());
        Bitmap imageBitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath.getAbsolutePath()), 300, 300);

        holder.imageSquareDisplayView.setImageBitmap(imageBitmap);
        holder.imageTitleTextView.setText(currentImage.getFile());

        return square;
    }

    static class ImageSquareHolder{

        SquareDisplayImageView imageSquareDisplayView;
        TextView imageTitleTextView;

    }



}
