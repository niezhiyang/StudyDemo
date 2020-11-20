package com.nzy.viewstudy;

import android.Manifest;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nzy.viewstudy.adb.A;
import com.nzy.viewstudy.view.FlowLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ComponentCallbacks2 {
    private static final String TAG = "MainActivity";
    FlowLayout flowLayout;
    View myview;
    private int  Lenth = 100000;
    private TextView tv_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        float density  = dm.density;		// 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;		// 像素密度（每寸像素：120/160/240/320）
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.demo500);
        bitmap.getByteCount();//
        Log.e("aaaaa","getByteCount--"+bitmap.getByteCount()+"----DPI:"+densityDPI);

        Log.e("ssssss",density+"----"+densityDPI);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.iv_flowlayougt);
        Byte[] bytes = new Byte[10000];

//        for(int i =0 ;i<100000;i++){
//            bytes[i]= new Byte((byte) 121212);
//            new Thread(i+""){
//                @Override
//                public void run() {
//                    super.run();
//                    Log.e("sssss","-----"+getName());
//                    try {
//                        Thread.sleep(100000000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }
        myview = findViewById(R.id.myview);
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryClass();
        activityManager.getLargeMemoryClass();
       Log.e("sssssss","freeMemory:"+(Runtime.getRuntime().freeMemory()/1024/1024)+"------totalMemory:"+(Runtime.getRuntime().totalMemory()/1024/1024)+"-----maxMemory:"+ (Runtime.getRuntime().maxMemory()/1024/1024)) ;

       Log.e("sssssss","getMemoryClass:"+(activityManager.getMemoryClass())+"------getLargeMemoryClass:"+(activityManager.getLargeMemoryClass()));
        ;
        A a  = new  A();
        A a1  = new  A();

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
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
//        memoryInfo.availMem;
//        memoryInfo.threshold;
//        memoryInfo.totalMem;
    }
}