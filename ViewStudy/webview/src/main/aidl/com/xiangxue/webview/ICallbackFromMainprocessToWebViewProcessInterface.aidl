// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.xiangxue.webview;

interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackname, String response);
}
