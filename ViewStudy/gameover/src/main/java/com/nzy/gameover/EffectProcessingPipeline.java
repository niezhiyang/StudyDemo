package com.nzy.gameover;

import android.hardware.Camera;
import android.opengl.EGLContext;
import android.os.Build;
import android.os.HandlerThread;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niezhiyang
 * since 2020/8/29
 */
public class EffectProcessingPipeline implements EffectSurfaceRender.EffectRender, IEffectProcessingPipeline{
    private static boolean VERSION_RENDER = false;
    private boolean rendering = false;
    private GLRenderer mRootInput;
    private boolean isAlphaRender = false;
    private Object mapLockObj;
    protected ConcurrentHashMap<String, EGL14Wrapper> mCodecWrapperMap;
    protected ConcurrentHashMap<String, EffectSurfaceRender> mRenderMap = new ConcurrentHashMap();
    protected ConcurrentHashMap<String, GLRenderer> mRootInputMap = new ConcurrentHashMap();
    protected ConcurrentHashMap<String, Queue<Runnable>> mRunOnDawMap = new ConcurrentHashMap();
    protected ConcurrentHashMap<String, Queue<Runnable>> mRunOnDawEndMap = new ConcurrentHashMap();
    protected ConcurrentHashMap<String, List<GLRenderer>> mFilterToDestoryMap = new ConcurrentHashMap();
    protected EffectSurfaceRender mRootRender;
    private HandlerThread mCmdHT = new HandlerThread("RecordingCmdHandle", 19);
    private EffectProcessingPipeline.RenderListener renderListener;
    private EffectProcessingPipeline.EglCreateListener eglCreateListener;
    private EGL14Wrapper mDummyScreen = null;
    private Object mDummyObj = new Object();
    private Object mScreenTexture;
    private Object mCodecMapLock = new Object();
    private Object mRendererLock = new Object();
    public EGLContext eglContext;
    private EffectProcessingPipeline.OnFPSRateListener onFPSRateListener;

    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    public EffectSurfaceRender getRootRender() {
        return this.mRootRender;
    }

    public EffectProcessingPipeline() {
        this.mCmdHT.start();
    }

    public void clearOnDraw(String key) {
        Queue<Runnable> mRunOnDraw = (Queue)this.mRunOnDawMap.get(key);
        mRunOnDraw.clear();
    }

    public void runOnDraw(Runnable runnable, String key) {
        Queue<Runnable> mRunOnDraw = (Queue)this.mRunOnDawMap.get(key);
        if (mRunOnDraw != null) {
            mRunOnDraw.add(runnable);
        }

    }

    public void runOnDrawEnd(Runnable runnable, String key) {
        Queue<Runnable> mRunOnDrawEnd = (Queue)this.mRunOnDawEndMap.get(key);
        if (mRunOnDrawEnd != null) {
            mRunOnDrawEnd.add(runnable);
        }

    }

    protected void runAllEnd(String key) {
        Queue<Runnable> queue = (Queue)this.mRunOnDawEndMap.get(key);
        if (queue != null) {
            synchronized(queue) {
                while(!queue.isEmpty()) {
                    Runnable poll = (Runnable)queue.poll();
                    if (poll != null) {
                        poll.run();
                    }
                }
            }
        }

    }

    protected void runAll(String key) {
        Queue<Runnable> queue = (Queue)this.mRunOnDawMap.get(key);
        if (queue != null) {
            synchronized(queue) {
                while(!queue.isEmpty()) {
                    Runnable poll = (Runnable)queue.poll();
                    if (poll != null) {
                        poll.run();
                    }
                }
            }
        }

    }

    public void addFilterToDestroy(final GLRenderer renderer, final String renderKey) {
        this.runOnDrawEnd(new Runnable() {
            public void run() {
                ArrayList<GLRenderer> filtersToDestroy = (ArrayList)EffectProcessingPipeline.this.mFilterToDestoryMap.get(renderKey);
                if (null != filtersToDestroy) {
                    filtersToDestroy.add(renderer);
                }

            }
        }, renderKey);
    }

