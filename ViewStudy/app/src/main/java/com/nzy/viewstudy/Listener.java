package com.nzy.viewstudy;

/**
 * @author niezhiyang
 * since 11/19/21
 */
public interface Listener {
    default void setName() {
    }

    public static final String name = "dds";

    public static final int age = 10;
    public void getname();
}
