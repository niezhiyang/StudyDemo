package com.nzy.hook;

import android.os.Bundle;

import com.immomo.cvcenter.CVCenter;
import com.immomo.resdownloader.utils.SDKConfig;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author niezhiyang
 * since 2020/9/15
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//

        CVCenter.getInstance().init(this, new SDKConfig() {
            @Override
            public String getAppId() {
                return "";
            }
        });
    }

}
