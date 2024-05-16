package com.nzy.nLog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nzy.nLog.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'nLog' library on application startup.
    static {
        System.loadLibrary("nLog");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
        File file = new File(getFilesDir(), "log.txt");
        if(file.exists()){
            file.delete();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        intMmap(file.getAbsolutePath());
//        write("大哥好");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        long start = System.currentTimeMillis();
                        for (int i = 1; i < 100_001; i++) {
                            write(i + "大家好\n");
                        }
                        Log.e("niezhiyang", "时间" + (System.currentTimeMillis() - start));
                    }
                }.start();

            }
        });
    }


    /**
     * A native method that is implemented by the 'nLog' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String intMmap(String dir);

    public native void write(String value);
}