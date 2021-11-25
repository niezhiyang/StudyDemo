package com.nzy.viewstudy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SinglTopActivity extends AppCompatActivity implements Runnable {
    public static final String TAG = "SinglTopActivity";


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mHandler.postDelayed(SinglTopActivity.this,1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singl_top);
        Log.e(TAG,"onCreate");


        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(1);
        alphaAnimation.setRepeatMode(Animation.INFINITE);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
               Log.e(TAG,"onAnimationRepeat");
            }
        });
        alphaAnimation.setRepeatCount(10000);
//        ObjectAnimator alphaanimator = ObjectAnimator.ofFloat(viewById,
//                "alpha", 0, 1.0f);
//        alphaanimator.setDuration(10000);
//        alphaanimator.setRepeatCount(2);
//        alphaanimator.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e(TAG,"onNewIntent"+intent.getData());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
        Log.e(TAG,(System.currentTimeMillis()-time)+"onDestroy----");
    }

    private long time ;
    @Override
    public void onBackPressed() {
        time = System.currentTimeMillis();
        Log.e(TAG,time+"----");
        super.onBackPressed();

    }

    public void goNext(View view) {
        Intent intent = new Intent(this,Maintactivity4.class);
        startActivity(intent);
    }

    @Override
    public void run() {
        Log.e(TAG,"run");
        mHandler.post(this);
    }
}