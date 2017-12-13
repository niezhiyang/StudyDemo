package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class TransformingActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transforming);
    }

    public void transform(View view) {
        switch (view.getId()) {
            case R.id.transform_map:
                Observable.just(1, 2, 3, 4).map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer * 2;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        Log.i(TAG, "map:" + s);
                    }
                });
                break;
            case R.id.transform_flatMap:
                Observable.just(1, 2, 3, 4).flatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) throws Exception {
                        ArrayList<Integer> integers = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            integers.add(i);
                        }
                        return Observable.fromIterable(integers);
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        Log.i(TAG, "flatMap:" + s);
                    }
                });
                break;
            case R.id.transform_concatMap:
                Observable.just(1, 2, 3, 4).concatMap(new Function<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> apply(Integer integer) throws Exception {
                        for (int i = 0; i < 3; i++) {
                        }
                        return Observable.just(integer * 2);
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer s) throws Exception {
                        Log.i(TAG, "concatMap:" + s);
                    }
                });
                break;
            case R.id.transform_buffer:
                Observable.just(1, 2, 3,4,5,6,7,8,9).buffer(4,2).subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        Log.i(TAG, "buffer:" + integers);
                    }
                });
                break;
            case R.id.transform_window:
                Observable.just(1, 2, 3,4,5,6,7,8,9).window(2, TimeUnit.SECONDS).subscribe(new Consumer<Observable<Integer>>() {
                    @Override
                    public void accept(Observable<Integer> integerObservable) throws Exception {
                        Log.i(TAG, "window:" + integerObservable);
                        integerObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "window:" + integer);

                            }
                        });
                    }
                });
                break;
            case R.id.transform_groupby:
                Observable.just(1, 2, 3,4,5).groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer%2==0?"偶数":"奇数";
                    }
                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void accept(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                        stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "groupby:" +stringIntegerGroupedObservable.getKey()+"----"+ integer);
                            }
                        });
                    }
                });
                break;
            default:
        }
    }
}
