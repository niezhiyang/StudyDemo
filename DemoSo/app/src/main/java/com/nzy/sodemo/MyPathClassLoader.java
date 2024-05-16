package com.nzy.sodemo;

import android.util.Log;

import dalvik.system.BaseDexClassLoader;

public class MyPathClassLoader extends BaseDexClassLoader {
    private static final String TAG = "MyPathClassLoader";
    private ClassLoader parent;
    public MyPathClassLoader(String dexPath, ClassLoader parent) {
        super(dexPath, null, null, parent);
        Log.d(TAG, "dexpath1: "+dexPath);
        this.parent = parent;
    }

    public MyPathClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) {
        super(dexPath, null, librarySearchPath, parent);
        Log.d(TAG, "dexpath2: "+dexPath + " librarySearchPath:"+ librarySearchPath);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Log.d(TAG, "loadClass: "+name);
        return super.loadClass(name);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Log.d(TAG, "loadClass : "+name +" resolve:"+resolve);
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Log.d(TAG, "findClass: "+name);
        return super.findClass(name);
    }

    @Override
    public String findLibrary(String name) {
        Log.e(TAG, "findLibrary: "+name);
        return super.findLibrary(name);
    }
}
