package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ErrorHandlingActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handling);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.error_retry, R.id.error_retryWhen, R.id.error_onExceptionResumeNext, R.id.error_onErrorResumeNext, R.id.error_onErrorReturn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.error_retry:
                Observable.just(1, "2", 3)
                        .cast(Integer.class)
                        .retry(3)
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "retry重试数据"+integer);
                            }
                        });
                break;
            case R.id.error_retryWhen:
                Observable.just(1, "2", 3)
                        .cast(Integer.class)
                        .retryWhen(new Function<Observable<Throwable>, ObservableSource<Integer>>() {
                            @Override
                            public ObservableSource<Integer> apply(Observable<Throwable> throwableObservable) throws Exception {
                                return Observable.just(4,5);
                            }
                        })
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.i(TAG, "retryWhen重试数据"+integer);
                            }
                        });
                break;
            case R.id.error_onExceptionResumeNext:
                Observable.just(1,"2",3) .cast(Integer.class)
                        .onExceptionResumeNext(Observable.just(0) ).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "onExceptionResumeNext 出现错误之后的默认数据"+integer);
                    }
                });
                break;
            case R.id.error_onErrorResumeNext:
                Observable.just(1,"2",3) .cast(Integer.class)
                        .onErrorResumeNext(Observable.just(0) ).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "onErrorResumeNext 出现错误之后的默认数据"+integer);
                    }
                });
                break;
            case R.id.error_onErrorReturn:
                Observable.just(1,"2",3)
                        .cast(Integer.class)
                        .onErrorReturn(new Function<Throwable, Integer>() {
                            @Override
                            public Integer apply(Throwable throwable) throws Exception {
                                return 0;
                            }
                        }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "onErrorReturn 出现错误之后的默认数据"+integer);
                    }
                });

                break;
                default:
        }
    }
}
