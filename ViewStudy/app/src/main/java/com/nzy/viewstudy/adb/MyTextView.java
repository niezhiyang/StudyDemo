package com.nzy.viewstudy.adb;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.concurrent.ExecutorService;

/**
 * @author niezhiyang
 * since 2020/11/2
 */
public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private ExecutorService myThreadPool;

//在某方法中执行...

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("AppCompatTextView","MyTextView");
        myThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                //业务代码
            }
        });

    }
}
