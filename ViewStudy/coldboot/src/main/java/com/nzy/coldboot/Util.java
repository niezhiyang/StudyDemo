package com.nzy.coldboot;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author niezhiyang
 * since 11/17/21
 */
public class Util {
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            Log.e("ddddd", oldPath+"---"+oldfile.exists());
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                    Log.e("ddddd",bytesum/1024+"---"+bytesum/1024%1024);

                    if(bytesum/1024/1024%1024 ==0 ){
                        Log.e("ddddd",oldfile.getTotalSpace()+"--"+bytesum);

                    }
                }
                inStream.close();
            }
        } catch (Exception e) {
            Log.e("ddddd","复制单个文件操作出错"+e.toString());
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }
}