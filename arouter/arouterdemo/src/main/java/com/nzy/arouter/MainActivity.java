package com.nzy.arouter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nzy.arouter_annotations.BindView;
import com.nzy.arouter_annotations.Route;
import com.nzy.arouter_api.ARouter;

import androidx.appcompat.app.AppCompatActivity;
@Route("main/main")
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView.setText("bind成功了");
    }

    public void click(View view) {
        ARouter.getInstance().gotoActivity("feed/center");
    }
}
