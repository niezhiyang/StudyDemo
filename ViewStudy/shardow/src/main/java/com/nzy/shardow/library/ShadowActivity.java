package com.nzy.shardow.library;

import android.content.Intent;
import android.os.Bundle;

import com.nzy.shardow.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author niezhiyang
 * since 11/30/21
 */
public class ShadowActivity  implements DLPlugin{
    private AppCompatActivity that;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        that.setContentView(R.layout.activity_main);
    }

    @Override
    public void setProxy(AppCompatActivity proxyActivity) {
        that = proxyActivity;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
