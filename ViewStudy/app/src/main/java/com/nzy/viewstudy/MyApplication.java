package com.nzy.viewstudy;

import android.app.Application;

/**
 * @author niezhiyang
 * since 11/24/21
 */
public class MyApplication extends Application {
    public static  MyApplication APPLICATION;
    @Override
    public void onCreate() {
        APPLICATION = this;
        super.onCreate();
    }
}
