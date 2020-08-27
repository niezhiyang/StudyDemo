package com.nzy.webviewnew.webview.command;


import com.nzy.webviewnew.webview.im.WebViewH5CallBack;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map parameters, WebViewH5CallBack callback);
}
