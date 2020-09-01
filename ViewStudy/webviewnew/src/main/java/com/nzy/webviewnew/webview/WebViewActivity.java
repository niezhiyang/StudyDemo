package com.nzy.webviewnew.webview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.nzy.webviewnew.R;
import com.nzy.webviewnew.databinding.ActivityWebviewBinding;
import com.nzy.webviewnew.webview.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebviewBinding mBinding;

    public static WebViewActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Handler handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                    }
                };
            }
        }.start();
//        new Thread(new Runnable() {
//            public void run() {
//                Handler handler = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
//                    }
//                };
//                handler.sendEmptyMessage(1);
//
//            }
//
//            ;
//        }).start();
        activity = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, true) ? View.VISIBLE : View.GONE);
        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(Constants.ANDROID_ASSET_URI + "demo.html", true);
        transaction.replace(R.id.web_view_fragment, fragment).commit();

    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}
