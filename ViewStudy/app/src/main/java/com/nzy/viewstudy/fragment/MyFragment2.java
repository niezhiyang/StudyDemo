package com.nzy.viewstudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nzy.viewstudy.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author niezhiyang
 * since 12/8/21
 */
public class MyFragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment1, null);
        TextView viewById = (TextView) inflate.findViewById(R.id.tv_name);
        viewById.setText("2222222222");
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFragment fragment1 = (MyFragment) getChildFragmentManager().findFragmentByTag("fragment1");
                fragment1.show();

            }
        });
        return inflate;
    }
}
