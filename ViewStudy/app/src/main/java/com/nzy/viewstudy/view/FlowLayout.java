package com.nzy.viewstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niezhiyang
 * since 2020/7/28
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowLayout";

    // 水平的空隙大小
    private int mHorizontalSpacing = 10;
    // 垂直的空隙大小
    private int mVerticalSpacing = 10;


    // 记录每一行的 View的 List
    private List<List<View>> allLines = new ArrayList<>();
    // 记录每一行的行高
    private List<Integer> lineHeights = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }


    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//   高两位 UNSPECIFIED, EXACTLY, AT_MOST
//
//    UNSPECIFIED 不确定View大小做限制，系统使用
//    EXACTLY ：具体的值
//    AT_MOST ： 最大的值，也就是他爹的大小

    /**
     * 一般都是先测量儿子 再测量自己
     * 父类 可能 调用此方法多次
     *
     * @param widthMeasureSpec  本view的父亲给的
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.e(TAG,"onMeasure");
        // 父类 可能 调用此方法多次 ,调用次数 由 父亲决定
        allLines.clear();
        lineHeights.clear();

        // 获取孩子的
        int childCount = getChildCount();

        // 获取此View的padding
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();


        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);  //ViewGroup解析的父亲给我的宽度
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec); // ViewGroup解析的父亲给我的高度

        List<View> lineViews = new ArrayList<>(); //保存一行中的所有的view

        int lineWidthUsed = 0; //记录这行已经使用了多宽的size
        int lineHeight = 0; // 一行的行高

        // 当父 View 测量 FlowLayout 不是 精确值的时候  会用到
        int parentNeededHeight = 0;
        int parentNeededWidth = 0;

        // 遍历孩子
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 获取childview的Layoutparams
            LayoutParams layoutParams = childView.getLayoutParams();
            if (childView.getVisibility() != View.GONE) {
                // 转成具体的大小                                 此View的大小    padding 也是此View。也就是父亲的，
                // 因为此View要装子View，所以这里是传入父亲的宽度 ，和 此View的左右 和 上下 padding，还有child的LayoutParams的width
                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight,
                        layoutParams.width);

                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom,
                        layoutParams.height);


                // 调用子View的 onMeasure() 方法 又递归给子类的 onMeasure()
                childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

                //获取子view的度量宽高
                int childMesauredWidth = childView.getMeasuredWidth();
                int childMeasuredHeight = childView.getMeasuredHeight();

                // 是否需要换行 :如果当前用的lineWidthUsed 加上 子View的宽度大于 自己。就换行
                if (childMesauredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {

                    // 保存 每行的View
                    allLines.add(lineViews);
                    // 保存行高
                    lineHeights.add(lineHeight);
                    //一单换行，可以判断当前行需要的宽度和高度

                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);

                    // 如果换行 清空记录的值
                    lineViews = new ArrayList<>();
                    lineWidthUsed = 0;
                    lineHeight = 0;
                }

                lineViews.add(childView);

                // 一行添加View,并且记录
                lineWidthUsed = lineWidthUsed + childMesauredWidth + mHorizontalSpacing;
                lineHeight = Math.max(lineHeight, childMeasuredHeight);

                //处理最后一行数据
                if (i == childCount - 1) {
                    allLines.add(lineViews);
                    lineHeights.add(lineHeight);
                    parentNeededHeight = parentNeededHeight + lineHeight + mVerticalSpacing;
                    parentNeededWidth = Math.max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing);
                }
            }

        }

        //然后FlowLayout 也是个 父亲的子View,所以确定自己的 测量mode。
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 如果是精确的mode 。那就是自己的大小。否则就是按照View相加得到的
        if(widthMode == MeasureSpec.EXACTLY){
            Log.e("wwwwwwwww","MeasureSpec.EXACTLY");
        }else if(widthMode == MeasureSpec.AT_MOST){
            Log.e("wwwwwwwww","MeasureSpec.AT_MOST");
        }else if(widthMode == MeasureSpec.UNSPECIFIED){
            Log.e("wwwwwwwww","MeasureSpec.UNSPECIFIED");
        }
        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth: parentNeededWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ?selfHeight: parentNeededHeight;
        // 最后测量自己，设置自己的宽高，并且保存,实际上宽度是确定的
        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean change, int leftt, int topp, int rightt, int bottomm) {

        Log.e(TAG,"onLayout");
        // getMeasureWidth() 只要走了 onMeasure() 函数  getMeasureWidth()就可以拿到值
        int lineCount = allLines.size();

        int curL = getPaddingLeft();
        int curT = getPaddingTop();

        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = allLines.get(i);

            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                int left = curL;
                int top = curT;

//                int right = left + view.getWidth();
//                int bottom = top + view.getHeight();

                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = right + mHorizontalSpacing;
            }
            curT = curT + lineHeight + mVerticalSpacing;
            curL = getPaddingLeft();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }
}
