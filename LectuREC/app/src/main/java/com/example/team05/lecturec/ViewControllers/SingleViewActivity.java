package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.example.team05.lecturec.CustomExtensions.ImageAdapter;
import com.example.team05.lecturec.R;

public class SingleViewActivity extends Activity {

    private ScaleGestureDetector SGD;
    private ImageView imageView;
    private float scale = 1f;
    private Matrix matrix = new Matrix();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleview);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        imageView = (ImageView) findViewById(R.id.SingleView);

        // to be completed. Needs passing image from item arraylist
        imageView.setImageResource(R.drawable.sample_0);


        matrix = new Matrix();
        SGD = new ScaleGestureDetector(this, new ScaleListener());




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        SGD.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            matrix.setScale(scale, scale);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }

}