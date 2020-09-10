package com.nzy.arouter_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * on 2018/5/2.
 * created by niezhiyang
 */

//编译型注解
@Retention(RetentionPolicy.CLASS)
//注解在成员变量上
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
