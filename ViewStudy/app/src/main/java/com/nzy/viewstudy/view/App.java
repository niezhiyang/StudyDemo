package com.nzy.viewstudy.view;

import android.app.Application;
import android.util.Log;

/**
 * @author niezhiyang
 * since 2020/8/10
 */
public class App extends Application {
    public static String logo = "";

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ssssss",logo);
        logo = "改变了";
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
