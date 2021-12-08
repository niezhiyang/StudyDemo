package com.nzy.shardow;

import android.content.res.Resources;
import android.os.Bundle;

import com.nzy.shardow.library.DLPlugin;

import java.lang.reflect.Constructor;

import androidx.appcompat.app.AppCompatActivity;

public class PluginContinerActivity extends AppCompatActivity {

    private String className = "DemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使用插件中的view 改变了下面两个 ，就可以改变这里的setContentView
        // 因为寻找资源就是 从 getResources 中拿的
        // 拿的ClassLoader 是咱们 插件中的ClassLoader
        setContentView(R.layout.activity_plugin_continer);
        try {
            Class<?> clazz = getClassLoader().loadClass(className);
            Constructor<?> constructor = clazz.getConstructor(new Class[]{});
            Object object = constructor.newInstance(new Object[]{});
            DLPlugin life = (DLPlugin) object;
            life.setProxy(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 改变这个
    @Override
    public ClassLoader getClassLoader() {
        return PluginMangerImpl.getInstance().getClassLoader();
    }

    // 改变这个
    @Override
    public Resources getResources() {
        return PluginMangerImpl.getInstance().getResources();
    }
}