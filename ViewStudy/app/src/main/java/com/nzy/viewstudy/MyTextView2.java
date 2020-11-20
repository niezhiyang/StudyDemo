package com.nzy.viewstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author niezhiyang
 * since 2020/11/2
 */
public class MyTextView2 extends AppCompatTextView {
    public MyTextView2(Context context) {
        super(context);
    }

    public MyTextView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        if (mDroids.length > 0 && mDroidCards.size() == mDroids.length) {
//            // 过度重绘代码
//            int i;
//            for (i = 0; i < mDroidCards.size(); i++) {
//                // 每张卡片都放在前一张卡片的右侧
//                mCardLeft = i * mCardSpacing;
//                drawDroidCard(canvas, mDroidCards.get(i), mCardLeft, 0);
//            }
//        }

        invalidate();
    }
}
