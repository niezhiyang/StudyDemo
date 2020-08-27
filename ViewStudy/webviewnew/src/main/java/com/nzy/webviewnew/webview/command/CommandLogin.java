package com.nzy.webviewnew.webview.command;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.nzy.webviewnew.webview.WebViewActivity;
import com.nzy.webviewnew.webview.im.WebViewH5CallBack;
import com.xiangxue.base.autoservice.XiangxueServiceLoader;
import com.xiangxue.common.autoservice.IUserCenterService;
import com.xiangxue.common.eventbus.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {
    IUserCenterService iUserCenterService = XiangxueServiceLoader.load(IUserCenterService.class);
    WebViewH5CallBack callback;
    String callbacknameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }
    private ProgressDialog dialog;
    @Override
    public void execute(final Map parameters, WebViewH5CallBack callback) {
        Log.d("CommandLogin", new Gson().toJson(parameters));
            this.callback = callback;
            this.callbacknameFromNativeJs = parameters.get("callbackname").toString();

        try {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                      dialog  = ProgressDialog.show(WebViewActivity.activity, "提示", "正在登陆中…", true, false, null);
                }
            });
            Thread.sleep(1000);
            HashMap map = new HashMap();
            map.put("accountName", "niezhiyang");
            callback.onResult(callbacknameFromNativeJs,new Gson().toJson(map));
            dialog.dismiss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.d("CommandLogin", event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        this.callback.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
    }
}