    public synchronized void changeRootRender(GLRenderer rootInput) {
        EffectSurfaceRender render = this.getRenderByFilter(rootInput);
        render.setMomoRender(this);
        this.mRootInput = rootInput;
        this.mRootRender = render;
        this.mRenderMap.put(rootInput.toString(), this.mRootRender);
        render.setRenderKey(rootInput.toString());
        GLRenderer input = (GLRenderer)this.mRootInputMap.get(rootInput.toString());
        if (null == input) {
            this.mRootInputMap.put(rootInput.toString(), rootInput);
            Queue<Runnable> mRunOnDraw = new LinkedList();
            Queue<Runnable> mRunOnDrawEnd = new LinkedList();
            this.mRunOnDawEndMap.put(rootInput.toString(), mRunOnDrawEnd);
            this.mRunOnDawMap.put(rootInput.toString(), mRunOnDraw);
        }

    }

    public synchronized void startRendering(Object screenTexture) {
        if (this.mRootInput != null) {
            this.rendering = true;
            this.mScreenTexture = screenTexture;
            this.mRootRender.prepare();
            this.mRootRender.startRender(screenTexture);
        }

    }

    public synchronized void resumeRendering(Object screenTexture) {
        this.rendering = true;
        this.mScreenTexture = screenTexture;
        Iterator var2 = this.mRenderMap.values().iterator();

        while(var2.hasNext()) {
            EffectSurfaceRender render = (EffectSurfaceRender)var2.next();
            if (render == this.mRootRender) {
                render.resumeRender(this.mScreenTexture);
            } else {
                render.resumeRender((Object)null);
            }
        }

    }

    public synchronized void setIsChangeFixSize(boolean is) {
        Iterator var2 = this.mRenderMap.values().iterator();

        while(var2.hasNext()) {
            EffectSurfaceRender render = (EffectSurfaceRender)var2.next();
            render.setIsChangeFixSize(is);
        }

    }

    public synchronized void pauseRendering() {
        this.rendering = false;
        this.mScreenTexture = null;
        Iterator var1 = this.mRenderMap.values().iterator();

        while(var1.hasNext()) {
            EffectSurfaceRender render = (EffectSurfaceRender)var1.next();
            render.pauseRender();
        }

    }

    public synchronized void addRootRenderer(GLRenderer rootInput) {
        Queue<Runnable> mRunOnDraw = new LinkedList();
        Queue<Runnable> mRunOnDrawEnd = new LinkedList();
        EffectSurfaceRender momoRender = this.getRenderByFilter(rootInput);
        if (null == this.mRootInput) {
            this.mRootRender = momoRender;
            this.mRootInput = rootInput;
            this.mRenderMap.put(rootInput.toString(), this.mRootRender);
        } else {
            this.mRenderMap.put(rootInput.toString(), momoRender);
        }

        momoRender.setMomoRender(this);
        momoRender.setRenderKey(rootInput.toString());
        this.mRootInputMap.put(rootInput.toString(), rootInput);
        this.mRunOnDawEndMap.put(rootInput.toString(), mRunOnDrawEnd);
        this.mRunOnDawMap.put(rootInput.toString(), mRunOnDraw);
        this.mFilterToDestoryMap.put(rootInput.toString(), new ArrayList());
    }

    public EffectSurfaceRender getRenderByFilter(GLRenderer renderer) {
        EffectSurfaceRender render = (EffectSurfaceRender)this.mRenderMap.get(renderer.toString());
        if (null == render) {
            render = new EffectSurfaceRender();
            render.setRenderKey(render.toString());
            this.mRenderMap.put(renderer.toString(), render);
        }

        return render;
    }

    public void removeRenderByFilter(GLRenderer renderer) {
        this.mRenderMap.remove(renderer.toString());
    }

    public boolean isRendering() {
        return this.rendering;
    }

