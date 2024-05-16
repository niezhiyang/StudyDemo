package com.nzy.sodemo;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @author niezhiyang
 * since 2020/10/9
 */
public class MyAppliCation extends Application {
    private final static String TAG = "MyAppliCation";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        getSysytemClassLoader();
    }

    private void getSysytemClassLoader() {
        Log.d("zhiyang",  ClassLoader.getSystemClassLoader().hashCode() + "---第一个---" +  ClassLoader.getSystemClassLoader().getClass());

    }

    @Override
    public void onCreate() {
        super.onCreate();
        String assetsPath = "";
        String[] supportAbis = Build.SUPPORTED_ABIS;
        // 这是demo ，所以选择最合适的去复制，线上需要判断apk中的so文件夹，对应的去复制
        // 比如线上so的文件夹只有arm ，就只复制arm,从dexPathList中的nativeLibraryDirectories中查看
        // 比如线上只有arm64
        Log.d(TAG, "最适合的架构类型是：" + supportAbis[0]);
        assetsPath = supportAbis[0];
        File fileso = new File(getFilesDir().getAbsolutePath() + "/solib");
        if (!fileso.exists()) {
            fileso.mkdirs();
        }
        if (fileso.list().length <= 0) {
            FileUtil.doCopy(this, assetsPath, fileso.getAbsolutePath());
        }
        try {
            SoHook.addPath(getClassLoader(), fileso);
//            TinkerLoadLibrary.installNativeLibraryPath(getClassLoader(),fileso);
        } catch (Throwable throwable) {
            Log.e("MyAppliCation", throwable.getMessage());
        }
//
        Log.d("zhiyang", getClassLoader().hashCode() + "--applcation的--" + getClassLoader().getClass());
        ApplicationInfo appInfo = getApplicationInfo();
        String apkPath = appInfo.sourceDir;
        MyPathClassLoader classLoader =
                new MyPathClassLoader(apkPath, getClassLoader());
        hookPackageClassLoader(classLoader);
        Thread.currentThread().setContextClassLoader(classLoader);

        new SoNoStatic();
    }

    private void hookPackageClassLoader(ClassLoader appClassLoaderNew) {
        try {
            Field packageInfoField =
                    Class.forName("android.app.ContextImpl").getDeclaredField("mPackageInfo");
            packageInfoField.setAccessible(true);
            Object loadedApkObject = packageInfoField.get(getBaseContext());
            Class clazzApk = Class.forName("android.app.LoadedApk");
            Field appClassLoaderField = clazzApk.getDeclaredField("mClassLoader");
            appClassLoaderField.setAccessible(true);
            Log.d("zhiyang",  appClassLoaderField.get(loadedApkObject).hashCode() + "-反射之前-" +  appClassLoaderField.get(loadedApkObject).getClass());
            appClassLoaderField.set(loadedApkObject, appClassLoaderNew);
        } catch (Throwable e) {
            Log.e("zhiyang", "error:" + e.getMessage());
        }
    }

}
