package com.enjoy.leo_recyclerview.leo_slidecard;

import android.graphics.Canvas;
import android.view.View;

import com.enjoy.leo_recyclerview.leo_slidecard.adapter.UniversalAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SlideCallback extends ItemTouchHelper.SimpleCallback {

    private RecyclerView mRv;
    private UniversalAdapter<SlideCardBean> adapter;
    private List<SlideCardBean> mDatas;

    public SlideCallback(RecyclerView mRv,
                         UniversalAdapter<SlideCardBean> adapter, List<SlideCardBean> mDatas) {
        super(0, 15);
        this.mRv = mRv;
        this.adapter = adapter;
        this.mDatas = mDatas;
    }

    // drag  拖拽
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    // 滑动
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        SlideCardBean remove = mDatas.remove(viewHolder.getLayoutPosition());
        mDatas.add(0, remove);
        adapter.notifyDataSetChanged();// onMeasure, onlayout
    }

    // onDraw


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        double maxDistance = recyclerView.getWidth() * 0.5f;
        double distance = Math.sqrt(dX * dX + dY * dY);
        double fraction = distance / maxDistance;

        if (fraction > 1) {
            fraction = 1;
        }

        // 显示的个数  4个
        int itemCount = recyclerView.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getChildAt(i);

            int level = itemCount - i - 1;

            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    view.setTranslationY((float) (CardConfig.TRANS_Y_GAP * level - fraction * CardConfig.TRANS_Y_GAP));
                    view.setScaleX((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                    view.setScaleY((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                }
            }
        }
    }

    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 1500;
    }
}