    public synchronized void onDestory() {
        Iterator var2;
        synchronized(this.mRootInputMap) {
            var2 = this.mRootInputMap.values().iterator();

            GLRenderer root;
            while(var2.hasNext()) {
                root = (GLRenderer)var2.next();
                this.runAllEnd(root.toString());
            }

            var2 = this.mRootInputMap.values().iterator();

            while(true) {
                if (!var2.hasNext()) {
                    var2 = this.mRenderMap.values().iterator();

                    while(var2.hasNext()) {
                        EffectSurfaceRender renderer = (EffectSurfaceRender)var2.next();
                        renderer.finishRender();
                    }

                    this.mRenderMap.clear();
                    this.mRootInputMap.clear();
                    this.mRunOnDawEndMap.clear();
                    this.mRunOnDawMap.clear();
                    this.mRootRender = null;
                    this.mRootInput = null;
                    break;
                }

                root = (GLRenderer)var2.next();
                root.destroy();
            }
        }

        synchronized(this.mDummyObj) {
            if (this.mDummyScreen != null) {
                this.mDummyScreen.releaseEgl();
                this.mDummyScreen = null;
            }
        }

        synchronized(this.mFilterToDestoryMap) {
            var2 = this.mFilterToDestoryMap.values().iterator();

            while(var2.hasNext()) {
                List<GLRenderer> renderers = (List)var2.next();
                Iterator var4 = renderers.iterator();

                while(var4.hasNext()) {
                    GLRenderer renderer = (GLRenderer)var4.next();
                    renderer.destroy();
                }
            }

            this.mFilterToDestoryMap.clear();
        }
    }

    public void onChangeRenderSizeFinish() {
        if (this.eglCreateListener != null) {
            this.eglCreateListener.onRenderSizeChangedFinish();
        }

    }

    public void onSurfaceRenderSizeChange(int width, int height) {
        if (this.eglCreateListener != null) {
            this.eglCreateListener.onSurfaceRenderSizeChanged(width, height);
        }

    }

    public void onDestory(EffectSurfaceRender surfaceRender) {
        synchronized(this.mRootInputMap) {
            EffectSurfaceRender render = (EffectSurfaceRender)this.mRenderMap.remove(surfaceRender.getRenderKey());
            if (render == null) {
                Log.e("Effect", "onDestory" + render + "bugs");
            }

            GLRenderer renderer = (GLRenderer)this.mRootInputMap.remove(surfaceRender.getRenderKey());
            Log.e("Effect", "Render to destory" + renderer);
            renderer.destroy();
            this.runAllEnd(surfaceRender.getRenderKey());
            this.mRunOnDawEndMap.remove(surfaceRender.getRenderKey());
            this.mRunOnDawMap.remove(surfaceRender.getRenderKey());
            if (this.mRootInputMap.size() == 0) {
                if (null != this.eglCreateListener) {
                    this.eglCreateListener.onEglDestory();
                }

                this.mRootRender = null;
                this.mRootInput = null;
                synchronized(this.mDummyObj) {
                    if (this.mDummyScreen != null) {
                        this.mDummyScreen.releaseEgl();
                        this.mDummyScreen = null;
                    }
                }
            }
        }

        synchronized(this.mFilterToDestoryMap) {
            Iterator var12 = this.mFilterToDestoryMap.values().iterator();

            while(var12.hasNext()) {
                List<GLRenderer> renderers = (List)var12.next();
                Iterator var5 = renderers.iterator();

                while(var5.hasNext()) {
                    GLRenderer renderer = (GLRenderer)var5.next();
                    renderer.destroy();
                }
            }

            this.mFilterToDestoryMap.clear();
        }
    }

    public void onRemove(EffectSurfaceRender render) {
        if (this.mRenderMap != null) {
            this.mRenderMap.remove(render.getRenderKey());
            this.mRootInputMap.remove(render.getRenderKey());
        }

    }

    public void onStartRender() {
        if (null != this.renderListener) {
            this.renderListener.onRenderStart();
        }

    }

    public void onLogInfo(EffectSurfaceRender render, int fps, int renderUse, int codecUse, int codecFps) {
        if (null != this.onFPSRateListener) {
            this.onFPSRateListener.onFpsInfoChange(render, fps, renderUse, codecUse, codecFps);
        }

    }

    public void finishRender() {
        Iterator var1 = this.mRenderMap.values().iterator();

        while(var1.hasNext()) {
            EffectSurfaceRender render = (EffectSurfaceRender)var1.next();
            render.finishRender();
        }

        if (Build.VERSION.SDK_INT >= 18) {
            if (this.mCmdHT != null) {
                this.mCmdHT.quitSafely();
            }
        } else if (this.mCmdHT != null) {
            this.mCmdHT.quit();
        }

        this.mRenderMap.clear();
    }

