package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class FilteringActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.filter_filter, R.id.filter_distince, R.id.filter_skip, R.id.filter_take, R.id.filter_last, R.id.filter_debounce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.filter_filter:
                Observable.just(1, 2, 3, 4, 5, 6)
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer > 2;
                            }
                        })
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "filter--大于2的数据有:" + integer);
                            }
                        });

                break;
            case R.id.filter_distince:
                Observable.just(1, 1, 3, 3, 5, 6)
                        .distinct()
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "distinct--去除重复的数据:" + integer);
                            }
                        });
                break;
            case R.id.filter_skip:
                Observable.just(1, 2, 3, 4, 5, 6)
                        .skip(2)
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "skip--跳过的数据:" + integer);
                            }
                        });
                break;
            case R.id.filter_take:
                Observable.just(1, 2, 3, 4, 5, 6)
                        .take(2)
//                        .take(1000, TimeUnit.MICROSECONDS)
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "take--发射的前两个数据:" + integer);
                            }
                        });
                break;
            case R.id.filter_last:
                Observable.just(1, 2, 3, 4, 5, 6)
                        .last(3)
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "last--最后的数据:" + integer);
                            }
                        });
                break;
            case R.id.filter_debounce:
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        emitter.onNext(4);
                        emitter.onNext(5);
                        Thread.sleep(2000);
                        emitter.onNext(6);
                        emitter.onNext(7);
                        emitter.onComplete();
                    }
                }).debounce(1000, TimeUnit.MICROSECONDS).subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "debounce--:" + integer);
                    }
                });
                break;
                default:
        }
    }
}
