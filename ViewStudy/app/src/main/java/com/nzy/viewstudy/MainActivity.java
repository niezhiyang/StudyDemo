package com.nzy.viewstudy;

import android.os.Bundle;
import android.view.View;

import com.nzy.viewstudy.view.FlowLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FlowLayout flowLayout;
    View myview;
    private int  Lenth = 100000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.iv_flowlayougt);
        myview = findViewById(R.id.myview);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },6);

    }
}