package com.nzy.webviewnew;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;
import com.nzy.webviewnew.webview.command.CommandLogin;
import com.nzy.webviewnew.webview.command.GotoCommand;
import com.nzy.webviewnew.webview.command.UICommand;
import com.nzy.webviewnew.webview.mainprocess.MainProcessCommandsManager;
import com.xiangxue.base.loadsir.CustomCallback;
import com.xiangxue.base.loadsir.EmptyCallback;
import com.xiangxue.base.loadsir.ErrorCallback;
import com.xiangxue.base.loadsir.LoadingCallback;
import com.xiangxue.base.loadsir.TimeoutCallback;

/**
 * @author niezhiyang
 * since 2020/8/26
 */
public class BaseApplication extends Application {
    public static BaseApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        com.xiangxue.base.BaseApplication.sApplication = sApplication;
        sApplication =this;
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();

    }
}
