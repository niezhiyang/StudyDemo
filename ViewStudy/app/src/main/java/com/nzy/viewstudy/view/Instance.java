package com.nzy.viewstudy.view;

/**
 * @author niezhiyang
 * since 2020/8/10
 */
public class Instance {
    private Instance(){};
    private static volatile Instance sInstance;
    public Instance getInstance(){
        if(sInstance==null){
            synchronized (Instance.class){
                if(sInstance==null){
                    sInstance = new Instance();
                }
            }
        }
        return sInstance;
    }
}
