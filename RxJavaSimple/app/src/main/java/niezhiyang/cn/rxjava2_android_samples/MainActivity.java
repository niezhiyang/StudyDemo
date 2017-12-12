package niezhiyang.cn.rxjava2_android_samples;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button viewById = (Button)findViewById(R.id.Creat);
        OkHttpUtils//
                .get()//
                .url("http://file.dafy.com.cn/file/20171211/059c55aec9d84ef98376232654328fa4.apk")//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "me.apk") {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        viewById.setText(progress%total*100+"");
                    }
                });
    }

    public void test(View view) {

        Intent intent = null;
        switch (view.getId()) {
            case R.id.Creat:
                intent = new Intent(MainActivity.this, CreatActivity.class);
                startActivity(intent);
                break;
            case R.id.Schedulers:
                intent = new Intent(MainActivity.this, SchedulersActivity.class);
                startActivity(intent);
                break;
            case R.id.Transforming:
                intent = new Intent(MainActivity.this, TransformingActivity.class);
                startActivity(intent);
                break;
            case R.id.Filtering:
                intent = new Intent(MainActivity.this, FilteringActivity.class);
                startActivity(intent);
                break;
            case R.id.Combining:
                intent = new Intent(MainActivity.this, CombiningActivity.class);
                startActivity(intent);
                break;
            case R.id.ErrorHandling:
                intent = new Intent(MainActivity.this, ErrorHandlingActivity.class);
                startActivity(intent);
                break;
            case R.id.Utility:
                intent = new Intent(MainActivity.this, UtilityActivity.class);
                startActivity(intent);
                break;
            case R.id.ConditionalandBoolean:
                intent = new Intent(MainActivity.this, ConditionalandBooleanActivity.class);
                startActivity(intent);
                break;
            case R.id.Calculation:
                intent = new Intent(MainActivity.this, CalculationActivity.class);
                startActivity(intent);
                break;
            case R.id.Connect:
                intent = new Intent(MainActivity.this, ConnectActivity.class);
                startActivity(intent);
                break;
            case R.id.Async:
                intent = new Intent(MainActivity.this, AsyncActivity.class);
                startActivity(intent);
                break;
            case R.id.Block:
                intent = new Intent(MainActivity.this, BlockActivity.class);
                startActivity(intent);
                break;
            case R.id.Strings:
                intent = new Intent(MainActivity.this, StringsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
