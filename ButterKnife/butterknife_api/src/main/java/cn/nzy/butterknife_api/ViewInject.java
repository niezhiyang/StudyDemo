package cn.nzy.butterknife_api;

/**
 * Created by zhy on 16/4/22.
 */
public interface ViewInject<T>
{
    void inject(T t, Object source);
}
