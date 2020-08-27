package com.nzy.coldboot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TAG = "ActivityTaskManager"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_splash)
        try {
            Thread.sleep(1000);
        } catch (e: InterruptedException) {
            e.printStackTrace();
        }
        tv_go_two.setOnClickListener{
            TwoActivity.startMe(this)
        }

       var thread =object :Thread(){
           override fun run() {
               super.run()

               sleep(1000)
               reportFullyDrawn()
           }
       }
        thread.start()
    }

    override fun onResume() {
        super.onResume()
//        try {
//            Thread.sleep(1000);
//        } catch (e: InterruptedException) {
//            e.printStackTrace();
//        }
        Log.e(TAG,"onResume");
    }
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.e(TAG,"onAttachedToWindow");
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        super.onWindowFocusChanged(hasFocus)
        Log.e(TAG,"onWindowFocusChanged");
    }


}
