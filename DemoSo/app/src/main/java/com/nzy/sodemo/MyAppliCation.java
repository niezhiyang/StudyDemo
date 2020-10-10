package com.nzy.sodemo;

import android.app.Application;
import android.app.Service;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.nzy.sodemo.tinker.ShareReflectUtil;
import com.nzy.sodemo.tinker.TinkerLoadLibrary;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author niezhiyang
 * since 2020/10/9
 */
public class MyAppliCation extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        String assetsPath = "";
        if(Build.BOARD.contains("x86")){
            //x86
            assetsPath = "x86";
        }else {
            assetsPath = "armeabi";
        }
        File fileso = new File(getFilesDir().getAbsolutePath()+"/solib");
        if(!fileso.exists()){
            fileso.mkdirs();
        }
        if(fileso.list().length<=0){
            FileUtil.doCopy(this, assetsPath,fileso.getAbsolutePath());
        }
        try {
            TinkerLoadLibrary.installNativeLibraryPath(getClassLoader(),fileso);
        } catch (Throwable throwable) {
            Log.e(MainActivity.TAG,throwable.getMessage());
        }

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        SDKInitializer.setCoordType(CoordType.BD09LL);
        getPathSo();

    }

    private void getPathSo() {
        Field pathListField = null;
        try {
            pathListField = ShareReflectUtil.findField(getClassLoader(), "pathList");

            Object dexPathList = pathListField.get(getClassLoader());

            Field nativeLibraryDirectories = ShareReflectUtil.findField(dexPathList, "nativeLibraryDirectories");

            List<File> libDirs = (List<File>) nativeLibraryDirectories.get(dexPathList);
            for (File file : libDirs) {
                Log.e(MainActivity.TAG, file.getAbsolutePath());
            }

//        libDirs.add(0, folder);
            Field systemNativeLibraryDirectories =
                    ShareReflectUtil.findField(dexPathList, "systemNativeLibraryDirectories");
            List<File> systemLibDirs = (List<File>) systemNativeLibraryDirectories.get(dexPathList);
            for (File file : systemLibDirs) {
                Log.e(MainActivity.TAG, file.getAbsolutePath());
            }
            Method makePathElements =
                    ShareReflectUtil.findMethod(dexPathList, "makePathElements", List.class);
            Field nativeLibraryPathElements = ShareReflectUtil.findField(dexPathList, "nativeLibraryPathElements");
            Object o = nativeLibraryPathElements.get(dexPathList);

        libDirs.addAll(systemLibDirs);
        Object[] elements = (Object[]) makePathElements.
                invoke(dexPathList, libDirs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
