package com.nzy.shardow;

import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * @author niezhiyang
 * since 11/30/21
 */
public class PluginMangerImpl {
    private static PluginMangerImpl instance = new PluginMangerImpl();
    public static PluginMangerImpl getInstance(){
        return instance;
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public Resources getResources() {
        return null;
    }

    public void loadPath(String absolutePath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
