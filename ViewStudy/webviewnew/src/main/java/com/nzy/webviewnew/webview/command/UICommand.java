package com.nzy.webviewnew.webview.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.nzy.webviewnew.BaseApplication;
import com.nzy.webviewnew.webview.im.WebViewH5CallBack;

import java.util.Map;

@AutoService({Command.class})
public class UICommand implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(final Map parameters,  WebViewH5CallBack callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
