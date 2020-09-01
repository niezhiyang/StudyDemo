package com.nzy.gameover;

import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.GLES20;

import androidx.annotation.RequiresApi;

public class EffectSurfaceRender {
    protected EffectSurfaceRender.RenderThread mRenderThread;
    protected EffectSurfaceRender.EffectRender mMomoRender;
    protected EGL14Wrapper mEGLScreen = null;
    protected EGL14Wrapper mDummyScreen = null;
    protected Object mScreenTexture;
    private final Object syncObj = new Object();
    private final Object mInitSync = new Object();
    protected final Object mFrameSyncObject = new Object();
    private boolean isRenderThreadInit;
    protected Boolean mFrameAvailable = false;
    protected boolean isRecording = false;
    protected int numberOfRender = 0;
    private boolean enableEmptyRender;
    protected String renderKey;
    protected Runnable onDrawBefore;
    protected Runnable onDrawAfter;
    private boolean isResumeRender = false;
    private boolean isChangeFixSize = false;
    private boolean mFirstFrameRender = false;
    protected boolean pauseRender = false;
    protected long now;
    protected long oldnow;
    protected long count = 0L;
    protected int i = 0;
    protected int t = 0;
    protected int mRenderFRate;
    public int mRenderTime = 30;
    public int mCodecFRate = 0;
    protected int fixedCount = 0;
    protected int curWidth;
    protected int curHeight;

    public EffectSurfaceRender() {
    }

    public String getRenderKey() {
        return this.renderKey;
    }

    public Object getEGLScreen() {
        return this.mScreenTexture;
    }

    public void setRenderKey(String renderKey) {
        this.renderKey = renderKey;
    }

    public void pauseRender() {
        this.mScreenTexture = null;
        if (this.mEGLScreen != null) {
            this.mEGLScreen.releaseEgl();
            this.mEGLScreen = null;
        }

        this.initScreenRender();
    }

    public void resumeRender(Object screenTexture) {
        this.mScreenTexture = screenTexture;
        if (this.mEGLScreen != null) {
            this.mEGLScreen.releaseEgl();
            this.mEGLScreen = null;
        }

        this.initScreenRender();
    }

    public void setIsChangeFixSize(boolean isChangeFixSize) {
        synchronized(this.mFrameSyncObject) {
            this.isChangeFixSize = isChangeFixSize;
        }
    }

    public void prepare() {
        if (this.mRenderThread == null) {
            this.mRenderThread = new EffectSurfaceRender.RenderThread("EffectPipRender");
        }

        this.mRenderThread.setPriority(10);
        this.mRenderThread.start();
        synchronized(this.mInitSync) {
            try {
                if (this.isRenderThreadInit) {
                    this.mInitSync.notifyAll();
                } else {
                    this.mInitSync.wait();
                }
            } catch (InterruptedException var4) {
            }

        }
    }

    public void startRender(Object screenTexture) {
        synchronized(this.mInitSync) {
            this.mScreenTexture = screenTexture;
            this.initScreenRender();
            this.mInitSync.notifyAll();
        }
    }

    public void stopRender() {
        this.mScreenTexture = null;
        if (this.mEGLScreen != null) {
            this.mEGLScreen.releaseEgl();
            this.mEGLScreen = null;
        }

    }

    public void setMomoRender(EffectSurfaceRender.EffectRender render) {
        this.mMomoRender = render;
    }

    public void enableEmptyRender(boolean enable) {
        this.enableEmptyRender = enable;
    }

    protected void onDrawFrame() {
        try {
            long renderStart = System.currentTimeMillis();
            if (null != this.onDrawBefore) {
                this.onDrawBefore.run();
                this.onDrawBefore = null;
            }

            if (this.enableEmptyRender && null != this.mDummyScreen) {
                this.mDummyScreen.makeCurrent();
                this.mMomoRender.onDrawFrame(this.mDummyScreen, this);
                this.mDummyScreen.swapBuffer();
                return;
            }

            int a;
            if (this.mEGLScreen != null && this.mScreenTexture != null) {
                boolean hasChangedSize = false;
                int height = this.mEGLScreen.getWidth();
                a = this.mEGLScreen.getHeight();
                if ((a != this.curWidth || height != this.curHeight) && this.curWidth > 0) {
                    this.mMomoRender.onSurfaceRenderSizeChange(a, height);
                    hasChangedSize = true;
                }

                this.curWidth = a;
                this.curHeight = height;
                if (null != this.mMomoRender) {
                    this.mEGLScreen.makeCurrent();
                    this.mMomoRender.onDrawFrame(this.mEGLScreen, this);
                    if (!this.mFirstFrameRender) {
                        this.mFirstFrameRender = true;
                    }

                    try {
                        this.mEGLScreen.swapBuffer();
                    } catch (Exception var7) {
                    }
                }

                if (hasChangedSize) {
                    this.mMomoRender.onChangeRenderSizeFinish();
                }
            }

            if (this.numberOfRender == 1) {
                this.mMomoRender.onStartRender();
            }

            long renderFinish = System.currentTimeMillis();
            ++this.i;
            this.now = System.nanoTime() / 1000L;
            if (this.i > 3) {
                this.t = (int)((long)this.t + (this.now - this.oldnow));
                ++this.count;
            }

            if (this.i > 20) {
                long den = (long)this.t / this.count;
                if (den > 0L) {
                    this.mRenderFRate = (int)(1000000L / den + 1L);
                }

                if (this.mRenderFRate > 0) {
                    this.mRenderTime = 1000 / this.mRenderFRate;
                }

                this.now = this.oldnow = this.count = 0L;
                this.i = this.t = 0;
            }

            this.oldnow = this.now;
            if (null != this.mMomoRender) {
                a = (int)(renderFinish - renderStart);
                this.mMomoRender.onLogInfo(this, this.mRenderFRate, a < 0 ? 0 : a, 0, this.mCodecFRate);
            }
        } catch (Throwable var8) {
            this.requestRender();
            var8.printStackTrace();
        }

    }

