package com.nzy.sodemo;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niezhiyang
 * since 2024/4/15
 */
public class SoHook {
    public static void addPath(ClassLoader classLoader, File path) throws Exception {
        // classLoader PathClassLoader，我们需要拿到他的父类
        Class clazzBaseDexClassLoader =  classLoader.getClass().getSuperclass();
        // 拿到 pathList属性
        Field fieldPathList = clazzBaseDexClassLoader.getDeclaredField("pathList");
        fieldPathList.setAccessible(true);
        // 这是 DexPathList
        Object pathList = fieldPathList.get(classLoader);


        // 拿到 nativeLibraryDirectories 类
        Field fieldNativeLibraryDirectories = pathList.getClass().getDeclaredField("nativeLibraryDirectories");
        fieldNativeLibraryDirectories.setAccessible(true);
        // 拿到 app 内的所有so的集合
        List<File> nativeList = (List<File>) fieldNativeLibraryDirectories.get(pathList);

        Field fieldSystemNativeLibraryDirectories = pathList.getClass().getDeclaredField("systemNativeLibraryDirectories");
        fieldSystemNativeLibraryDirectories.setAccessible(true);
        // 拿到 系统 的所有so的集合
        List<File> systemNativeList = (List<File>) fieldSystemNativeLibraryDirectories.get(pathList);


        // 把咱们的路径添加到第一个位置
        nativeList.add(0, path);

        // 合并到一个文件夹
        final List<File> newLibDirs = new ArrayList<>(systemNativeList.size() + nativeList.size());
        newLibDirs.addAll(systemNativeList);
        newLibDirs.addAll(nativeList);

        // 拿到 DexPathList 的 makePathElements 方法
        Method methodMakePathElements = pathList.getClass().getDeclaredMethod("makePathElements", List.class);
        methodMakePathElements.setAccessible(true);
        // 使用 makePathElements 得到 NativeLibraryElement[]
        Object elements = methodMakePathElements.invoke(null, newLibDirs);

        // 设置给 DexPathList 的 nativeLibraryPathElements 属性
        Field fieldElements = pathList.getClass().getDeclaredField("nativeLibraryPathElements");
        fieldElements.setAccessible(true);
        fieldElements.set(pathList, elements);


    }
}
