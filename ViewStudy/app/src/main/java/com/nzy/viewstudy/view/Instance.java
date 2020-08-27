package com.nzy.viewstudy.view;

import android.util.Log;

/**
 * @author niezhiyang
 * since 2020/8/10
 */
public class Instance {
    private static Instance instance;

    private Instance() {
    }

    public static Instance getInstance() {
        Log.e("sssss","333333");
        if (instance == null) {
            Log.e("sssss","1111111");
            synchronized (Instance.class) {
                if (instance == null) {
                    Log.e("sssss","2222222");
                    instance = new Instance();
                }
            }
        }

        return instance;
    }
}
