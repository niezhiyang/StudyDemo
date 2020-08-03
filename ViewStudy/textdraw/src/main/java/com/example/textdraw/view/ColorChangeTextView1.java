package com.example.textdraw.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.textdraw.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

public class ColorChangeTextView1 extends View {
    private static final String TAG = "sssss";

    private String mText = "大威天龙";//成员变量
    private int mTextSize = sp2px(30);
    private int mTextColor = Color.BLACK;
    private int mTextColorChange = Color.RED;
    private float mProgress;

    @Directions
    private int mDirection = DIRECTION_LEFT;
    private Paint mLinePaint;

    ///////////////////////////////////////////////////////////////////////////
    // Direction 代替枚举
    ///////////////////////////////////////////////////////////////////////////
    @IntDef(flag = true, value = {DIRECTION_LEFT, DIRECTION_RIGHT, DIRECTION_TOP, DIRECTION_BOTTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Directions {
    }

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_TOP = 2;
    public static final int DIRECTION_BOTTOM = 3;

    ///////////////////////////////////////////////////////////////////////////
    // 绘制
    ///////////////////////////////////////////////////////////////////////////
    private Rect mTextBound = new Rect();
    private Paint mPaint;
    private int mTextWidth;
    private int mTextHeight;

    private int mTextStartX;
    private int mTextStartY;

    public void setDirection(@Directions int direction) {
        mDirection = direction;
    }

    public ColorChangeTextView1(Context context) {
        this(context, null);
    }

    public ColorChangeTextView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorChangeTextView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        initAttr(context, attrs);
    }

    private void init() {
        mPaint = new Paint();
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(dp2px(3));
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.GREEN);
    }

    private void initAttr(final Context context, @Nullable final AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.ColorChangeTextView1);
        mText = ta.getString(R.styleable.ColorChangeTextView1_text);
        mTextSize = ta.getDimensionPixelSize(
                R.styleable.ColorChangeTextView1_text_size, mTextSize);
        mTextColor = ta.getColor(
                R.styleable.ColorChangeTextView1_text_color, mTextColor);
        mTextColorChange = ta.getColor(
                R.styleable.ColorChangeTextView1_text_color_change, mTextColorChange);
        mProgress = ta.getFloat(R.styleable.ColorChangeTextView1_progress, 0);

        mDirection = ta
                .getInt(R.styleable.ColorChangeTextView1_direction, mDirection);

        ta.recycle();

        mPaint.setTextSize(mTextSize);
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

        mTextStartX = getMeasuredWidth() / 2 - mTextWidth / 2;
        mTextStartY = getMeasuredHeight() / 2 - mTextHeight / 2;

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
                result = (int) (mTextWidth + .5f) + getPaddingLeft() + getPaddingRight();
                break;
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        result = (mode == MeasureSpec.AT_MOST) ? Math.min(result, size) : result;
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
                result = (int) (mTextBound.height() + .5f) + getPaddingTop() + getPaddingBottom();
                break;
        }
        //如果是AT_MOST,不能超过父布局的尺寸
        result = (mode == MeasureSpec.AT_MOST) ? Math.min(result, size) : result;
        return result;
    }

    private void measureText() {
        mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
        Log.i(TAG, "mTextBound = " + mTextBound);
        mTextWidth = (int) (mPaint.measureText(mText) + .5f);
        Log.i(TAG, "mTextWidth = " + mTextWidth);

//        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
//
//        mPaint.getFontMetrics(fontMetrics);
//
//        mTextHeight = (int) (fontMetrics.descent - fontMetrics.ascent + .5f);
//        Log.i(TAG, "mTextHeight = " + mTextHeight);

    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        switch (mDirection) {
            case DIRECTION_LEFT:
                Log.i(TAG, "DIRECTION_LEFT = " + DIRECTION_LEFT);
                //1 先绘制改变的颜色的文字  ？
                mPaint.setColor(mTextColorChange);
                //起始
                //绘制的终点
                canvas.save();
                canvas.clipRect(mTextStartX,0,(int)(mTextStartX + mProgress * mTextWidth),getMeasuredHeight());
                canvas.drawRect(canvas.getClipBounds(),mLinePaint);
                canvas.drawText(mText,mTextStartX
                        ,getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2),mPaint);
                canvas.restore();

                //2.绘制没有改变 底色
                mPaint.setColor(mTextColor);
                canvas.save();
                canvas.clipRect((int)(mTextStartX + mProgress * mTextWidth),0,mTextStartX + mTextWidth,getMeasuredHeight());
                canvas.drawText(mText,mTextStartX,getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2),mPaint);
                canvas.restore();
