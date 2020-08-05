package com.nzy.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author niezhiyang
 * since 2020/8/3
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private long tiem =0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long i = System.currentTimeMillis()-tiem;
        tiem = System.currentTimeMillis();
        Log.e("MyView",i+"");
        invalidate();
    }
}
