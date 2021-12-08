package com.nzy.viewstudy;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nzy.viewstudy.scrollview.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

/**
 * @author niezhiyang
 * since 12/7/21
 */
public class ScorllActivity extends AppCompatActivity {
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry",
            "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry",
            "Mango"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorll);

        IntentFilter intentFilter = new IntentFilter();
//        registerReceiver(MyBroadCast.class,intentFilter);
        ViewPager viewPager = findViewById(R.id.viewpager);
        List<View> list = new ArrayList<>();

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.iv1);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        list.add(imageView);

        ImageView imageView1 = new ImageView(this);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.mipmap.iv2);
        list.add(imageView1);

        ImageView imageView3 = new ImageView(this);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setImageResource(R.mipmap.iv3);
        list.add(imageView3);

        ViewAdapter adapter = new ViewAdapter(list);
        viewPager.setAdapter(adapter);

//        ListView listView = findViewById(R.id.listview);
//        listView.addHeaderView(inflate);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                ScorllActivity.this, android.R.layout.simple_list_item_1, data);
//        listView.setAdapter(arrayAdapter);

    }
}
