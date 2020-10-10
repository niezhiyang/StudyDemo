package com.nzy.arouter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nzy.arouter_annotations.Route;
import com.nzy.arouter_api.ARouter;

import androidx.appcompat.app.AppCompatActivity;

@Route("main/main")
public class MainActivity extends AppCompatActivity {
    TextView mTextView;
    public final boolean processNonSeparate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView =  findViewById(R.id.tv);
        mTextView.setText("lalala");
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent interweb= new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(interweb);
                System.load("lalala");
            }
        });

//
//        FrameLayout frameLayout =  findViewById(R.id.ll);
//        FragmentManager supportFragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//        transaction.add(new OneFragment(),"sssss").commit();
    }

    @Override
    protected void onStart() {
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        super.onStart();
        Log.e(OneFragment.TAG,"aaa----onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(OneFragment.TAG,"aaa----onResume");
    }


    public void click(View view) {
        ARouter.getInstance().gotoActivity("feed/center");
    }
}
