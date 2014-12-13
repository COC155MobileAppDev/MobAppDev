package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.R;

import java.io.File;

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
        Bundle passedBundle = getIntent().getExtras();

        // Selected image id
        //int position = i.getExtras().getInt("id");

        String imageFileName = passedBundle.getString("imageFilePath");
        File imageFilePath = FileManager.getImageFileFormat(getApplicationContext(), imageFileName);

        imageView = (ImageView) findViewById(R.id.SingleView);

        // to be completed. Needs passing image from item arraylist
        imageView.setImageURI(Uri.fromFile(imageFilePath));


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