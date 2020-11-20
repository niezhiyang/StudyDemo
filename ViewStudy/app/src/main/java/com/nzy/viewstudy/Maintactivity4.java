package com.nzy.viewstudy;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {

    private IntentFilter intentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        registerReceiver(new MyBroadCast(), new IntentFilter("com.example.brocast.nimei"));
        //当网络发生变化的时候，系统广播会发出值为android.net.conn.CONNECTIVITY_CHANGE这样的一条广播
        findViewById(R.id.iv_ddd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra("info_key", "广播传递的参数");//发送带参数的广播；
                intent.setAction("com.example.brocast.nimei");
                intent.putExtra("info_key", "广播传递的参数");//发送带参数的广播；
                sendBroadcast(intent, "com.nzy.viewstudy.MyBroadCast");//需要带指定权限的接受者才能接收到广播
                Log.e("sssssss","-----"+intent.getAction()+"-----");
            }
        });

    }
}
