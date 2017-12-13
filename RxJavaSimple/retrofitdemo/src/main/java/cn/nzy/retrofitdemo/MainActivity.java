package cn.nzy.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.nzy.retrofitdemo.api.ApiService;
import cn.nzy.retrofitdemo.bean.MovicBean;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private String TAG = getClass().getSimpleName();
    private static final String BASE_URL = "http://m.maoyan.com/";
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // 设置超时时间
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                // 设置读写时间
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                // 添加生成bean的工厂
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    @OnClick({R.id.tv_get, R.id.tv_post, R.id.tv_put, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get:
                Call<ResponseBody> netData = mApiService.getNetData();
                netData.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                                Log.i(TAG,response.body().string()+"----"+response.code());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e(TAG,t.toString());
                    }
                });
                Call<MovicBean> beanData = mApiService.getBeanData();
                beanData.enqueue(new Callback<MovicBean>() {

                    @Override
                    public void onResponse(Call<MovicBean> call, Response<MovicBean> response) {
                        if (response.isSuccessful()) {
                            MovicBean movicBean = response.body();
                            Toast.makeText(MainActivity.this, movicBean.toString(), Toast.LENGTH_SHORT).show();
                            Log.i(TAG,movicBean.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovicBean> call, Throwable t) {

                    }
                });
                break;
            case R.id.tv_post:
                break;
            case R.id.tv_put:
                break;
            case R.id.tv_delete:
                break;
                default:
        }
    }
}
