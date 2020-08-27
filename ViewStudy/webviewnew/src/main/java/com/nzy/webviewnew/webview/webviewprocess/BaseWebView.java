package com.nzy.webviewnew.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.nzy.webviewnew.webview.WebViewCallBack;
import com.nzy.webviewnew.webview.bean.JsParam;
import com.nzy.webviewnew.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.nzy.webviewnew.webview.webviewprocess.webchromeclient.XiangxueWebChromeClient;
import com.nzy.webviewnew.webview.webviewprocess.webviewclient.XiangxueWebViewClient;


public class BaseWebView extends WebView {
    public static final String TAG = "XiangxueWebView";

    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "xiangxuewebview");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new XiangxueWebViewClient(webViewCallBack));
        setWebChromeClient(new XiangxueWebChromeClient(webViewCallBack));

    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), this);
            }
        }
    }

    public void handleCallback(final String callbackname, final String response){
        if(!TextUtils.isEmpty(callbackname) && !TextUtils.isEmpty(response)){
            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:xiangxuejs.callback('" + callbackname + "'," + response + ")";
                    Log.e("xxxxxx", jscode);
                    evaluateJavascript(jscode, null);
                }
            });
        }
    }
}
