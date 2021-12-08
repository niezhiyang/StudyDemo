package com.nzy.shardow.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public interface DLPlugin {
    public void onActivityResult(int requestCode, int resultCode, Intent data);
    public void onResume();  
    public void onPause();  
    public void onStop();  
    public void onDestroy();  
    public void onCreate(Bundle savedInstanceState);
    public void setProxy(AppCompatActivity proxyActivity);
}  