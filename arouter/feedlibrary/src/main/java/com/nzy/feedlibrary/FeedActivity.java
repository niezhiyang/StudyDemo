package com.nzy.feedlibrary;

import android.os.Bundle;

import com.nzy.arouter_annotations.Route;

import androidx.appcompat.app.AppCompatActivity;

@Route("feed/center")
public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }
}