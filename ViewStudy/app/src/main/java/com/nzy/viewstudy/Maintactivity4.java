package com.nzy.viewstudy;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.nzy.viewstudy.fragment.MyAdapter;
import com.nzy.viewstudy.fragment.MyFragment;
import com.nzy.viewstudy.fragment.MyFragment2;
import com.nzy.viewstudy.fragment.MyRecyclerView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {


    private IntentFilter intentFilter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        View viewById = findViewById(R.id.fl1);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl1, new MyFragment(), "fragment1").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl2, new MyFragment2(), "fragment2").commit();

        MyRecyclerView viewById1 = (MyRecyclerView) findViewById(R.id.recyclerview);


        ArrayList<String> mData = new ArrayList<String>();
        for (int i = 0; i < 43; i++) {
            mData.add("item" + i);
        }
        MyAdapter myAdapter = new MyAdapter(this, mData);
        viewById1.setAdapter(myAdapter);//设置适配器

    }
}
