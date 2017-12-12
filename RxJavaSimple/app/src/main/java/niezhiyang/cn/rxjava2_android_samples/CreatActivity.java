package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * 创建型
 */
public class CreatActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName().toString();
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);

    }

    public void create(View view) {
        switch (view.getId()) {
            case R.id.creat_creat:
                //creat 操作符
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,integer+"");
                    }
                });
                break;
            case R.id.creat_just:
                //just 操作符
                Observable.just(1,2,3).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG,integer+"");
                    }
                });
                break;
            case R.id.creat_form:
                ArrayList<String >  arrayList = new ArrayList<>();
                for(int i = 0;i<3;i++) {
                    arrayList.add(""+i+i);
                }
                Observable.fromIterable(arrayList).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG,s+"");
                    }
                });
                //form 操作符
                break;
            case R.id.creat_timer:
//                        DAYS,HOURS,MICROSECONDS,MILLISECONDS,MINUTES,NANOSECONDS,SECONDS;
//                        天   小时   微秒         毫秒         分钟    纳秒       秒
                final long start = System.currentTimeMillis();
                Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        long end = System.currentTimeMillis();
                        Log.i(TAG,"时间差:"+(end-start)+"ms");
                    }
                });
                break;
            case R.id.creat_interval:
                Observable.interval(1000, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG,"interval:"+aLong);
                    }
                });
                break;
            case R.id.creat_repeat:
                Observable.just(1).repeat(2).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) throws Exception {
                        mCount++;
                        Log.i(TAG,"第:"+mCount+"次"+"数据为:"+i);
                    }
                });
                break;
                default:
        }
    }
}
