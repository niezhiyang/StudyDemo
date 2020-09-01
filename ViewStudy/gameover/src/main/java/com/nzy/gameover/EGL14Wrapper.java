package com.nzy.gameover;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class EGL14Wrapper {
    private static final int EGL_RECORDABLE_ANDROID = 12610;
    private final String TAG = "EGL14Wrapper";
    public EGLDisplay mEGLDisplay;
    public EGLContext mEGLContext;
    public EGLConfig eglConfig;
    public EGLSurface eglSurface;
    public int esVersion = 3;
    public boolean isAlpha = false;
    public static EGL14 mEGL = null;
    private static EGL14Wrapper mInstance;

    public EGL14Wrapper() {
    }

    public EGL14Wrapper(boolean isAlpha) {
        this.isAlpha = isAlpha;
    }

    public static EGLContext getGloableContex() {
        if (mInstance == null) {
            mInstance = new EGL14Wrapper();
            mInstance.createDummyScreenEgl();
            mInstance.makeCurrent();
        }

        return mInstance.mEGLContext;
    }

    public static void release() {
        if (mInstance != null) {
            mInstance.releaseEgl();
            mInstance = null;
        }

    }

    @TargetApi(17)
    public void createDummyScreenEgl() {
        this.mEGLDisplay = EGL14.eglGetDisplay(0);
        if (EGL14.EGL_NO_DISPLAY == this.mEGLDisplay) {
            throw new RuntimeException("eglGetDisplay,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        } else {
            Log.e("EGL14Wrapper", "eglGetDisplay");
            int[] versions = new int[2];
            if (!EGL14.eglInitialize(this.mEGLDisplay, versions, 0, versions, 1)) {
                throw new RuntimeException("eglInitialize,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                Log.e("EGL14Wrapper", "eglInitialize");
                int[] configsCount = new int[1];
                EGLConfig[] configs = new EGLConfig[1];
                int[] configSpec = this.isAlpha ? new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 0, 12344} : new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 0, 12326, 0, 12344};
                EGL14.eglChooseConfig(this.mEGLDisplay, configSpec, 0, configs, 0, 1, configsCount, 0);
                if (configsCount[0] <= 0) {
                    throw new RuntimeException("eglChooseConfig,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                } else {
                    Log.e("EGL14Wrapper", "eglChooseConfig");
                    this.eglConfig = configs[0];
                    int[] surfaceAttribs = new int[]{12375, 1, 12374, 1, 12344};
                    int[] contextSpec = new int[]{12440, 2, 12344};
                    this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, this.eglConfig, EGL14.EGL_NO_CONTEXT, contextSpec, 0);
                    if (EGL14.EGL_NO_CONTEXT == this.mEGLContext) {
                        throw new RuntimeException("eglCreateContext,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                    } else {
                        Log.e("EGL14Wrapper", "eglCreateContext");
                        int[] values = new int[1];
                        EGL14.eglQueryContext(this.mEGLDisplay, this.mEGLContext, 12440, values, 0);
                        this.eglSurface = EGL14.eglCreatePbufferSurface(this.mEGLDisplay, this.eglConfig, surfaceAttribs, 0);
                        if (null != this.eglSurface && EGL14.EGL_NO_SURFACE != this.eglSurface) {
                            Log.e("EGL14Wrapper", "eglCreatePbufferSurface");
                        } else {
                            throw new RuntimeException("eglCreateWindowSurface,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        }
                    }
                }
            }
        }
    }

    @TargetApi(17)
    public void createDummyScreenEgl(EGLContext ctx) {
        this.mEGLDisplay = EGL14.eglGetDisplay(0);
        if (EGL14.EGL_NO_DISPLAY == this.mEGLDisplay) {
            throw new RuntimeException("eglGetDisplay,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        } else {
            int[] versions = new int[2];
            if (!EGL14.eglInitialize(this.mEGLDisplay, versions, 0, versions, 1)) {
                throw new RuntimeException("eglInitialize,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                int[] configsCount = new int[1];
                EGLConfig[] configs = new EGLConfig[1];
                int[] configSpec = this.isAlpha ? new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 0, 12344} : new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 0, 12326, 0, 12344};
                EGL14.eglChooseConfig(this.mEGLDisplay, configSpec, 0, configs, 0, 1, configsCount, 0);
                if (configsCount[0] <= 0) {
                    throw new RuntimeException("eglChooseConfig,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                } else {
                    this.eglConfig = configs[0];
                    int[] surfaceAttribs = new int[]{12375, 1, 12374, 1, 12344};
                    int[] contextSpec = new int[]{12440, 2, 12344};
                    if (ctx != null) {
                        this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, this.eglConfig, ctx, contextSpec, 0);
                    } else {
                        this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, this.eglConfig, EGL14.EGL_NO_CONTEXT, contextSpec, 0);
                    }

                    if (EGL14.EGL_NO_CONTEXT == this.mEGLContext) {
                        throw new RuntimeException("eglCreateContext,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                    } else {
                        int[] values = new int[1];
                        EGL14.eglQueryContext(this.mEGLDisplay, this.mEGLContext, 12440, values, 0);
                        this.eglSurface = EGL14.eglCreatePbufferSurface(this.mEGLDisplay, this.eglConfig, surfaceAttribs, 0);
                        if (null == this.eglSurface || EGL14.EGL_NO_SURFACE == this.eglSurface) {
                            throw new RuntimeException("eglCreateWindowSurface,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        }
                    }
                }
            }
        }
    }

    @TargetApi(17)
    public void createMediaCodecEgl(EGLContext sharedContext, Object mediaInputSurface) {
        this.mEGLDisplay = EGL14.eglGetDisplay(0);
        if (EGL14.EGL_NO_DISPLAY == this.mEGLDisplay) {
            throw new RuntimeException("eglGetDisplay,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        } else {
            int[] versions = new int[2];
            if (!EGL14.eglInitialize(this.mEGLDisplay, versions, 0, versions, 1)) {
                throw new RuntimeException("eglInitialize,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                int[] configsCount = new int[1];
                EGLConfig[] configs = new EGLConfig[1];
                int[] configSpec = this.isAlpha ? new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12610, 1, 12325, 16, 12326, 0, 12344} : new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12610, 1, 12325, 0, 12326, 0, 12344};
                EGL14.eglChooseConfig(this.mEGLDisplay, configSpec, 0, configs, 0, 1, configsCount, 0);
                if (configsCount[0] <= 0) {
                    throw new RuntimeException("eglChooseConfig,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                } else {
                    this.eglConfig = configs[0];
                    int[] surfaceAttribs = new int[]{12344};
                    int[] contextSpec = new int[]{12440, 2, 12344};
                    this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, this.eglConfig, sharedContext, contextSpec, 0);
                    if (EGL14.EGL_NO_CONTEXT == this.mEGLContext) {
                        throw new RuntimeException("eglCreateContext,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                    } else {
                        int[] values = new int[1];
                        EGL14.eglQueryContext(this.mEGLDisplay, this.mEGLContext, 12440, values, 0);
                        this.eglSurface = EGL14.eglCreateWindowSurface(this.mEGLDisplay, this.eglConfig, mediaInputSurface, surfaceAttribs, 0);
                        if (null == this.eglSurface || EGL14.EGL_NO_SURFACE == this.eglSurface) {
                            throw new RuntimeException("eglCreateWindowSurface,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        }
                    }
                }
            }
        }
    }

    @TargetApi(17)
    public void createScreenEgl(EGLContext sharedContext, Object screenSurface) {
        this.mEGLDisplay = EGL14.eglGetDisplay(0);
        if (EGL14.EGL_NO_DISPLAY == this.mEGLDisplay) {
            throw new RuntimeException("eglGetDisplay,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        } else {
            int[] versions = new int[2];
            if (!EGL14.eglInitialize(this.mEGLDisplay, versions, 0, versions, 1)) {
                throw new RuntimeException("eglInitialize,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                int[] configsCount = new int[1];
                EGLConfig[] configs = new EGLConfig[1];
                int[] configSpec = this.isAlpha ? new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 0, 12344} : new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 0, 12326, 0, 12344};
                EGL14.eglChooseConfig(this.mEGLDisplay, configSpec, 0, configs, 0, 1, configsCount, 0);
                if (configsCount[0] <= 0) {
                    throw new RuntimeException("eglChooseConfig,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                } else {
                    this.eglConfig = configs[0];
                    int[] surfaceAttribs = new int[]{12344};
                    int[] contextSpec = new int[]{12440, 2, 12344};
                    this.mEGLContext = EGL14.eglCreateContext(this.mEGLDisplay, this.eglConfig, sharedContext, contextSpec, 0);
                    if (EGL14.EGL_NO_CONTEXT == this.mEGLContext) {
                        throw new RuntimeException("eglCreateContext,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                    } else {
                        int[] values = new int[1];
                        EGL14.eglQueryContext(this.mEGLDisplay, this.mEGLContext, 12440, values, 0);
                        this.eglSurface = EGL14.eglCreateWindowSurface(this.mEGLDisplay, this.eglConfig, screenSurface, surfaceAttribs, 0);
                        if (null == this.eglSurface || EGL14.EGL_NO_SURFACE == this.eglSurface) {
                            throw new RuntimeException("eglCreateWindowSurface,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
                        }
                    }
                }
            }
        }
    }

    @TargetApi(17)
    public void releaseEgl() {
        if (this.mEGLDisplay != null && this.eglSurface != null && this.mEGLContext != null) {
            if (!EGL14.eglMakeCurrent(this.mEGLDisplay, this.eglSurface, this.eglSurface, this.mEGLContext)) {
            }

            EGL14.eglDestroySurface(this.mEGLDisplay, this.eglSurface);
            EGL14.eglDestroyContext(this.mEGLDisplay, this.mEGLContext);
            EGL14.eglTerminate(this.mEGLDisplay);
        }

        EGL14.eglMakeCurrent(this.mEGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
    }

    @TargetApi(17)
    public boolean makeCurrent(EGLSurface readSurface) {
        if (this.mEGLDisplay != null && this.eglSurface != null && this.mEGLContext != null) {
            if (!EGL14.eglMakeCurrent(this.mEGLDisplay, this.eglSurface, readSurface, this.mEGLContext)) {
                throw new RuntimeException("eglMakeCurrent,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @TargetApi(17)
    public boolean makeCurrent() {
        if (this.mEGLDisplay != null && this.eglSurface != null && this.mEGLContext != null) {
            if (!EGL14.eglMakeCurrent(this.mEGLDisplay, this.eglSurface, this.eglSurface, this.mEGLContext)) {
                throw new RuntimeException("eglMakeCurrent,failed:" + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @TargetApi(17)
    public void swapBuffer() {
        if (this.mEGLDisplay != null && this.eglSurface != null && !EGL14.eglSwapBuffers(this.mEGLDisplay, this.eglSurface)) {
            throw new RuntimeException("eglSwapBuffers,failed!");
        }
    }

    public static int createOneExternalTexture() {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        GLES20.glBindTexture(36197, textures[0]);
        checkGlError("glBindTexture mTextureID");
        GLES20.glTexParameterf(3553, 10241, 9728.0F);
        GLES20.glTexParameterf(3553, 10240, 9729.0F);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        checkGlError("glTexParameter");
        return textures[0];
    }

    public static void releaseTexture(int texutreId) {
        int[] textureArray = new int[]{texutreId};
        GLES20.glDeleteTextures(1, textureArray, 0);
    }

    @TargetApi(17)
    public int getHeight() {
        int[] value = new int[1];
        if (this.mEGLDisplay != null && this.eglSurface != null && this.mEGLContext != null) {
            EGL14.eglQuerySurface(this.mEGLDisplay, this.eglSurface, 12375, value, 0);
        }

        return value[0];
    }

    @TargetApi(17)
    public int getWidth() {
        int[] value = new int[1];
        if (this.mEGLDisplay != null && this.eglSurface != null && this.mEGLContext != null) {
            EGL14.eglQuerySurface(this.mEGLDisplay, this.eglSurface, 12374, value, 0);
        }

        return value[0];
    }

    private static void checkGlError(String op) {
        int error = GLES20.glGetError();
        if (error != 0) {
            String msg = op + ": glError 0x" + Integer.toHexString(error);
            throw new RuntimeException(msg);
        }
    }
}
