package com.nzy.viewstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author niezhiyang
 * since 11/19/21
 */
public class MyDialogFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_dialog, null);
        View inflate1 = inflater.inflate(R.layout.fragment_dialog, null);
        Log.e("MyDialogFragment","onCreateView "+inflate.getContext()+"---"+inflate1.getContext()+"----"+getContext());
        return inflate;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e("MyDialogFragment","onHiddenChanged----"+hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.e("MyDialogFragment","onSaveInstanceState----");
        super.onSaveInstanceState(outState);
    }
}
