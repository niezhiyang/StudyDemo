package com.nzy.arouter_api.ll;

/**
 * Created by zhy on 16/4/22.
 */
public interface ViewInject<T>
{
    void inject(T t, Object source);
}
