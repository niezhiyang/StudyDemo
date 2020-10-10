package com.nzy.hook;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * @author niezhiyang
 * since 2020/9/15
 */
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            TinkerLoadLibrary.installNativeLibraryPath(getClassLoader(), getCacheDir());
            File[] files = getCacheDir().listFiles();
            for(File file:files){
                Log.e("sssss",file.getAbsolutePath()+"----"+files.length);
//                if(file.getName().contains("libcurl")||file.getName().contains("agora")){
                    System.load(file.getAbsolutePath());
//                }


            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
