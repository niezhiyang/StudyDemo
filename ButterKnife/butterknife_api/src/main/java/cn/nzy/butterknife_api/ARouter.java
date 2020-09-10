package cn.nzy.butterknife_api;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import dalvik.system.DexFile;

/**
 * @author niezhiyang
 * since 2020/9/10
 */
public class ARouter {
    private Context mContext;

    private ARouter() {
        routerMap = new HashMap<>();
    }

    private HashMap<String, Class> routerMap;
    private volatile static ARouter sInstance;

    public static ARouter getInstance() {
        if (sInstance == null) {
            synchronized (ARouter.class) {
                if (sInstance == null) {
                    sInstance = new ARouter();
                }
            }
        }
        return sInstance;
    }

    public void initRouter(Context context) {
        mContext = context;
        // 找到某个包名下的所有的文件
        List<String> clazzName = getClassName("com.nzy.arouter.util");
        for (String fileName : clazzName) {
            try {

                Class<?> aClass = Class.forName(fileName);
                // 反射 new出实例
                IRouter iRouter = (IRouter) aClass.newInstance();
                // 调用方法
                iRouter.putActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addActivity(String key, Class activityClass) {
        if (key != null && activityClass != null) {
            if (routerMap.containsKey(key)) {
                new RuntimeException("已经有这个key:" + key + "了");
            } else {
                routerMap.put(key, activityClass);
            }
        }
    }

    public void gotoActivity(String key) {
        if (routerMap.containsKey(key)) {
            Intent intent = new Intent(mContext, routerMap.get(key));
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "没有这个key", Toast.LENGTH_LONG).show();
        }
    }

    public List<String> getClassName(String packageName) {
        List<String> classNameList = new ArrayList<String>();
        try {

            DexFile df = new DexFile(mContext.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();

                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNameList;
    }

}
