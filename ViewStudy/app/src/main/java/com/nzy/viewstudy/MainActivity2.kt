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
        var dm = DisplayMetrics()
        dm = resources.displayMetrics
        val density = dm.density // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）

        val densityDPI = dm.densityDpi // 像素密度（每寸像素：120/160/240/320）

        val bitmap:Bitmap? = BitmapFactory.decodeResource(resources, R.drawable.demo500)

        var outputStream = ByteArrayOutputStream();
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
        var options = 90
        while (outputStream.toByteArray().size / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            outputStream.reset() // 重置baos即清空baos
            bitmap?.compress(Bitmap.CompressFormat.JPEG, options, outputStream) // 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10 // 每次都减少10
        }
        val isBm = ByteArrayInputStream(outputStream.toByteArray())
        val bitmap3 = BitmapFactory.decodeStream(isBm, null, null)
        findViewById<ImageView>(R.id.iv_demo).setImageBitmap(bitmap)

        var file = File(cacheDir, "demo500.jpg")
       var bitmap2 =  BitmapFactory.decodeFile(file.absolutePath)
        Log.e("aaaaa", "getByteCount--" + bitmap?.byteCount + "----DPI:" + densityDPI + "----bitmap2：" + bitmap2.byteCount + "----bitmap3：" + bitmap3?.byteCount)
//
//        Log.e("ssssss", "$density----$densityDPI")
//        Log.e("sssss", TestUtil.test + "--ssss---")
//        TestUtil.test = "你好呀"
//        Log.e(TAG, "MainActivity2 - - onCreate")
//        view1 = findViewById<View>(R.id.iv_src_approve);
//        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
//        activityManager.memoryClass
//        activityManager.largeMemoryClass
//        Log.e("sssssss", "freeMemory:" + Runtime.getRuntime()
//                .freeMemory() / 1024 / 1024 + "------totalMemory:" + Runtime.getRuntime()
//                .totalMemory() / 1024 / 1024 + "-----maxMemory:" + Runtime.getRuntime()
//                .maxMemory() / 1024 / 1024)
//
//        Log.e("sssssss", "getMemoryClass:" + activityManager.memoryClass + "------getLargeMemoryClass:" + activityManager.largeMemoryClass)
//        Log.e("sssssss", "availMem:" + (getAvailableMemory().availMem / 1024 / 1024) + "------threshold:" + (getAvailableMemory().threshold / 1024 / 1024) + "------totalMem:" + (getAvailableMemory().totalMem / 1024 / 1024))
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