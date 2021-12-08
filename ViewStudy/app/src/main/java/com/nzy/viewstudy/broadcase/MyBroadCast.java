package com.nzy.viewstudy.broadcase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niezhiyang
 * since 12/8/21
 */
public class MyBroadCast extends BroadcastReceiver {
    class Person {

    }
    class Teacher extends Person {

    }
    class Student extends Teacher {
        int num = 10;
        public void set(){
        }

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        List<? super Teacher> list = new ArrayList<>();
        //Teacher 及其子类可以看做成 Teacher，从而添加成功
//        list.add(new Person()); // compile error
//        list.add(new Teacher()); // compile success
        list.add(new Student()); // compile success
        Object object = list.get(0);// compile success
    }


}
