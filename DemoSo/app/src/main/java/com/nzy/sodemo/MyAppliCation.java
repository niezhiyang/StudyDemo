package com.nzy.sodemo;

import android.app.Application;
import android.os.Build;
import android.util.Log;

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
    private final static String TAG = "MyAppliCation";

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
            TinkerLoadLibrary.installNativeLibraryPath(getClassLoader(), fileso);
        } catch (Throwable throwable) {
            Log.e(MainActivity.TAG, throwable.getMessage());
        }
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
