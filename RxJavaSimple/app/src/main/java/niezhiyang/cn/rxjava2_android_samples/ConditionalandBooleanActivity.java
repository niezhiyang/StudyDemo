package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class ConditionalandBooleanActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditionaland_boolean);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.condition_all, R.id.condition_contains, R.id.condition_takeUntil, R.id.condition_isEmptyUntil})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.condition_all:
                Observable.just(1,2,3)
                        .all(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer>2;
                            }
                        })
                        .subscribe(new BiConsumer<Boolean, Throwable>() {
                            @Override
                            public void accept(Boolean aBoolean, Throwable throwable) throws Exception {
                                Log.i(TAG, "all-->:" + aBoolean);
                            }
                        });

                break;
            case R.id.condition_contains:
                Observable.just(1,2,3)
                        .contains(3)
                        .subscribe(new BiConsumer<Boolean, Throwable>() {
                            @Override
                            public void accept(Boolean aBoolean, Throwable throwable) throws Exception {
                                Log.i(TAG, "contains-->:" + aBoolean);
                            }
                        });
                break;
            case R.id.condition_takeUntil:
                Observable.just(1,2,3,4,5)
                        .takeUntil(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer>2;
                            }
                        })
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "takeUntil -->:" + integer);
                            }
                        });
                break;
            case R.id.condition_isEmptyUntil:
                Observable.just(1,2,3)
                        .isEmpty()
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                Log.i(TAG, "isEmpty -->:" + aBoolean);
                            }
                        });
                break;
                default:
        }
    }
}
