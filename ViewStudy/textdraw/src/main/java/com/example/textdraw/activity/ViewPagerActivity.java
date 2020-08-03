package com.example.textdraw.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.textdraw.R;
import com.example.textdraw.view.ColorChangeTextView1;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = "Zero";
    private String[] mTitles = new String[]{"关注", "热点", "推荐", "长沙"};
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TagFragment[] mFragments = new TagFragment[mTitles.length];
    private List<ColorChangeTextView1> mTabs = new ArrayList<ColorChangeTextView1>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (positionOffset > 0) {
                    ColorChangeTextView1 left = mTabs.get(position);
                    ColorChangeTextView1 right = mTabs.get(position + 1);

                    left.setDirection(ColorChangeTextView1.DIRECTION_RIGHT);
                    right.setDirection(ColorChangeTextView1.DIRECTION_LEFT);
                    Log.v(TAG, positionOffset + "");
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas() {

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = TagFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews() {
        mViewPager = findViewById(R.id.id_viewpager);

        mTabs.add((ColorChangeTextView1) findViewById(R.id.id_tab_01));
        mTabs.add((ColorChangeTextView1) findViewById(R.id.id_tab_02));
        mTabs.add((ColorChangeTextView1) findViewById(R.id.id_tab_03));
        mTabs.add((ColorChangeTextView1) findViewById(R.id.id_tab_04));
    }


}
