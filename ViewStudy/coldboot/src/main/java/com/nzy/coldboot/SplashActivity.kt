package com.nzy.coldboot

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.File
import kotlin.concurrent.thread


class SplashActivity : AppCompatActivity() {

    private val TAG = "ActivityTaskManager"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPermissson()

        var file = Environment.getExternalStorageDirectory();
        var filePut = File(file, "尚气/尚气.mkv")
        Log.e("ddddd", "${filePut.absolutePath}---${filePut.exists()}");

        var fileOut = File(filesDir.absolutePath,"shangqi111.mkv")
        var thread = object : Thread() {
            override fun run() {
                super.run()
                Util.copyFile(filePut.absolutePath, fileOut.absolutePath)
            }
        }
        thread.start()
        var fileOut2 = File(filesDir.absolutePath,"shangqi222.mkv")
        var thread2 = object : Thread() {
            override fun run() {
                super.run()
                Util.copyFile(filePut.absolutePath, fileOut2.absolutePath)
            }
        }
        thread2.start()

        var fileOu3 = File(filesDir.absolutePath,"shangqi333.mkv")
        var thread3 = object : Thread() {
            override fun run() {
                super.run()
                Util.copyFile(filePut.absolutePath, fileOu3.absolutePath)
            }
        }
        thread3.start()
        var fileOu4 = File(filesDir.absolutePath,"shangqi444.mkv")
        var thread4 = object : Thread() {
            override fun run() {
                super.run()
                Util.copyFile(filePut.absolutePath, fileOu4.absolutePath)
            }
        }
        thread4.start()
    }

    private fun getPermissson() {
        val checkWriteStoragePermission: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        //如果没有被授予
        //如果没有被授予
        if (checkWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            //请求权限,此处可以同时申请多个权限
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            return
        } else {
            // do something....
        }
    }


}