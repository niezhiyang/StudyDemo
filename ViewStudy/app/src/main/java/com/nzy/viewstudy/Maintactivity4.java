package com.nzy.viewstudy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.nzy.viewstudy.fragment.MyFragment;
import com.nzy.viewstudy.fragment.MyFragment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {








    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       getClassLoader();
       findViewById(R.id.myview).post(new Runnable() {
           @Override
           public void run() {
               int width = findViewById(R.id.rllll).getWidth();
               int height = findViewById(R.id.rllll).getHeight();
               Log.e("qqqqaaaaaa",width+"--"+height);
           }
       });

        Intent intent = new Intent();
        intent.setAction("myaction");
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification n = new Notification();
        n.icon = R.drawable.ic_launcher_background;
        n.when = System.currentTimeMillis();
        nm.notify(0, n);
        MyViewModle myViewModle = new ViewModelProvider(this).get(MyViewModle.class);
        Log.e("ddddddddddd",myViewModle.toString());
        if (savedInstanceState != null) {
            Log.e("SSSSSSSSSSS", "onCreate" + savedInstanceState.getString("SSSSSSSSSSS"));
        }

        View viewById = findViewById(R.id.fl1);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl1, new MyFragment(), "fragment1").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl2, new MyFragment2(), "fragment2").commit();

//        MyRecyclerView viewById1 = (MyRecyclerView) findViewById(R.id.recyclerview);
//
//
//        ArrayList<String> mData = new ArrayList<String>();
//        for (int i = 0; i < 43; i++) {
//            mData.add("item" + i);
//        }
//        MyAdapter myAdapter = new MyAdapter(this, mData);
//        viewById1.setAdapter(myAdapter);//设置适配器
//        viewById1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        //解析图片时需要使用到的参数都封装在这个对象里了
        BitmapFactory.Options opt = new BitmapFactory.Options();
        //不为像素申请内存，只获取图片宽高
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile("sdcard/dog.jpg", opt);
        //拿到图片宽高
        int imageWidth = opt.outWidth;
        int imageHeight = opt.outHeight;

        Display dp = getWindowManager().getDefaultDisplay();
        //拿到屏幕宽高
        int screenWidth = dp.getWidth();
        int screenHeight = dp.getHeight();


        //计算缩放比例
        int scale = 1;
        int scaleWidth = imageWidth / screenWidth;
        int scaleHeight = imageHeight / screenHeight;
        //选择较大的那个比例
        if (scaleWidth >= scaleHeight && scaleWidth >= 1) {
            scale = scaleWidth;
        } else if (scaleWidth < scaleHeight && scaleHeight >= 1) {
            scale = scaleHeight;
        }


        //设置缩放比例
        opt.inSampleSize = scale;
        //不仅仅加载边界 使之为图片设置像素
        opt.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg", opt);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SSSSSSSSSSS", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SSSSSSSSSSS", "onStart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("SSSSSSSSSSS", "SSSSSSSSSSS1111");
        Log.e("SSSSSSSSSSS", "onSaveInstanceState");
    }

    public void clickc(View view) {
        Intent intent = new Intent(Maintactivity4.this, MainActivity3.class);
        startActivity(intent);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("SSSSSSSSSSS", "onRestoreInstanceState" + savedInstanceState.getString("SSSSSSSSSSS"));
    }


    void setSomthing() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("String");
                e.onError(new Throwable("dsss"));
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull String s) {

                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
