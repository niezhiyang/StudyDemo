package com.example.textdraw.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class TagFragment extends Fragment
{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        TextView tv = new TextView(getActivity());
        tv.setTextSize(60);
        Random r = new Random();
        tv.setBackgroundColor(Color.argb(r.nextInt(120), r.nextInt(255),
                r.nextInt(255), r.nextInt(255)));
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);
        return tv;

    }

    public static TagFragment newInstance(String title)
    {
        TagFragment tabFragment = new TagFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

}
