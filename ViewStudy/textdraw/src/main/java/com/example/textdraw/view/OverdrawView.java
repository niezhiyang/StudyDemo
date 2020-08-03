package com.example.textdraw.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.textdraw.R;

public class OverdrawView extends View {

    private Bitmap[] mPukes = new Bitmap[3];
    private int[] mPukeIds = new int[]{
            R.drawable.puke_red_a,
            R.drawable.puke_red_q,
            R.drawable.puke_red_k,
    };
    private Paint mPaint;

    private void initBitmap(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        Bitmap bitmap = null;
        for (int i = 0; i < mPukes.length; i++) {
            bitmap = BitmapFactory.decodeResource(getResources(),mPukeIds[i]);
            mPukes[i] = Bitmap.createScaledBitmap(bitmap,400,600,false);
            mPukes[i] = bitmap;
        }
        setBackgroundColor(0xffffffff);
    }

    public OverdrawView(final Context context) {
        super(context);
        initBitmap();
    }

    public OverdrawView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initBitmap();
    }

    public OverdrawView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmap();
    }

    public OverdrawView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initBitmap();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(120,0);
        for (Bitmap puke : mPukes) {
            canvas.translate(120,0);
            canvas.drawBitmap(puke,0,0,null);
        }
        canvas.restore();
        //第二种绘制
        canvas.save();
        canvas.translate(120,800);
        canvas.drawText("避免过度绘制",0,-20,mPaint);
        for (int i = 0; i < mPukes.length; i++) {
            canvas.translate(120,0);
            canvas.save();
            if(i < mPukes.length -1){
                canvas.clipRect(0,0,120,mPukes[i].getHeight());
            }
            canvas.drawBitmap(mPukes[i],0,0,null);
            canvas.restore();
        }
        canvas.restore();
    }
}
