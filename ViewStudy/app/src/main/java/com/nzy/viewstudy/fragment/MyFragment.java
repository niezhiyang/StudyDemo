package com.nzy.viewstudy.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nzy.viewstudy.MyViewModle;
import com.nzy.viewstudy.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author niezhiyang
 * since 12/8/21
 */
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment1, null);
        TextView viewById = (TextView) inflate.findViewById(R.id.tv_name);
        viewById.setText("11111111111");
        MyViewModle myViewModle = new ViewModelProvider(requireActivity()).get(MyViewModle.class);
        Log.e("ddddddddddd",myViewModle.toString());
        return inflate;
    }

    public void show() {
        Toast.makeText(getActivity(),"大哥",Toast.LENGTH_SHORT).show();
    }
}
