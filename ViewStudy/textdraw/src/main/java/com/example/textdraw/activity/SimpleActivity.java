package com.example.textdraw.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.textdraw.R;
import com.example.textdraw.view.MyTextView;

import androidx.appcompat.app.AppCompatActivity;


public class SimpleActivity extends AppCompatActivity {

    MyTextView mView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mView1 = findViewById(R.id.color_change_textview);

        //属性动画
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onStartLeft(null);
            }
        },2000);
    }

    public void onStartLeft(View view){
        ObjectAnimator.ofFloat(mView1,"percent",0,1).setDuration(5000).start();
    }

    public void onStartRight(View view){
//        mView1.setDirection(ColorChangeTextView2.DIRECTION_RIGHT);
//        ObjectAnimator.ofFloat(mView1,"progress",0,1).setDuration(2500).start();
    }

    public void onStartTop(View view){
//        mView1.setDirection(ColorChangeTextView2.DIRECTION_TOP);
//        ObjectAnimator.ofFloat(mView1,"progress",0,1).setDuration(2500).start();
    }

    public void onStartBottom(View view){
//        mView1.setDirection(ColorChangeTextView2.DIRECTION_BOTTOM);
//        ObjectAnimator.ofFloat(mView1,"progress",0,1).setDuration(2500).start();
    }
}
