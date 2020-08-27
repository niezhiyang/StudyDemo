package com.nzy.viewstudy.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.nzy.viewstudy.R;

import androidx.appcompat.app.AppCompatActivity;

public class SingleActivity extends AppCompatActivity {
    TextView viewById;
    Handler mHandler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("sssss","sssss");
            viewById.setText("sssss");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single);
         viewById = findViewById(R.id.edit_query);
       new Thread(){
           @Override
           public void run() {
               super.run();
               try {
                   Thread.sleep(1000);
                   mHandler.sendEmptyMessage(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }.start();
        try {
            Thread.sleep(8000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}