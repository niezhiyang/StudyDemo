package com.nzy.viewstudy;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.nzy.viewstudy.MyApplication.APPLICATION;

/**
 * @author niezhiyang
 * since 2020/9/17
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("sssss","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("sssss","onStartCommand");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(MyService.this,Maintactivity4.class);
                APPLICATION.startActivity(intent1);
            }
        },5000);
        return super.onStartCommand(intent, flags, startId);

    }
}
