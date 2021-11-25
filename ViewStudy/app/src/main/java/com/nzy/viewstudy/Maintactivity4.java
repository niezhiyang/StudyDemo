package com.nzy.viewstudy;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {

    private IntentFilter intentFilter;
    private String TAG = "zhiyang  Maintactivity4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("大哥哥");
        ArrayMap<String, String> map = new ArrayMap<>();
        SparseArray<String> sparse = new SparseArray<>();

        sparse.put(10, "你好呀");
//        ExecutorService cachedThreadPool = new ThreadPoolExecutor(1, 2,
//                60L, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(1), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
//
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.e("ddddddddd","11111111");
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.e("ddddddddd","22222222");
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.e("ddddddddd","3333333333");
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    Log.e("ddddddddd","444444444---"+ Thread.currentThread().getName());
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        setContentView(R.layout.activity_main3);
//        SurfaceView surfaceView = new SurfaceView(this);
//        SurfaceHolder surfaceViewHolder = surfaceView.getHolder();
//
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        Integer integer = arrayList.get(0);
//        surfaceViewHolder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                Surface surface = holder.getSurface();
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//
//            }
//        });

        MutableLiveData<String> data = new MutableLiveData<>();
        data.setValue("你好友");
//        礼物动效
        data.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("ddddd", "大家都好" + s);
            }
        });
//        findViewById(R.id.lv_line).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("zhiyang onclick ","lv_line onClick");
//            }
//        });
//        findViewById(R.id.lv_line).setOnTouchListener(new View.OnTouchListener() {
//            private String TAG = "---lv_line";
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        Log.e(TAG, "onTouchEvent ACTION_DOWN");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        Log.e(TAG, "onTouchEvent ACTION_MOVE");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.e(TAG, "onTouchEvent ACTION_UP");
//                        break;
//
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });

//        findViewById(R.id.iv_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Maintactivity4.this, SinglTopActivity.class);
//                startActivity(intent);
//                int i = 0;
//                if (i == 1) {
//                    Log.e("aaaaaaa", "=====1 了 ");
//                }
//                Toast.makeText(Maintactivity4.this,"点我",1).show();
//                Log.e("zhiyang onclick ","iv_button onClick");
//
//                Toast toast = Toast.makeText(Maintactivity4.this, "1111", Toast.LENGTH_SHORT);
//
//                toast.setText("大家好呀");
//
//                toast.show();
//                Intent intent = new Intent(Maintactivity4.this,NewVersionActivity.class);
//                Intent intent = new Intent(Maintactivity4.this,MainActivity2.class);
//                startActivity(intent);
//
//
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//                        try {
//                            sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                data.setValue("222222");
//                                data.setValue("11111`1");
//                            }
//                        });
////                        data.postValue("222222");
////                        data.postValue("11111`1");
//                    }
//                }.start();

//            }
//        });


    }

    MyDialogFragment dialogFragment = new MyDialogFragment();

    public void show(View view) {
//
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_contnier,dialogFragment).commit();
////        getSupportFragmentManager().beginTransaction().show(dialogFragment).commit();
//        View viewById = findViewById(R.id.fl_contnier);
//        Log.e("MyDialogFragment","viewById "+viewById);

//        Intent intent = new Intent(Maintactivity4.this, SinglTopActivity.class);
//        startActivity(intent);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:

                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:

                Log.e(TAG, "dispatchTouchEvent ACTION_CANCEL");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ddddd", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ddddd", "onResume");
    }

    private void requestPermission() {

    }

    public void hide(View view) {
        getSupportFragmentManager().beginTransaction().hide(dialogFragment).commit();
    }
}