    protected SurfaceTexture getScreenTextureShow() {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0);
        GLES20.glBindTexture(36197, textures[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0F);
        GLES20.glTexParameterf(36197, 10240, 9729.0F);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return new SurfaceTexture(textures[0]);
    }

    protected void initScreenRender() {
        if (null != this.mMomoRender && this.mDummyScreen == null && this.mEGLScreen == null) {
            this.mDummyScreen = this.mMomoRender.getDummyScreen();
        }

        if (this.mEGLScreen == null && this.mDummyScreen != null) {
            if (this.mScreenTexture == null) {
                this.mScreenTexture = this.getScreenTextureShow();
            }

            try {
                this.mEGLScreen = new EGL14Wrapper(this.mMomoRender.isAlphaRender());
                this.mEGLScreen.createScreenEgl(this.mDummyScreen.mEGLContext, this.mScreenTexture);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            if (this.mMomoRender != null) {
                this.mMomoRender.onCreatedEgl(this);
            }
        }

    }

    private void resumeRender() {
        if (this.mEGLScreen != null) {
            this.mEGLScreen.releaseEgl();
            this.mEGLScreen = null;
        }

        this.isResumeRender = false;
    }

    public EGLContext getEGLContext() {
        return this.mDummyScreen != null ? this.mDummyScreen.mEGLContext : null;
    }

    public void requestRender(Runnable runnable, Runnable runnable2) {
        if (!this.pauseRender && !this.mFrameAvailable) {
            synchronized(this.mFrameSyncObject) {
                runnable2.run();
                this.onDrawBefore = runnable;
                this.mFrameAvailable = true;
                this.mFrameSyncObject.notifyAll();
            }
        }
    }

    public void requestRender() {
        if (!this.pauseRender && !this.mFrameAvailable) {
            synchronized(this.mFrameSyncObject) {
                this.mFrameAvailable = true;
                this.mFrameSyncObject.notifyAll();
            }
        }
    }

    public void finishRender() {
        if (null != this.mRenderThread) {
            this.mFrameAvailable = false;
            this.isResumeRender = false;
            this.isRecording = false;
            this.mRenderThread.quit();
            this.mRenderThread = null;
        }

    }

    public void finishSingleRender() {
        if (null != this.mRenderThread) {
            this.mFrameAvailable = false;
            this.isResumeRender = false;
            this.isRecording = false;
            if (null != this.mMomoRender) {
                this.mMomoRender.onRemove(this);
            }

            this.mMomoRender = null;
            this.mEGLScreen = null;
            this.mRenderThread.quit();
            this.mRenderThread = null;
        }

    }

    private void clearBuffer() {
        try {
            if (this.mEGLScreen != null && this.mScreenTexture != null) {
                this.mEGLScreen.makeCurrent();
                GLES20.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
                GLES20.glClear(16384);
                this.mEGLScreen.swapBuffer();
            }
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

    }

    private void releaseEgl() {
        if (this.mEGLScreen != null) {
            this.mEGLScreen.releaseEgl();
            this.mEGLScreen = null;
        }

        if (null != this.mMomoRender) {
            this.mMomoRender.onDestory(this);
            this.mMomoRender = null;
        }

    }

    public void resetFirstrenderStatus() {
        this.mFirstFrameRender = false;
    }

    public interface EffectRender {
        boolean prepared();

        void onDrawFrame(EGL14Wrapper var1, EffectSurfaceRender var2);

        void onChangeRenderSizeFinish();

        void onSurfaceRenderSizeChange(int var1, int var2);

        void onDestory(EffectSurfaceRender var1);

        void onRemove(EffectSurfaceRender var1);

        void onStartRender();

        void onLogInfo(EffectSurfaceRender var1, int var2, int var3, int var4, int var5);

        EGL14Wrapper getDummyScreen();

        void onCreatedEgl(EffectSurfaceRender var1);

        boolean isAlphaRender();
    }

    protected class RenderThread extends Thread {
        protected final int TIMEOUT_MS = 100;
        public boolean mThNeedExit = false;

        RenderThread(String name) {
            super(name);
        }

        public void quit() {
            this.mThNeedExit = true;
            this.interrupt();
        }

        @RequiresApi(
            api = 17
        )
        public void run() {
            synchronized(EffectSurfaceRender.this.mInitSync) {
                EffectSurfaceRender.this.isRenderThreadInit = true;
                EffectSurfaceRender.this.mInitSync.notifyAll();
            }

            do {
                synchronized(EffectSurfaceRender.this.mFrameSyncObject) {
                    if (!EffectSurfaceRender.this.mFrameAvailable) {
                        try {
                            EffectSurfaceRender.this.mFrameSyncObject.wait(100L);
                        } catch (InterruptedException var4) {
                            var4.printStackTrace();
                        }
                    }

                    if (EffectSurfaceRender.this.mFrameAvailable) {
                        if (EffectSurfaceRender.this.mScreenTexture != null && EffectSurfaceRender.this.mEGLScreen != null) {
                            EffectSurfaceRender.this.mFrameAvailable = false;
                        } else {
                            EffectSurfaceRender.this.mFrameAvailable = true;
                        }

                        EffectSurfaceRender.this.onDrawFrame();
                    }
                }
            } while(!this.mThNeedExit && !this.isInterrupted());

            EffectSurfaceRender.this.clearBuffer();
            EffectSurfaceRender.this.releaseEgl();
        }
    }
}
