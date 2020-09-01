package com.nzy.gameover;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.util.Log;
import android.view.Surface;

import java.io.IOException;

import androidx.annotation.RequiresApi;

public class EffectPlayerInput extends MMTextureResourceInput implements SurfaceTexture.OnFrameAvailableListener, ISourceInput, IMediaPlayer, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {
    private static final String LOG_TAG = "EffectPlayerInput";
    private EffectSurfaceRender mMomoSurfaceRender;
    protected MediaPlayer mMediaPlayer;
    String rtmpPath;
    private Surface mSurface;
    private Context mContext;
    int inputWidth = 480;
    int inputHeight = 480;
    long start_time;
    boolean isPrepared = false;
    private AssetFileDescriptor fd;
   IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener;
   IMediaPlayer.OnCompletionListener onCompletionListener;
    RenderTimestampListener renderTimestampListener;
   IMediaPlayer.OnErrorListener onErrorListener;
    int mFps = 30;
    protected boolean isFistFrame = false;

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public EffectPlayerInput(Context context, AssetFileDescriptor fileDescriptor) {
        this.mContext = context;
        this.fd = fileDescriptor;
    }

    public EffectPlayerInput(Context context, String path) {
        this.mContext = context;
        this.rtmpPath = path;
    }

    public SurfaceTexture getScreenTexture() {
        this.deleteTexture();
        if (null == this.mText) {
            int[] textures = new int[1];
            GLES20.glGenTextures(1, textures, 0);
            GLES20.glBindTexture(36197, textures[0]);
            GLES20.glTexParameterf(36197, 10241, 9729.0F);
            GLES20.glTexParameterf(36197, 10240, 9729.0F);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            this.texture_in = textures[0];
            this.mText = new SurfaceTexture(this.texture_in);
            Log.e("zk", "zk init ijk texture");
        }

        return this.mText;
    }

    private void deleteTexture() {
        if (this.texture_in > 0) {
            int[] tex = new int[1];
            GLES20.glDeleteTextures(1, tex, 0);
            this.texture_in = 0;
        }

    }

    public void setMomoSurfaceRender(EffectSurfaceRender mMomoSurfaceRender) {
        this.mMomoSurfaceRender = mMomoSurfaceRender;
    }

    public GLTextureOutputRenderer getInput() {
        return this;
    }

    public void setFps(int fps) {
        this.mFps = fps;
    }

    public void start() {
        this.start_time = System.currentTimeMillis();
        Log.e("EffectPlayerInput", "openPublishHelp, start");
        if (this.mMediaPlayer != null) {
            this.stopAndReleasePlayer();
            Log.e("EffectPlayerInput", "openPublishHelp, <release> cost time:" + (System.currentTimeMillis() - this.start_time) + "ms");
        }

        try {
            if (null == this.mText) {
                this.mText = this.getScreenTexture();
                this.mSurface = new Surface(this.mText);
            }

            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setOnCompletionListener(this);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            this.mMediaPlayer.setOnErrorListener(this);
            this.mMediaPlayer.setOnInfoListener(this);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            this.mMediaPlayer.setSurface(this.mSurface);
            if (null != this.fd) {
                this.mMediaPlayer.setDataSource(this.fd.getFileDescriptor(), this.fd.getStartOffset(), this.fd.getLength());
            } else {
                this.mMediaPlayer.setDataSource(this.rtmpPath);
            }

            this.mMediaPlayer.prepareAsync();
            Log.e("EffectPlayerInput", "openPublishHelp, end, cost time:" + (System.currentTimeMillis() - this.start_time) + "ms");
        } catch (IOException var2) {
            Log.e("EffectPlayerInput", "openPublishHelp Unable to open content: " + this.rtmpPath);
            this.stop();
            this.catchErrorLog0();
        } catch (IllegalArgumentException var3) {
            Log.e("EffectPlayerInput", "openPublishHelp Unable to open content: " + this.rtmpPath);
            this.stop();
            this.catchErrorLog1();
        }
    }

    protected void catchErrorLog0() {
    }

    protected void catchErrorLog1() {
    }

    public void pause() {
        if (this.mMediaPlayer != null && this.isPrepared) {
            this.mMediaPlayer.pause();
        }

    }

    public void resume() {
        if (this.mMediaPlayer != null && this.isPrepared) {
            this.mMediaPlayer.start();
        }

    }

