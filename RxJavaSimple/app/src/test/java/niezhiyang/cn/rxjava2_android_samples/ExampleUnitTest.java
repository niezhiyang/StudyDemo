package niezhiyang.cn.rxjava2_android_samples;

import android.util.Log;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
         final String Tag = this.getClass().getSimpleName();
        Observable.just("1", "2", "3").observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Tag,d.toString());
            }

            @Override
            public void onNext(String value) {
                Log.i(Tag,value);
            }


            @Override
            public void onError(Throwable e) {
                Log.i(Tag,"onError");
            }

            @Override
            public void onComplete() {
                Log.i(Tag,"onComplete");
            }
        });
    }
}