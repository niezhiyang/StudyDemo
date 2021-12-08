package com.nzy.viewstudy.xiaofeizhemoshi;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;

/**
 * @author niezhiyang
 * since 12/6/21
 */
public class SingleInstance {
    private static SingleInstance sInstance;

    private SingleInstance() {
    }

    public static SingleInstance getInstance() {
        if (sInstance == null) {
            synchronized (SingleInstance.class) {
                if (sInstance == null) {
                    sInstance = new SingleInstance();
                }
            }
        }

        return sInstance;
    }

    public void setData(@Nullable String tyoe){
        setName(tyoe);
    }


    public void setName(@NotNull String name){
        Log.e("sss",name);
    }
}


