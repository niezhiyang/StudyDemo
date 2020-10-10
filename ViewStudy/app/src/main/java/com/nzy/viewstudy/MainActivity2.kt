package com.nzy.viewstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity2 : AppCompatActivity() {
    val TAG =" MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Log.e(TAG,"MainActivity2 - - onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"MainActivity2 - - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"MainActivity2 - - onResume")
    }

}