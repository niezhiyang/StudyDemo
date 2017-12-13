package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CombiningActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combining);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.combin_zip, R.id.combin_concat, R.id.combin_merge, R.id.combin_combineLatest, R.id.combin_startWith})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.combin_zip:
                Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
                Observable<Integer> observable2 = Observable.just(1, 2, 3);
                Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer integer, Integer integer2) throws Exception {
                        return integer + integer2 + "";
                    }
                }).subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "zip合并后的数据是:" + s);
                    }
                });
                break;
            case R.id.combin_concat:
                Observable<Integer> observable3 = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        if (true) {
                            e.onNext(1);
                        } else {
                            e.onComplete();
                        }

                    }
                });
                Observable<Integer> observable4 = Observable.just(1, 2, 3);
                Observable.concat(observable3, observable4).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "concat的数据是:" + integer);
                    }
                });
                break;
            case R.id.combin_merge:
                Observable<Integer> observable5 = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                    }
                }).subscribeOn(Schedulers.io());
                Observable<Integer> observable6 = Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(4);
                        e.onNext(5);
                        e.onNext(6);
                    }
                });
                Observable.merge(observable5, observable6).observeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "merge合并后的数据是:" + integer);
                    }
                });
                break;
            case R.id.combin_combineLatest:
                Observable<Integer> observable7 = Observable.just(1, 2, 3);
                Observable<Integer> observable8 = Observable.just(4, 5, 6, 7);
                Observable.combineLatest(observable7, observable8, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        //integer 这个就是observable7中最后发的一个元素
                        return integer+integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "combineLatest的数据是:" + integer);
                    }
                });
                break;
            case R.id.combin_startWith:
                Observable.just(1, 2, 3).startWith(0).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "startWith添加后的数据是:" + integer);
                    }
                });
                break;
            default:
        }
    }
}
