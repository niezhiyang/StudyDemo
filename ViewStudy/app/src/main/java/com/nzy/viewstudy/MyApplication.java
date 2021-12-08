package com.nzy.viewstudy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author niezhiyang
 * since 11/24/21
 */
public class MyApplication extends Application {
    public static MyApplication APPLICATION;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
//                LayoutInflater inflater = LayoutInflater.from(activity);
//                MyInflate inflate = new MyInflate(inflater, activity);
//                LayoutInflater.from(activity);
//                try {
//
//                    Field mInflater = ContextThemeWrapper.class.getDeclaredField("mInflater");
//                    mInflater.setAccessible(true);
//                    mInflater.set(activity, inflate);
//                } catch (Exception e) {
//                    Log.e("MyApplication", e.toString());
//                    e.printStackTrace();
//                }
//                LayoutInflater from = LayoutInflater.from(activity);

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    @Override
    public void onCreate() {
        APPLICATION = this;
        super.onCreate();
    }

    public static class MyInflate extends LayoutInflater {
        LayoutInflater mLayoutInflater;

        protected MyInflate(Context context) {
            super(context);
        }

        protected MyInflate(LayoutInflater original, Context newContext) {
            super(original, newContext);
        }


        @Override
        public LayoutInflater cloneInContext(Context newContext) {
            return null;
        }

        @Override
        public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
            Log.e("MyApplication", System.currentTimeMillis() + "");
            View view = super.inflate(parser, root, attachToRoot);
            Log.e("MyApplication", System.currentTimeMillis() + "");
            return view;
        }
    }
}
