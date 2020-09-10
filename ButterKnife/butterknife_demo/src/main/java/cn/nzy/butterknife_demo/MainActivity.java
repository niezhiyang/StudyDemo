package cn.nzy.butterknife_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.nzy.butterknife_annotations.BindView;
import cn.nzy.butterknife_api.ARouter;
import cn.nzy.butterknife_api.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView  mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTextView.setText("bind成功了");
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().gotoActivity("main2");
            }
        });
    }
}
