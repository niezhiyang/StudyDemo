package com.nzy.viewstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nzy.viewstudy.view.FlowLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    FlowLayout flowLayout;
    View myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         flowLayout =  findViewById(R.id.iv_flowlayougt);
        myview =  findViewById(R.id.myview);
        findViewById(R.id.tv_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.requestLayout();
                myview.invalidate();
            }
        });


        TextView textView = new TextView(this);
        textView.setText("");

    }
}