//                //先绘制改变的颜色
//                drawTextHorizontal(canvas, mTextColorChange, mTextStartX,
//                        (int) (mTextStartX + mProgress * mTextWidth));
//                //后绘制没改变的
//                drawTextHorizontal(canvas, mTextColor,
//                        (int) (mTextStartX + mProgress * mTextWidth), mTextStartX + mTextWidth);
                break;
            case DIRECTION_RIGHT:
                Log.i(TAG, "DIRECTION_RIGHT = " + DIRECTION_RIGHT);
                //先绘制改变的颜色

//                canvas.drawRect((int) (mTextStartX + (1 - mProgress) * mTextWidth), 0, mTextStartX + mTextWidth, getMeasuredHeight(), mLinePaint);
                drawTextHorizontal(canvas, mTextColorChange,
                        (int) (mTextStartX + (1 - mProgress) * mTextWidth), mTextStartX + mTextWidth);
                //后绘制没改变的
                drawTextHorizontal(canvas, mTextColor, mTextStartX,
                        (int) (mTextStartX + (1 - mProgress) * mTextWidth));
                break;
            case DIRECTION_TOP:
                Log.i(TAG, "DIRECTION_TOP = " + DIRECTION_TOP);
                //先绘制改变的颜色
                drawTextVertical(canvas, mTextColorChange, mTextStartY,
                        (int) (mTextStartY + mProgress * mTextHeight));
                //后绘制没改变的

                drawTextVertical(canvas, mTextColor,
                        (int) (mTextStartY + mProgress * mTextHeight), mTextStartY + mTextHeight);
                break;
            case DIRECTION_BOTTOM:
                Log.i(TAG, "DIRECTION_BOTTOM = " + DIRECTION_BOTTOM);
                //先绘制改变的颜色
                drawTextVertical(canvas, mTextColorChange,
                        (int) (mTextStartY + (1 - mProgress) * mTextHeight), mTextStartY + mTextHeight);
                //后绘制没改变的
                drawTextVertical(canvas, mTextColor, mTextStartY,
                        (int) (mTextStartY + (1 - mProgress) * mTextHeight));
                break;
            default:
                break;

        }

    }
	/*
                //1 先绘制改变的颜色的文字  ？
                mPaint.setColor(mTextColorChange);
                //起始
                //绘制的终点
                canvas.save();
                canvas.clipRect(mTextStartX,0,(int)(mTextStartX + mProgress * mTextWidth),getMeasuredHeight());
                canvas.drawRect(canvas.getClipBounds(),mLinePaint);
                canvas.drawText(mText,mTextStartX
                ,getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2),mPaint);
                canvas.restore();

                //2.绘制没有改变 底色
                mPaint.setColor(mTextColor);
                canvas.save();
                canvas.clipRect((int)(mTextStartX + mProgress * mTextWidth),0,mTextStartX + mTextWidth,getMeasuredHeight());
                canvas.drawText(mText,mTextStartX,getMeasuredHeight()/2 - (mPaint.descent()/2 + mPaint.ascent()/2),mPaint);
                canvas.restore();*/

    private void drawTextHorizontal(Canvas canvas, int color, int startX, int endX) {
        mPaint.setColor(color);

        canvas.save();
        canvas.clipRect(startX, 0, endX, getMeasuredHeight());
        canvas.drawText(mText, mTextStartX,
                getMeasuredHeight() / 2
                        - ((mPaint.descent() + mPaint.ascent()) / 2), mPaint);
        canvas.restore();
    }

    private void drawTextVertical(Canvas canvas, int color, int startY, int endY) {
        mPaint.setColor(color);

        canvas.save();
        canvas.clipRect(0, startY, getMeasuredWidth(), endY);
        canvas.drawText(mText, mTextStartX,
                getMeasuredHeight() / 2
                        - ((mPaint.descent() + mPaint.ascent()) / 2), mPaint);
        canvas.restore();
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        mPaint.setTextSize(mTextSize);
        requestLayout();
        invalidate();
    }

    public void setText(String text) {
        this.mText = text;
        requestLayout();
        invalidate();
    }

    public int getTextColorChange() {
        return mTextColorChange;
    }

    public void setTextColorChange(int textColorChange) {
        this.mTextColorChange = textColorChange;
        invalidate();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
        invalidate();
    }

    static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
