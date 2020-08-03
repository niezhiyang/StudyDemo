package com.example.textdraw.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.textdraw.view.OverdrawView;


public class OverDrawActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new OverdrawView(this));


    }

}
