package com.nzy.gameover;

import android.opengl.GLES20;
import android.os.Build;
import android.util.Log;

public class GLFrameBuffer {
    public boolean isLocked;
    protected int[] frameBuffer;
    protected int[] texture_out;
    protected int[] depthRenderBuffer;
    private Object countLock = new Object();
    private int framebufferReferenceCount;
    private int mWidth;
    private int mHeight;
    private boolean isInited = false;
    static boolean isUsedFloat = false;

    private boolean isFloat = false;

    public GLFrameBuffer(int width, int height) {
        this.frameBuffer = new int[1];
        this.texture_out = new int[1];
        this.depthRenderBuffer = new int[1];
        framebufferReferenceCount = 0;
    }

    public boolean cloudRelease() {
        return framebufferReferenceCount <= 0;
    }

    public void lock() {
        synchronized (countLock) {
            isLocked = true;
            framebufferReferenceCount++;
        }
    }

    public void unlock() {
        synchronized (countLock) {
            framebufferReferenceCount--;
            if (framebufferReferenceCount < 1) {
                isLocked = false;
            }
        }
    }

    public int[] getFrameBuffer() {
        return frameBuffer;
    }

    public int[] getTexture_out() {
        return texture_out;
    }

    public int[] getDepthRenderBuffer() {
        return depthRenderBuffer;
    }

    public void destoryBuffer() {
        if (frameBuffer != null) {
            GLES20.glDeleteFramebuffers(1, frameBuffer, 0);
            frameBuffer = null;
        }
        if (texture_out != null) {
            GLES20.glDeleteTextures(1, texture_out, 0);
            texture_out = null;
        }
        if (depthRenderBuffer != null) {
            GLES20.glDeleteRenderbuffers(1, depthRenderBuffer, 0);
            depthRenderBuffer = null;
        }
    }

    public int getBufferWidth() {
        return mWidth;
    }

    public int getBufferHigh() {
        return mHeight;
    }

    public void setFloat(boolean aFloat) {
        //浮点纹理需要5.0以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String str = GLES20.glGetString(GLES20.GL_EXTENSIONS);
            if (str.contains("GL_OES_texture_half_float")) {
                isFloat = aFloat;
            }
        }
    }

    public void activityFrameBuffer(int width, int height) {
        if (isInited) {
            return;
        }
        mWidth = width;
        mHeight = height;
        GLES20.glGenFramebuffers(1, frameBuffer, 0);
        GLES20.glGenRenderbuffers(1, depthRenderBuffer, 0);
        GLES20.glGenTextures(1, texture_out, 0);
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, frameBuffer[0]);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture_out[0]);

        if (isFloat) {
            int GL_OES_texture_half_float = 0x8D61;
            int GL_RGBA16F_EXT = 0x881a;
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GL_RGBA16F_EXT, width, height, 0, GLES20.GL_RGBA, GL_OES_texture_half_float, null);

            Log.i("FilterProcess", "use half float ");
        } else {
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
        }

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, texture_out[0], 0);
        GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, depthRenderBuffer[0]);
        GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, width, height);
        GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, depthRenderBuffer[0]);
        isInited = true;
    }

}
