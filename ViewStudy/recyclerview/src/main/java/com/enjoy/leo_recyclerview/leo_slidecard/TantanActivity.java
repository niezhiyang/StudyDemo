package com.enjoy.leo_recyclerview.leo_slidecard;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.enjoy.leo_recyclerview.R;
import com.enjoy.leo_recyclerview.leo_slidecard.adapter.UniversalAdapter;
import com.enjoy.leo_recyclerview.leo_slidecard.adapter.ViewHolder;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 类似于探探左右滑动
 */
public class TantanActivity extends AppCompatActivity {

    private RecyclerView rv;
    private UniversalAdapter<SlideCardBean> adapter;
    private List<SlideCardBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tantan);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new SlideCardLayoutManager());

        mDatas = SlideCardBean.initDatas();
        adapter = new UniversalAdapter<SlideCardBean>(this, mDatas, R.layout.item_swipe_card) {

            @Override
            public void convert(ViewHolder viewHolder, SlideCardBean slideCardBean) {
                viewHolder.setText(R.id.tvName, slideCardBean.getName());
                viewHolder.setText(R.id.tvPrecent, slideCardBean.getPostition() + "/" + mDatas.size());
                Glide.with(TantanActivity.this)
                        .load(slideCardBean.getUrl())
                        .into((ImageView) viewHolder.getView(R.id.iv));
            }
        };
        rv.setAdapter(adapter);
        // 初始化数据
        CardConfig.initConfig(this);

        SlideCallback slideCallback = new SlideCallback(rv, adapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(slideCallback);
        itemTouchHelper.attachToRecyclerView(rv);

    }
}
