package com.nzy.webviewnew;

import java.util.Collections;
import java.util.HashMap;

/**
 * @author niezhiyang
 * since 11/19/21
 */
public class Son extends Parent {
    static {
        System.out.println("Son 的 静态代码块");
    }
    public static int staticMember = staticMethod();



    {
        System.out.println("Son 的 普通代码块");
    }

    public int memberVar = memberMethod();

    public Son() {
        System.out.println("Son 的 构造方法");
    }

    private static int staticMethod() {
        System.out.println("Son 的 静态变量");
        Collections.synchronizedMap(new HashMap<>());
        return 0;
    }

    private int memberMethod() {
        System.out.println("Son 的 普通变量");
        return 0;
    }


}
