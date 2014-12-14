package com.example.team05.lecturec.ViewControllers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.team05.lecturec.Controllers.FileManager;
import com.example.team05.lecturec.R;

import java.io.File;

public class SingleViewActivity extends Activity {

    Bitmap imageBitmap;

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
        String imageFileName = passedBundle.getString("imageFilePath");

        //TODO Make task ASYNC and proper Resolution
        File imageFilePath = FileManager.getImageFileFormat(getApplicationContext(), imageFileName);
        imageBitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imageFilePath.getAbsolutePath()), 2000, 2000);

        imageView = (ImageView) findViewById(R.id.SingleView);

        imageView.setImageBitmap(imageBitmap);

        matrix = new Matrix();
        SGD = new ScaleGestureDetector(this, new ScaleListener());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageBitmap.recycle();
        imageBitmap = null;
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