package com.nzy.viewstudy.viewmode;

import android.os.Bundle;

import com.nzy.viewstudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author niezhiyang
 * since 11/25/21
 */
public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}
