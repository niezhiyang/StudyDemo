package com.nzy.arouter;

import android.app.Application;

import com.nzy.arouter_api.ARouter;

/**
 * @author niezhiyang
 * since 2020/9/10
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().initRouter(this);
    }
}
