package com.nzy.sodemo;

import android.util.Log;

/**
 * @author niezhiyang
 * since 2024/3/26
 */
public class SoLoader {
    private final static String TAG = "SoLoader";

    public static void loadLibrary(String libname) {
        Log.d(TAG, "拦截到了 SoLoader " + libname + "------");
        System.loadLibrary(libname);
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stackTraceElements.length; i++) {
            sb.append(stackTraceElements[i]);
            sb.append("\n");
        }
        Log.d(TAG, "拦截到了 堆栈信息 " + sb);
    }
}
