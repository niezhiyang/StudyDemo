package com.nzy.sodemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.mmkv.MMKV;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        String rootDir = MMKV.initialize(this);
        Log.d(TAG,"mmkv root: " + rootDir);
        Log.d("zhiyang", getClassLoader().hashCode() + "--" + getClassLoader().getClass());
        Log.d("zhiyang",  SoLoader.class.getClassLoader().hashCode() + "--so--" + SoLoader.class.getClassLoader().getClass());
    }
    public void setMMKV(View view){


    }
    public void getMMKV(View view){

    }
}