package com.nzy.webviewnew.webview.mainprocess;

import android.util.Log;

import com.nzy.webviewnew.webview.command.Command;
import com.nzy.webviewnew.webview.im.WebViewH5CallBack;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager {
    private static final String TAG = "CommandsManager";
    private static MainProcessCommandsManager sInstance;
    private static HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                sInstance = new MainProcessCommandsManager();
            }
        }
        return sInstance;
    }

    public MainProcessCommandsManager addCommand(Command command){
        mCommands.put(command.name(),command);
        return this;
    }
    private MainProcessCommandsManager(){
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for(Command command : serviceLoader){
            if(!mCommands.containsKey(command.name())){
                mCommands.put(command.name(), command);
            }
        }
    }

    public void executeCommand(String commandName, Map params,  WebViewH5CallBack callback) {

        try {
            mCommands.get(commandName).execute(params, callback);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,e.toString());
        }
    }


}
