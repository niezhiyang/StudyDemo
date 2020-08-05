package com.enjoy.leo_recyclerview.leo_slidecard.adapter;

import android.view.View;
import android.view.ViewGroup;

public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup var1, View var2, T var3, int var4);

    boolean onItemLongClick(ViewGroup var1, View var2, T var3, int var4);
}
