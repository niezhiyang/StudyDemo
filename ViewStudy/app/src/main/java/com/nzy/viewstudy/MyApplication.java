package com.nzy.viewstudy;

import android.app.Application;

import com.nzy.viewstudy.crash.CrashCaptureManager;

/**
 * @author niezhiyang
 * since 11/24/21
 */
public class MyApplication extends Application {
    public static MyApplication APPLICATION;



    @Override
    public void onCreate() {
        APPLICATION = this;
        super.onCreate();
        CrashCaptureManager.getInstance().init(this);
        CrashCaptureManager.getInstance().start();
    }

}
