package cn.nzy.butterknife_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.nzy.butterknife_annotations.Route;

@Route("main3")
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}