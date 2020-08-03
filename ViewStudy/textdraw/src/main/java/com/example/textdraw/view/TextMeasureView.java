package com.example.textdraw.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

public class TextMeasureView extends View {
    private static final String TAG = "Zero";
    private String mText;
    private Paint mPaint;
    private Rect mTextBounds;
    private Paint.FontMetrics mFontMetrics;
    private float mMeasureWidth;
    private Paint mLinePaint;

    public TextMeasureView(final Context context) {
        super(context);
        init();
    }

    public TextMeasureView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextMeasureView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TextMeasureView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //1. 先测量文字
        measureText();
        //2. 测量自身
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        //3. 保持测量尺寸
        setMeasuredDimension(width, height);

    }

    private int measureWidth(final int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                Log.i(TAG, "measureWidth: EXACTLY");
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = (int)(mMeasureWidth+ .5f) + getPaddingLeft() + getPaddingRight();
                break;
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        result = (mode == MeasureSpec.AT_MOST)? Math.min(result,size):result;
        return result;
    }

    private int measureHeight(final int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = 0;
        switch (mode) {
            case MeasureSpec.EXACTLY:
                Log.i(TAG, "measureWidth: EXACTLY");
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                result = (int)(mFontMetrics.descent - mFontMetrics.ascent +.5f) + getPaddingTop() + getPaddingBottom();
                break;
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        result = (mode == MeasureSpec.AT_MOST)? Math.min(result,size):result;
        return result;
    }

    private void measureText() {//Paint 画笔
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
        Log.i(TAG, "mTextBounds = " + mTextBounds);
        mMeasureWidth = mPaint.measureText(mText);
        Log.i(TAG, "mMeasureWidth = " + mMeasureWidth);

        int textWidth = mTextBounds.width();
        Log.i(TAG, "textWidth = " + textWidth);
        int textHeight = mTextBounds.height();
        Log.i(TAG, "textheight = " + textHeight);

        mPaint.getFontMetrics(mFontMetrics);
        float textFontHeight = mFontMetrics.bottom - mFontMetrics.top;
        Log.i(TAG, "textFontHeight = " + textFontHeight);
        float textFontHeight1 = mFontMetrics.descent - mFontMetrics.ascent;
        Log.i(TAG, "textFontHeight1 = " + textFontHeight1);
//        public float ascent;
//        public float bottom;
//        public float descent;
//        public float leading;
//        public float top;
        Log.i(TAG, "mFontMetrics = {ascent: " + mFontMetrics.ascent + " ,bottom: " + mFontMetrics.bottom + " ,descent: " + mFontMetrics.descent
                + " ,leading: " + mFontMetrics.leading + " ,top: " + mFontMetrics.top);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        //偏移量
        float offsetx = (mTextBounds.left + mTextBounds.right) / 2;
        float offsety = (mTextBounds.top + mTextBounds.bottom) / 2;

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float halfWidth = getMeasuredWidth() / 2;
        float halfHeight = getMeasuredHeight() / 2;

        float x = halfWidth - offsetx;
        float y = halfHeight - offsety;
        //文字绘制在中心点
        canvas.drawText(mText, 0, mText.length(), x, y, mPaint);


        mLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));
        canvas.drawLine(0, halfHeight, width, halfHeight, mLinePaint);
        canvas.drawLine(halfWidth, 0, halfWidth, height, mLinePaint);
        //绘制 x,y
        mLinePaint.setColor(Color.BLUE);
        canvas.drawLine(x, 0, x, height, mLinePaint);
        canvas.drawLine(0, y, width, y, mLinePaint);
        mLinePaint.setPathEffect(null);
        //绘制mMeasureWidth
        canvas.drawLine(x + mMeasureWidth + mTextBounds.left, 0, x + mMeasureWidth + mTextBounds.left, height, mLinePaint);

        //绘制mTextBounds
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.FILL);
        canvas.drawLine(x + mTextBounds.left, y + mTextBounds.top, x + mTextBounds.left, y + mTextBounds.bottom, mLinePaint);//left
        canvas.drawLine(x + mTextBounds.left, y + mTextBounds.bottom, x + mTextBounds.right, y + mTextBounds.bottom, mLinePaint);//bottom
        canvas.drawLine(x + mTextBounds.right, y + mTextBounds.top, x + mTextBounds.right, y + mTextBounds.bottom, mLinePaint);//right
        canvas.drawLine(x + mTextBounds.left, y + mTextBounds.top, x + mTextBounds.right, y + mTextBounds.top, mLinePaint);//top

        //绘制FontMetrics
//        public float ascent;
        mLinePaint.setColor(Color.parseColor("#56121c"));
        mLinePaint.setTextSize(sp2px(20));
        mLinePaint.setStrokeWidth(dp2px(0.5f));
        canvas.drawText("ascent", 0, y + mFontMetrics.ascent, mLinePaint);
        canvas.drawLine(0, y + mFontMetrics.ascent, width, y + mFontMetrics.ascent, mLinePaint);
//        public float bottom;
        mLinePaint.setColor(Color.parseColor("#125615"));
        canvas.drawText("bottom", 0, y + mFontMetrics.bottom, mLinePaint);
        canvas.drawLine(0, y + mFontMetrics.bottom, width, y + mFontMetrics.bottom, mLinePaint);
//        public float descent;
        mLinePaint.setColor(Color.parseColor("#e7e31d"));
        canvas.drawText("descent", width - 150, y + mFontMetrics.descent, mLinePaint);
        canvas.drawLine(0, y + mFontMetrics.descent, width, y + mFontMetrics.descent, mLinePaint);
//        public float leading;
        mLinePaint.setColor(Color.parseColor("#8d8df2"));
        canvas.drawText("leading", 0, y + mFontMetrics.leading, mLinePaint);
        canvas.drawLine(0, y + mFontMetrics.leading, width, y + mFontMetrics.leading, mLinePaint);
//        public float top;
        mLinePaint.setColor(Color.parseColor("#cc8df2"));
        canvas.drawText("top", width - 150, y + mFontMetrics.top, mLinePaint);
        canvas.drawLine(0, y + mFontMetrics.top, width, y + mFontMetrics.top, mLinePaint);


    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(sp2px(80));

        mLinePaint = new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(dp2px(1));


        mTextBounds = new Rect();
        mFontMetrics = new Paint.FontMetrics();

        mText = "ཁྱོད་བདེ་མོ།";//藏文
        mText = "السلام عليكم";
//        mText = "jEh";
    }

    static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
