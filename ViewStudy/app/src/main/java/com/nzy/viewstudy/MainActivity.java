package com.nzy.viewstudy;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.nzy.viewstudy.view.FlowLayout;

public class MainActivity extends AppCompatActivity implements ComponentCallbacks2 {
    private static final String TAG = "MainActivity";
    FlowLayout flowLayout;
    View myview;
    private int Lenth = 100000;
    private TextView tv_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_name = findViewById(R.id.tv_name);
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Lenth++;
                        sleep(1000);
                        tv_name.setText("你倒是更新呀" + Lenth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();

    }


    @Override
    protected void onStart() {
        super.onStart();


        Log.e(TAG, "MainActivity - - onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity - - onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity - - onPause");


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity - - onStop");
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
    }


}