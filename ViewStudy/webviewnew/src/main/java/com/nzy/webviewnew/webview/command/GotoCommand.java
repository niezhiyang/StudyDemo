package com.nzy.webviewnew.webview.command;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.nzy.webviewnew.webview.im.WebViewH5CallBack;
import com.xiangxue.base.BaseApplication;

import java.util.Map;

@AutoService({Command.class})
public class GotoCommand implements Command {

    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, WebViewH5CallBack callback) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
