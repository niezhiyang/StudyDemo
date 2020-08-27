package com.nzy.webviewnew.webview.webviewprocess;

import com.google.gson.Gson;
import com.nzy.webviewnew.webview.im.WebViewH5CallBack;
import com.nzy.webviewnew.webview.mainprocess.MainProcessCommandsManager;

import java.util.HashMap;


public class WebViewProcessCommandDispatcher {
    private static WebViewProcessCommandDispatcher sInstance;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }


    public void executeCommand(String commandName, String params, final BaseWebView baseWebView) {

        MainProcessCommandsManager.getInstance().executeCommand(commandName, new Gson().fromJson(params, HashMap.class), new WebViewH5CallBack() {
            public void onResult(String callbackname, String response) {
                baseWebView.handleCallback(callbackname, response);
            }
        });
    }
}
