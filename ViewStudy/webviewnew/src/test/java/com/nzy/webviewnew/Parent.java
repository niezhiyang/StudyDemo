package com.nzy.webviewnew;

public class Parent {
    static {
        System.out.println("Parent的 静态代码块");
    }
    public static int staticMember1 = staticMethod1();



    {
        System.out.println("Parent的 普通代码块");
    }

    public int memberVar1 = memberMethod1();

    public Parent() {
        System.out.println("Parent的 构造方法");
    }

    private static int staticMethod1() {
        System.out.println("Parent的 静态变量");
        return 0;
    }

    private int memberMethod1() {
        System.out.println("Parent的 普通变量");
        return 0;
    }


}

