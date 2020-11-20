package com.nzy.viewstudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"lalla",Toast.LENGTH_SHORT).show();
        Log.e("sssssss",intent.getAction()+"-----");
        try {
            Thread.sleep(30000);
            Log.e("sssssss",intent.getAction()+"---sleep完事--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
