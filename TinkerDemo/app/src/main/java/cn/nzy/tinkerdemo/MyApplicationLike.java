package cn.nzy.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * on 2017/10/26.
 * 类的描述:
 */
@DefaultLifeCycle(application = ".MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag =true)
public class MyApplicationLike extends ApplicationLike {
    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerInstaller.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
