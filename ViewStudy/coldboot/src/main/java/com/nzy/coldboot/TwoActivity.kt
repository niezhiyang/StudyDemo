package com.nzy.coldboot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
    }

    companion object{
        fun startMe(activity: AppCompatActivity){
            activity.startActivity(Intent(activity,TwoActivity::class.java))
        }
    }
}