package com.nzy.arouter;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author niezhiyang
 * since 2020/9/25
 */
public class OneFragment extends Fragment {
    public static String TAG = "OneFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv  = new TextView(getActivity());
        tv.setText("oneFragment");
        tv.setTextSize(100);
        tv.setTextColor(Color.parseColor("#FF0000"));
        return tv;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }
}
