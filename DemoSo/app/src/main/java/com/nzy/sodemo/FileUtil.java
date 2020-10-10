package com.nzy.sodemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author niezhiyang
 * since 2020/10/9
 */
public class FileUtil {
    public static void doCopy(Context context, String assetsPath, String desPath) {
        File file = new File(desPath);
        if(!file.exists()){
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] srcFiles = new String[0];//for directory
        try {
            srcFiles = context.getAssets().list(assetsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String srcFileName : srcFiles) {


            String outFileName = desPath + File.separator + srcFileName;
            String inFileName = assetsPath + File.separator + srcFileName;
            if (assetsPath.equals("")) {// for first time
                inFileName = srcFileName;
            }
//            Log.e("tag","========= assets: "+ assetsPath+"  filename: "+srcFileName +" infile: "+inFileName+" outFile: "+outFileName);
            try {
                InputStream inputStream = context.getAssets().open(inFileName);
                copyAndClose(inputStream, new FileOutputStream(outFileName));
            } catch (IOException e) {//if directory fails exception
                e.printStackTrace();
                new File(outFileName).mkdir();
                doCopy(context,inFileName, outFileName);
            }
        }
    }

    private static void closeQuietly(OutputStream out){
        try{
            if(out != null) out.close();;
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void closeQuietly(InputStream is){
        try{
            if(is != null){
                is.close();
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void copyAndClose(InputStream is, OutputStream out) throws IOException {
        copy(is,out);
        closeQuietly(is);
        closeQuietly(out);
    }

    private static void copy(InputStream is, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int n = 0;
        while(-1 != (n = is.read(buffer))){
            out.write(buffer,0,n);
        }
    }

    public static boolean copyAssets(Context context, String fileName, File destFile) {
        if (!TextUtils.isEmpty(fileName) && destFile != null) {
            boolean isSuccess = false;
            AssetManager assetManager = context.getAssets();
            InputStream in = null;
            FileOutputStream out = null;

            try {
                in = assetManager.open(fileName);
                out = new FileOutputStream(destFile);
                copyFile((InputStream)in, (OutputStream)out);
                isSuccess = true;
            } catch (IOException var16) {
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }

                    if (out != null) {
                        out.close();
                    }
                } catch (IOException var15) {
                }

            }

            return isSuccess;
        } else {
            return false;
        }
    }
    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];

        int read;
        while((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

    }
}