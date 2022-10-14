package com.nzy.viewstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



    }

    public void click(View view) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                // ip 就是 电脑的ip
                .url("http://172.16.5.234:4523/m1/1731342-0-default/pet/2")
                .get()
                .build();
        try {
           client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(@NonNull Call call, @NonNull IOException e) {
                   e.printStackTrace();
               }

               @Override
               public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                     Log.e("ssss",response.body().string());
               }
           });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
