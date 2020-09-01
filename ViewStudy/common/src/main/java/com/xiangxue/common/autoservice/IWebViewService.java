package com.xiangxue.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);
    Fragment getWebViewFragment(String url, boolean canNativeRefresh);
    void startDemoHtml(Context context);
}
