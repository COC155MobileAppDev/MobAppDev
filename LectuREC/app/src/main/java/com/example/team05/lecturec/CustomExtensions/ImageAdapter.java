package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team05.lecturec.R;
import com.example.team05.lecturec.ViewControllers.ImagesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernandoalvan on 11/12/14.
 */
public class ImageAdapter extends BaseAdapter {

    public List<Item> images = new ArrayList<Item>();
    private LayoutInflater inflater;

    public ImageAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
        images.add(new Item("Image 0", R.drawable.sample_0));
        images.add(new Item("Image 1", R.drawable.sample_1));
        images.add(new Item("Image 2", R.drawable.sample_2));
        images.add(new Item("Image 3", R.drawable.sample_3));
        images.add(new Item("Image 4", R.drawable.sample_4));
        images.add(new Item("Image 5", R.drawable.sample_5));
        images.add(new Item("Image 0", R.drawable.sample_0));
        images.add(new Item("Image 1", R.drawable.sample_1));
        images.add(new Item("Image 2", R.drawable.sample_2));
        images.add(new Item("Image 3", R.drawable.sample_3));
        images.add(new Item("Image 4", R.drawable.sample_4));
        images.add(new Item("Image 5", R.drawable.sample_5));
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i)
    {
        return images.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return images.get(i).drawableId;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View iView = view;
        ImageView picture;
        TextView name;

        if(iView == null)
        {
            iView = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            iView.setTag(R.id.picture, iView.findViewById(R.id.picture));
            iView.setTag(R.id.text, iView.findViewById(R.id.text));

        }

        picture = (ImageView)iView.getTag(R.id.picture);
        name = (TextView)iView.getTag(R.id.text);

        Item item = (Item)getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return iView;
    }

    private class Item
    {
        final String name;
        final int drawableId;

        Item(String name, int drawableId)
        {
            this.name = name;
            this.drawableId = drawableId;
        }
    }



}

