package niezhiyang.cn.rxjava2_android_samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class UtilityActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.utility_doOnNext, R.id.utility_doAfterNext, R.id.utility_doOnComplete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.utility_doOnNext:
                Observable.just(1, 2, 3).doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "doOnNext接受之前:" + integer);
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "doOnNext接受到的数据:" + integer);
                    }
                });
                break;
            case R.id.utility_doAfterNext:
                Observable.just(1, 2, 3).doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "doAfterNext接受之后:" + integer);
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "doAfterNext接受到的数据:" + integer);
                    }
                });
                break;
            case R.id.utility_doOnComplete:
                Observable.just(1, 2, 3).doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "最终的操作");
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "doOnComplete接受到的数据:" + integer);
                    }
                });
                break;
                default:
        }
    }
}
