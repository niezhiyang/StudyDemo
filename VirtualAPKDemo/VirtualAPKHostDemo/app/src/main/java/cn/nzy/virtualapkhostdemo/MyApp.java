package cn.nzy.virtualapkhostdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.didi.virtualapk.PluginManager;

import java.io.File;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * on 2017/10/18.
 * 类的描述:
 */

public class MyApp extends Application {
   private String TAG = this.getClass().getSimpleName();
    @Override
    protected void attachBaseContext(Context context)  {
        super.attachBaseContext(context);
        PluginManager.getInstance(context).init();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PluginManager pluginManager = PluginManager.getInstance(this);
        //此处是当查看插件apk是否存在,如果存在就去加载(比如修改线上的bug,把插件apk下载到sdcard的根目录下取名为Demo.apk)
        File apk = new File(getExternalStorageDirectory(), "Demo.apk");
        if (apk.exists()) {
            try {
                Log.i(TAG,"准备加载...");
                pluginManager.loadPlugin(apk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
