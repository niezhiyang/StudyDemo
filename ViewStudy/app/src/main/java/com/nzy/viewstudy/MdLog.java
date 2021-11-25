package com.nzy.viewstudy;

import android.util.Log;

import androidx.annotation.Nullable;

public class MdLog {

    /**
     * @param object  传入Class类型 可以用getClass()得到
     * @param key  要输出数据的标识
     * @param out   动态参数，这里是要输出的数据
     */
    public static void log(Class<?> object, @Nullable String key, Object... out) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        boolean next = false;//判断是否含有 lambda 表达式
        String methodName;

        String className = object.getName();
        if (className == null || className.isEmpty()) {
            return;
        } else if (className.contains("$")) { //用于内部类的名字解析
            className = className.substring(className.lastIndexOf(".") + 1, className.indexOf("$"));
        } else {
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
        }

        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                next = true;
            }
        }
        if (!next) {
            methodName = s[3].getMethodName();
        } else {
            methodName = s[5].getMethodName();
        }
        int lines = s[3].getLineNumber();

        //生成指向java的字符串 加入到TAG标签里面
        String TAG = "类class" + "(" + className + ".java:" + lines + ")";

        //生成用户想要输出的数据
        StringBuilder temp = new StringBuilder();
        for (Object anOut : out) {
            temp.append(" ").append(anOut).append(",");
        }
        //删除输出数据 最后的 ,  号
        if (out.length != 0) {
            temp.deleteCharAt(temp.length() - 1);
        }

        if (key == null || key.trim().isEmpty()) {
//          System.out.println("类class ： " + simpleName + "   方法method ：" + methodName + "  line:" + lines + "  输出： " + temp);
            String parameter = "方法method ：" + methodName + "  输出： " + temp;
            Log.d(TAG, parameter);
        } else {
//          System.out.println("类class ： " + simpleName + "   方法method ：" + methodName + "  line:" + lines + "  输出： " + key + " :" + temp);
            String parameter;
            if (out.length == 0) {
                parameter = "方法method ：" + methodName + "  输出： " + "\"" + key + "\"";
            } else {
                parameter = "方法method ：" + methodName + "  输出： " + "\"" + key + "\"" + " ::" + temp;
            }
            Log.d(TAG, parameter);
        }
    }


    /**
     * 和上面方法功能相同，少了 key
     * @param object  传入Class类型 可以用getClass()得到
     * @param out    动态参数，这里是要输出的数据
     */
    public static void log(Class<?> object, Object... out) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        boolean next = false;//判断是否含有 lambda 表达式
        String methodName;

        String className = object.getName();
        if (className == null || className.isEmpty()) {
            return;
        } else if (className.contains("$")) { //用于内部类的名字解析
            className = className.substring(className.lastIndexOf(".") + 1, className.indexOf("$"));
        } else {
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
        }
        StackTraceElement[] s = Thread.currentThread().getStackTrace();
        for (StackTraceElement value : s) {
            if (value.getMethodName().startsWith("lambda")) {
                next = true;
            }
        }
        if (!next) {
            methodName = s[3].getMethodName();
        } else {
            methodName = s[5].getMethodName();
        }

        //得到代码所在的行数
        int lines = s[3].getLineNumber();

        //生成指向java的字符串 加入到TAG标签里面
        String TAG = "类class" + "(" + className + ".java:" + lines + ")";

        //生成用户想要输出的数据
        StringBuilder temp = new StringBuilder();
        for (Object anOut : out) {
            temp.append(" ").append(anOut).append(",");
        }
        //删除最后的 ,  号
        if (out.length != 0) {
            temp.deleteCharAt(temp.length() - 1);
        }
        String parameter = "方法method ：" + methodName + "  输出： " + temp;
        Log.d(TAG, parameter);
    }
}