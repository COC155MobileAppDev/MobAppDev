package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.team05.lecturec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernandoalvan on 11/12/14.
 */
public class ImageAdapter extends BaseAdapter {

    private List<Item> images = new ArrayList<Item>();
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


     /*
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // Keep all Images in array

    public Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
    */
