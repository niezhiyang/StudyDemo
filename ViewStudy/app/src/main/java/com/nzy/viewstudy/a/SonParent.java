package com.nzy.viewstudy.a;

import android.util.Log;

import com.nzy.viewstudy.Parent;

public class SonParent extends Parent {
    static {
        Log.e("ssss","Son 的 静态代码块");
    }
    public static int staticMember = staticMethod();



    {
        Log.e("ssss","Son 的 普通代码块");
    }

    public int memberVar = memberMethod();

    public SonParent() {
        Log.e("ssss","Son 的 构造方法");
    }

    private static int staticMethod() {
        Log.e("ssss","Son 的 静态变量");
        return 0;
    }

    private int memberMethod() {
        Log.e("ssss","Son 的 普通变量");
        return 0;
    }


}