    public boolean prepared() {
        return true;
    }

    public void onDrawFrame(EGL14Wrapper egl14Wrapper, EffectSurfaceRender render) {
        long drawUse = 0L;
        this.runAll(render.getRenderKey());
        if (this.isRendering()) {
            GLRenderer rootRender = null;
            synchronized(this.mRootInputMap) {
                rootRender = (GLRenderer)this.mRootInputMap.get(render.getRenderKey());
            }

            if (VERSION_RENDER) {
                synchronized(this.mRendererLock) {
                    if (null != rootRender) {
                        rootRender.onDrawFrame();
                    }
                }
            } else if (null != rootRender) {
                rootRender.onDrawFrame();
            }
        }

        ArrayList<GLRenderer> renders = (ArrayList)this.mFilterToDestoryMap.get(render.getRenderKey());
        if (null != renders) {
            Iterator var6 = renders.iterator();

            while(var6.hasNext()) {
                GLRenderer renderer = (GLRenderer)var6.next();
                renderer.destroy();
            }

            renders.clear();
        }

        this.runAllEnd(render.getRenderKey());
    }

    public void setRenderListener(EffectProcessingPipeline.RenderListener renderListener) {
        this.renderListener = renderListener;
    }

    public EGL14Wrapper getDummyScreen() {
        synchronized(this.mDummyObj) {
            if (this.mDummyScreen == null) {
                this.mDummyScreen = new EGL14Wrapper(this.isAlphaRender);
                this.mDummyScreen.createDummyScreenEgl();
            }
        }

        return this.mDummyScreen;
    }

    public ConcurrentHashMap<String, EGL14Wrapper> getCodecWrapperMap() {
        synchronized(this.mCodecMapLock) {
            if (null == this.mCodecWrapperMap) {
                this.mCodecWrapperMap = new ConcurrentHashMap();
            }
        }

        return this.mCodecWrapperMap;
    }

    public void onCreatedEgl(final EffectSurfaceRender surfaceRender) {
        this.runOnDraw(new Runnable() {
            public void run() {
                if (EffectProcessingPipeline.this.eglCreateListener != null && surfaceRender == EffectProcessingPipeline.this.mRootRender) {
                    EffectProcessingPipeline.this.eglCreateListener.onCreatedEgl();
                }

            }
        }, surfaceRender.getRenderKey());
    }

    public boolean isAlphaRender() {
        return this.isAlphaRender;
    }

    public void setOnFPSRateListener(EffectProcessingPipeline.OnFPSRateListener onFPSRateListener) {
        this.onFPSRateListener = onFPSRateListener;
    }

    public void setOnRenderListener(EffectProcessingPipeline.RenderListener renderListener) {
        this.renderListener = renderListener;
    }

    public void setEglCreateListener(EffectProcessingPipeline.EglCreateListener eglCreateListener) {
        this.eglCreateListener = eglCreateListener;
    }

    public void setAlphaRender(boolean alphaRender) {
        this.isAlphaRender = alphaRender;
    }

    public interface OnRtcStatusListener {
        void OnRtcStatusUpdate(Object var1);
    }

    public interface OnFPSRateListener {
        void onFpsInfoChange(EffectSurfaceRender var1, int var2, int var3, int var4, int var5);
    }

    public interface OnVideoPreviewSizeSetListener {
        void onPreviewSizeSet(int var1, int var2);
    }

    public interface OnCameraSetListener {
        void OnCameraSet(Camera var1);
    }

    public interface PcmDateCallback {
        void onPcmDateCallback(long var1, byte[] var3, int var4, boolean var5, Object var6);
    }

    public interface JsonDateCallback {
        void JsonDateCallback(byte[] var1, int var2);
    }

    public interface EglCreateListener {
        void onCreatedEgl();

        void onRenderSizeChangedFinish();

        void onSurfaceRenderSizeChanged(int var1, int var2);

        void onEglDestory();
    }

    public interface RenderListener {
        void onRenderStart();

        void onRenderStoped();
    }
}
