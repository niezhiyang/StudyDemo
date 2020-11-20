package com.nzy.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author niezhiyang
 * since 2020/11/2
 */
public class MyTextView3 extends AppCompatTextView {
    public MyTextView3(Context context) {
        super(context);
    }

    public MyTextView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("AppCompatTextView","MyTextView3");
    }
}
