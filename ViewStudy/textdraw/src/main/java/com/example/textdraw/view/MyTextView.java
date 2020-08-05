package com.example.textdraw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class MyTextView extends View {
//    大威天龙，世尊地藏，般若诸佛，般若巴嘛空。”“哼！雕虫小技，竟敢班门弄斧，大威天龙
    private String mText = "世尊地藏";//成员变量

    private float mPercent = 0.0f;

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        invalidate();//重绘
    }

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制文字
        Paint paint = new Paint();
        paint.setTextSize(80);
//        float baseline = 100;
//        canvas.drawText(mText,0,baseline,paint);

        drawCenterLineX(canvas);
//        float x = getWidth()/2;
//
//        //默认LEFT
//        canvas.drawText(mText,x,baseline,paint);
//        //1. 设置文字对齐
//        paint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(mText,x,baseline+ paint.getFontSpacing(),paint);
//        //RIGHT
//        paint.setTextAlign(Paint.Align.RIGHT);
//        canvas.drawText(mText,x,baseline + paint.getFontSpacing()*2,paint);

        drawCenterLineY(canvas);
        //把文字绘制到view的中心
        //文字高度的计算

//        public float ascent;
//        public float bottom;
//        public float descent;
//        public float leading;
//        public float top; 从baseline到文字最顶端的尺寸


        //第二种方式 x种居中
        //底层 黑色
        drawCenterText(canvas);
        //上面一层 红色
        drawCenterText1(canvas);






    }

    private void drawCenterText(final Canvas canvas){
        //反面教程
        canvas.save();
        Paint  paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        float width = paint.measureText(mText);
        paint.setTextAlign(Paint.Align.LEFT);
        float left = getWidth()/2 -width/2;
        float left_x = left + width*mPercent;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseline = getHeight()/2 - (fontMetrics.descent + fontMetrics.ascent)/2;
        Rect rect = new Rect((int)left_x,0,getWidth(),getHeight());
        canvas.clipRect(rect);
        Paint  paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.parseColor("#66000000"));
        paint2.setAntiAlias(true);

//        canvas.drawRect(rect,paint2);
        canvas.drawText(mText,left,baseline ,paint);
        canvas.restore();
    }

    private void drawCenterText1(final Canvas canvas){
        canvas.save();
        Paint  paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(80);
        // 测量设置成 mText 的时候 的大小
        float width = paint.measureText(mText);
        // 设置居中方式，只支持水平，不支持垂直部分
        paint.setTextAlign(Paint.Align.LEFT);

        float left = getWidth()/2 -width/2;
        // 根据百分比设置右边界
        float right = left + width*mPercent;
        // 这个主要是为了算垂直的baseLine
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseline = getHeight()/2 - (fontMetrics.descent + fontMetrics.ascent)/2;

        // 裁剪了红色的rect
        Rect rect = new Rect((int)left,0,(int)right,getHeight());
        // 裁剪canvas 用于对画布进行矩形裁剪的方法
        canvas.clipRect(rect);

        Paint  paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.parseColor("#11000000"));
        paint2.setAntiAlias(true);

//        canvas.drawRect(rect,paint2);
        canvas.drawText(mText,left,baseline ,paint);
        canvas.restore();

    }

    private void drawCenterLineX(final Canvas canvas){
        Paint  paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight(),paint);
    }

    private void drawCenterLineY(final Canvas canvas){
        Paint  paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,paint);
    }




}
