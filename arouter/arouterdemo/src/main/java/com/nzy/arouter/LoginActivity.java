package com.nzy.arouter;

import android.os.Bundle;

import com.nzy.arouter_annotations.Route;

import androidx.appcompat.app.AppCompatActivity;

@Route("login/login1")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}