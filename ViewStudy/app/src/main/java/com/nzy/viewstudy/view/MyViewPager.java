package com.nzy.viewstudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author niezhiyang
 * 为什么ViewPager的高度 wrap_content 无效，
 * 因为 ViewPgaer的onMeasure里面，上来就设置了 ViewPager的大小，根本没有测量子View的大小
 * setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
 * getDefaultSize(0, heightMeasureSpec));
 * since 2020/7/28
 */
public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
//并且使用的时候

//View.inflater(context,R.layout.item,true)
    //


    // 像下面这样写，会在 inflater 的时候，判断 是否有parent，false   会设置根据item的大小来设置 LayoutParmas,第三个false 代表，如果是false 就会把item的根布解析属性

    //  false 代表 会拿到item的根布局的属性。
//RecyclerView 使用这个 View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_item_person_info, parent, false)  // 一般咱们 最后面是false  ，系统的会true

    // View.inflater(context,R.layout.item,true) 相当于这个
    //item的大小才会有用


    //  网上说的这个都是错的
//  child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            // 测量每个儿子的
            child.measure(widthMeasureSpec, getChildMeasureSpec(heightMeasureSpec, 0, layoutParams.height));


            // 拿到儿子的高度
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        // 设置精准值
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}



