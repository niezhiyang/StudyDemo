/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nzy.hook;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.immomo.resdownloader.tinker.ShareReflectUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshaowen on 17/1/5.
 * Thanks for Android Fragmentation
 */

public class TinkerLoadLibrary {
    private static final String TAG = "Tinker.LoadLibrary";

    public static synchronized void installNativeLibraryPath(ClassLoader classLoader, File folder)
        throws Throwable {
        if (folder == null || !folder.exists()) {
            Log.e(TAG, "installNativeLibraryPath, folder %s is illegal  "+folder.getAbsolutePath()+" " );
            return;
        }
        // android o sdk_int 26
        // for android o preview sdk_int 25
        if ((Build.VERSION.SDK_INT == 25 && getPreviousSdkInt() != 0)
            || Build.VERSION.SDK_INT > 25) {
            try {
                V25.install(classLoader, folder);
                return;
            } catch (Throwable throwable) {
                // install fail, try to treat it as v23
                // some preview N version may go here
//                Log.e(TAG, "installNativeLibraryPath, v25 fail, sdk: %d, error: %s, try to fallback to V23",
//                        Build.VERSION.SDK_INT, throwable.getMessage());
                Log.e(TAG,throwable.getMessage());
                V23.install(classLoader, folder);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {
            try {
                V23.install(classLoader, folder);
            } catch (Throwable throwable) {
                // install fail, try to treat it as v14
//                Log.e(TAG, "installNativeLibraryPath, v23 fail, sdk: %d, error: %s, try to fallback to V14",
//                    Build.VERSION.SDK_INT, throwable.getMessage());
                Log.e(TAG,throwable.getMessage());

                V14.install(classLoader, folder);
            }
        } else if (Build.VERSION.SDK_INT >= 14) {
            V14.install(classLoader, folder);
        }
    }

    /**
     * fuck部分机型删了该成员属性，兼容
     * @return 被厂家删了返回1，否则正常读取
     */
    @TargetApi(Build.VERSION_CODES.M)
    private static int getPreviousSdkInt() {
        try {
            return Build.VERSION.PREVIEW_SDK_INT;
        } catch (Throwable ignore) {
        }
        return 1;
    }

    private static final class V14 {
        private static void install(ClassLoader classLoader, File folder)  throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            ShareReflectUtil.expandFieldArray(dexPathList, "nativeLibraryDirectories", new File[]{folder});
        }
    }

    private static final class V23 {
        private static void install(ClassLoader classLoader, File folder)  throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            Field nativeLibraryDirectories = ShareReflectUtil.findField(dexPathList, "nativeLibraryDirectories");

            List<File> libDirs = (List<File>) nativeLibraryDirectories.get(dexPathList);
            libDirs.add(0, folder);
            Field systemNativeLibraryDirectories =
                ShareReflectUtil.findField(dexPathList, "systemNativeLibraryDirectories");
            List<File> systemLibDirs = (List<File>) systemNativeLibraryDirectories.get(dexPathList);
            Method makePathElements =
                ShareReflectUtil.findMethod(dexPathList, "makePathElements", List.class, File.class, List.class);
            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            libDirs.addAll(systemLibDirs);
            Object[] elements = (Object[]) makePathElements.
                invoke(dexPathList, libDirs, null, suppressedExceptions);
            Field nativeLibraryPathElements = ShareReflectUtil.findField(dexPathList, "nativeLibraryPathElements");
            nativeLibraryPathElements.setAccessible(true);
            nativeLibraryPathElements.set(dexPathList, elements);
        }
    }

    private static final class V25 {
        private static void install(ClassLoader classLoader, File folder)  throws Throwable {
            Field pathListField = ShareReflectUtil.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            Field nativeLibraryDirectories = ShareReflectUtil.findField(dexPathList, "nativeLibraryDirectories");

            List<File> libDirs = (List<File>) nativeLibraryDirectories.get(dexPathList);
            libDirs.add(0, folder);
            Field systemNativeLibraryDirectories =
                    ShareReflectUtil.findField(dexPathList, "systemNativeLibraryDirectories");
            List<File> systemLibDirs = (List<File>) systemNativeLibraryDirectories.get(dexPathList);
            Method makePathElements =
                    ShareReflectUtil.findMethod(dexPathList, "makePathElements", List.class);
            libDirs.addAll(systemLibDirs);
            Object[] elements = (Object[]) makePathElements.
                    invoke(dexPathList, libDirs);
            Field nativeLibraryPathElements = ShareReflectUtil.findField(dexPathList, "nativeLibraryPathElements");
            nativeLibraryPathElements.setAccessible(true);
            nativeLibraryPathElements.set(dexPathList, elements);
        }
    }
}
