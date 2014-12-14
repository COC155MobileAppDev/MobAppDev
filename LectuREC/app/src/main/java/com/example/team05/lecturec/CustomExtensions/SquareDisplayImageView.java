package com.example.team05.lecturec.CustomExtensions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Johnbastian on 13/12/2014.
 */
public class SquareDisplayImageView extends ImageView {

    public SquareDisplayImageView(Context context)
    {    super(context); }

    public SquareDisplayImageView(Context context, AttributeSet attrs)
    {    super(context, attrs);  }

    public SquareDisplayImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {    super(context, attrs, defStyleAttr);  }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());

    }

}
