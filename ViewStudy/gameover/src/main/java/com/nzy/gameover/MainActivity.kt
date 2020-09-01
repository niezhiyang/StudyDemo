package com.nzy.gameover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CopyZipFileToSD(this, "vedio_a.mp4", this.cacheDir.absolutePath).copy()
        CopyZipFileToSD(this, "vedio_b.mp4", this.cacheDir.absolutePath).copy()
        setContentView(R.layout.activity_main)

        bt.setOnClickListener {
//            viewa.parseVideoInfoAndPlay("vedio_a.mp4",cacheDir,5)
//            viewb.parseVideoInfoAndPlay("vedio_b.mp4",cacheDir,5)

            for (index in 1..9) {
                Log.e("ssssss", "$index");
            }
        }

        var thread = object : Thread() {
            override fun run() {
                super.run()
                var     handler = Handler();
            }
        }
        thread.start()




    }

    private var handler: Handler? = null

}
