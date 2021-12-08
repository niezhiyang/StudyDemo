package com.nzy.viewstudy.viewmode;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author niezhiyang
 * since 11/25/21
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
                Log.e("MeasureSpec", "MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.e("MeasureSpec", "MeasureSpec.EXACTLY");
                break;
            default:
                Log.e("MeasureSpec", "MeasureSpec.Defult");
                break;
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("MeasureSpec", "MeasureSpec.Defult111");
        canvas.drawColor(0xFF0000);
    }
}
