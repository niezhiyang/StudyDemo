package com.nzy.viewstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nzy.viewstudy.view.FlowLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FlowLayout flowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         flowLayout =  findViewById(R.id.iv_flowlayougt);
        findViewById(R.id.tv_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.requestLayout();
            }
        });


        TextView textView = new TextView(this);
        textView.setText("");

    }
}