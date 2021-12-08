package com.nzy.viewstudy

import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
    var view1: View? = null
    val TAG = " MainActivity"
    val mToast:Toast by lazy {
        Toast.makeText(this, "", Toast.LENGTH_SHORT)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)



    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun show(view: View) {

//        mToast.setText("大哥哥")
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
//                 showText();
            var intent = Intent(this,MyService::class.java)
            startService(intent)

        },5000)

    }

    private fun showText() {
        val windowManager: WindowManager = getWindowManager() ?: return

        val params = WindowManager.LayoutParams()
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.format = PixelFormat.TRANSLUCENT
        params.windowAnimations = android.R.style.Animation_Toast
        params.flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        params.packageName = "packageName"
        params.gravity = mToast.getGravity()
        params.x = mToast.getXOffset()
        params.y = mToast.getYOffset()
        params.verticalMargin = mToast.getVerticalMargin()
        params.horizontalMargin = mToast.getHorizontalMargin()
        var textView = TextView(this);
        textView.setText("大家好")
        textView.setTextColor(0xFFFFFF)
        windowManager.addView(mToast.view, params)
    }

    fun remove(view: View) {
        val windowManager: WindowManager = getWindowManager()
                ?: return

        windowManager.removeViewImmediate(mToast.getView())
    }

    suspend fun getImage(){


    }



}