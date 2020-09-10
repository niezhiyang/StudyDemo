package com.nzy.viewstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nzy.viewstudy.view.FlowLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FlowLayout flowLayout;
    View myview;
    private int  Lenth = 100000;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flowLayout = findViewById(R.id.iv_flowlayougt);
        myview = findViewById(R.id.myview);

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



        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    tv_name.setText("子线程更新了");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

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

    @Override
    protected void onStart() {
        super.onStart();


    }


    @Override
    protected void onResume() {
        super.onResume();

    }


}