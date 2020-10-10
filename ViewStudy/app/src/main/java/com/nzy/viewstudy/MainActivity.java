package com.nzy.viewstudy;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nzy.viewstudy.view.FlowLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FlowLayout flowLayout;
    View myview;
    private int  Lenth = 100000;
    private TextView tv_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.iv_flowlayougt);
        myview = findViewById(R.id.myview);


        System.load("ssd");

        tv_name = findViewById(R.id.tv_name);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    tv_name.setText("你倒是更新呀");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        },1000);

        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e(TAG,"MainActivity - - onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"MainActivity - - onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"MainActivity - - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"MainActivity - - onStop");
    }
}