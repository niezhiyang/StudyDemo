package com.nzy.viewstudy

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File

class MainActivity2 : AppCompatActivity() {
    var view1 :View? = null
    val TAG =" MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "MainActivity2 - - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "MainActivity2 - - onResume")
    }

    fun clicc(view: View) {
//        view1!!.visibility = View.GONE

    }
    private fun getAvailableMemory(): ActivityManager.MemoryInfo {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().also { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
        }
    }

}