    public void seek(long t) {
        if (this.mMediaPlayer != null && this.isPrepared) {
            this.mMediaPlayer.seekTo((int)t);
        }

    }

    public void stop() {
        this.isPrepared = false;
        this.stopAndReleasePlayer();
    }

    private void stopAndReleasePlayer() {
        if (this.mMediaPlayer != null) {
            synchronized(this.mMediaPlayer) {
//                this.mMediaPlayer.setOnErrorListener((OnErrorListener)null);
//                this.mMediaPlayer.setOnCompletionListener((OnCompletionListener)null);
//                this.mMediaPlayer.setOnPreparedListener((OnPreparedListener)null);
//                this.mMediaPlayer.setOnVideoSizeChangedListener((OnVideoSizeChangedListener)null);
                this.mMediaPlayer.setSurface((Surface)null);
                this.mMediaPlayer.stop();
                this.mMediaPlayer.reset();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
            }

            if (this.mSurface != null) {
                this.mSurface.release();
                this.mSurface = null;
            }

        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        Log.e("EffectPlayerInput", "onFrameAvailable");
        this.mMomoSurfaceRender.requestRender();
    }

    public void onDrawFrame() {
        if (this.renderTimestampListener != null && this.mMediaPlayer != null) {
            this.renderTimestampListener.onRenderTimestampChanged((long)this.mMediaPlayer.getCurrentPosition());
        }

        this.loadTexture(this.texture_in, this.mText);
        super.onDrawFrame();
    }

    @RequiresApi(
        api = 15
    )
    public void initWithGLContext() {
        super.initWithGLContext();
        if (this.mMediaPlayer != null) {
            if (this.mText == null) {
                this.mText = this.getScreenTexture();
            }

            if (this.mSurface == null) {
                this.mSurface = new Surface(this.mText);
            }

            this.mText.setDefaultBufferSize(this.inputWidth, this.inputHeight);
            this.mText.setOnFrameAvailableListener(this);
            this.mMediaPlayer.setSurface(this.mSurface);
        }

    }

    public void destroy() {
        super.destroy();
        if (this.mText != null) {
            this.mText.release();
            this.mText = null;
        }

        this.deleteTexture();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (null != this.onCompletionListener) {
            this.onCompletionListener.onCompletion((IMediaPlayer)null);
        }

    }

    public boolean onError(MediaPlayer mediaPlayer, int frameworkErr, int implErr) {
        return this.onErrorListener != null && this.onErrorListener.onError(mediaPlayer, frameworkErr, implErr);
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    public void onPrepared(MediaPlayer mp) {
        this.isPrepared = true;
        this.inputWidth = mp.getVideoWidth();
        this.inputHeight = mp.getVideoHeight();
        Log.e("zk", "openPublishHelp, <onPrepared> cost time:" + (System.currentTimeMillis() - this.start_time) + "ms height" + this.inputWidth + "height" + this.inputHeight);
        if (this.mText == null) {
            this.getScreenTexture();
        }

        this.mText.setDefaultBufferSize(this.inputWidth, this.inputHeight);
        this.mText.setOnFrameAvailableListener(this);
        mp.start();
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
        int new_width = mediaPlayer.getVideoWidth();
        int new_height = mediaPlayer.getVideoHeight();
        this.inputWidth = new_width;
        this.inputHeight = new_height;
        if (null != this.mText) {
            this.mText.setDefaultBufferSize(this.inputWidth, this.inputHeight);
        }

        this.setRenderSize(new_width, new_height);
        this.isFistFrame = true;
        if (this.onVideoSizeChangedListener != null) {
            this.onVideoSizeChangedListener.onVideoSizeChanged(this, new_width, new_height, i, i1);
        }

        Log.e("zk", "openPublishHelp onVideoSizeChanged:w=" + this.width + ",h=" + this.height + "," + new_width + "," + new_height);
    }

    public void setOnVideoSizeChangedListener(IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.onVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public void setRenderTimestampListener(RenderTimestampListener renderTimestampListener) {
        this.renderTimestampListener = renderTimestampListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener errorListener) {
        this.onErrorListener = errorListener;
    }

    public int getInputWidth() {
        return this.inputWidth;
    }

    public int getInputHeight() {
        return this.inputHeight;
    }
}
