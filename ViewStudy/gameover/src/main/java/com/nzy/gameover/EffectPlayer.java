package com.nzy.gameover;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.nzy.gameover.sticker.StickerGiftItem;
import com.nzy.gameover.sticker.VideoSticker;

import java.io.IOException;

/**
 * @author niezhiyang
 * since 2020/8/29
 */
public class EffectPlayer implements IEffectPlayer, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.RenderTimestampListener {
    private EffectProcessingPipeline pipeline;
    private EffectPlayerInput videoInput;
    private GLScreenEndpoint screenEndPoint;
    private BasicFilter basicFilter;
    private VideoStickerMaskFilter videoStickerFilter;
    private AssetFileDescriptor fd;
    private Uri uri;
    private Context context;
    private int effectType;
    private long duration;
    private int renderWidth;
    private int renderHeight;
    private IEffectPlayer.OnCompletionListener completionListener;
    private IEffectPlayer.OnVideoSizeChangedListener videoSizeChangedListener;
    private IEffectPlayer.RenderPositionChangedListener renderPositionListener;
    private IEffectPlayer.OnErrorListener onErrorListener;

    public EffectPlayer(Context context) {
        this.context = context;
    }

    private void initPipeline() {
        this.pipeline = new EffectProcessingPipeline();
        this.pipeline.setAlphaRender(true);
        if (this.uri != null && !TextUtils.isEmpty(this.uri.getPath())) {
            this.videoInput = new EffectPlayerInput(this.context, this.uri.toString());
        } else {
            this.videoInput = new EffectPlayerInput(this.context, this.fd);
        }

        this.pipeline.addRootRenderer(this.videoInput);
        EffectSurfaceRender render = this.pipeline.getRenderByFilter(this.videoInput);
        this.videoInput.setMomoSurfaceRender(render);
        if (this.effectType == 2) {
            this.basicFilter = new GreenKeyingFilter();
        } else {
            this.basicFilter = new VideoAlphaMergeFilter();
        }

        this.screenEndPoint = new GLScreenEndpoint();
        this.videoStickerFilter = new VideoStickerMaskFilter(this.context);
        this.readMediaInfo(this.uri.getPath());
        this.videoStickerFilter.setTotalTime(this.duration);
        this.basicFilter.setRenderSize(this.renderWidth, this.renderHeight);
        this.videoInput.addTarget(this.basicFilter);
        this.basicFilter.addTarget(this.videoStickerFilter);
        this.videoStickerFilter.addTarget(this.screenEndPoint);
        this.screenEndPoint.setRenderSize(this.renderWidth, this.renderHeight);
        this.screenEndPoint.setBackgroundColour(0.0F, 0.0F, 0.0F, 0.0F);
        this.videoInput.setOnVideoSizeChangedListener(this);
        this.videoInput.setOnCompletionListener(this);
        this.videoInput.setOnErrorListener(this);
        this.videoInput.setRenderTimestampListener(this);
        this.videoInput.start();
    }

    private void readMediaInfo(String path) {
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(path);
            String duration = retriever.extractMetadata(9);
            String width = retriever.extractMetadata(18);
            String height = retriever.extractMetadata(19);
            this.duration = Long.parseLong(duration);
            int videoWidth = this.effectType == 1 ? Integer.parseInt(width) / 2 : Integer.parseInt(width);
            int videoHeight = Integer.parseInt(height);
            if (this.renderWidth == 0 && videoWidth > 0) {
                this.renderWidth = videoWidth;
            }

            if (this.renderHeight == 0 && videoHeight > 0) {
                this.renderHeight = videoHeight;
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            this.renderWidth = 720;
            this.renderHeight = 1280;
        }

    }

    public void setRenderSize(int width, int height) {
        this.renderWidth = width;
        this.renderHeight = height;
    }

    public void setDataSource(String path, int effectType) {
        if (TextUtils.isEmpty(path)) {
            Log.e("EffectPlayer", "path must be not null");
        } else {
            this.uri = Uri.parse(path);
            this.effectType = effectType;
        }
    }

    public void setDataSource(AssetFileDescriptor fd, int effectType) {
        this.fd = fd;
        this.effectType = effectType;
    }

    public void prepare() {
        this.initPipeline();
    }

    public void startPlay(Object surface) {
        this.pipeline.startRendering(surface);
    }

    public void stopPlay() {
        if (this.videoInput != null && this.pipeline != null) {
            this.pipeline.addFilterToDestroy(this.basicFilter, this.videoInput.toString());
            this.pipeline.addFilterToDestroy(this.videoStickerFilter, this.videoInput.toString());
            this.pipeline.addFilterToDestroy(this.screenEndPoint, this.videoInput.toString());
            this.pipeline.finishRender();
            this.videoInput.stop();
        }

        if (this.fd != null) {
            try {
                this.fd.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public void addStickerItem(StickerGiftItem item) {
        if (this.videoStickerFilter != null) {
            this.videoStickerFilter.addStickerWithDefaultAnimation(item);
        }

    }

    public void addStickerElement(VideoSticker sticker) {
        if (this.videoStickerFilter != null) {
            this.videoStickerFilter.addSticker(sticker);
        }

    }

    private void onFinish() {
        if (this.pipeline != null) {
            this.pipeline.addFilterToDestroy(this.basicFilter, this.videoInput.toString());
            this.pipeline.addFilterToDestroy(this.videoStickerFilter, this.videoInput.toString());
            this.pipeline.addFilterToDestroy(this.screenEndPoint, this.videoInput.toString());
            this.pipeline.finishRender();
            this.videoInput.stop();
//            this.videoInput.setOnVideoSizeChangedListener((OnVideoSizeChangedListener)null);
//            this.videoInput.setOnCompletionListener((OnCompletionListener)null);
//            this.videoInput.setOnErrorListener((OnErrorListener)null);
//            this.videoInput.setRenderTimestampListener((RenderTimestampListener)null);
        }

        if (this.fd != null) {
            try {
                this.fd.close();
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    public void onCompletion(IMediaPlayer mp) {
        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            public void run() {
                EffectPlayer.this.onFinish();
                if (EffectPlayer.this.completionListener != null) {
                    EffectPlayer.this.completionListener.onCompletion();
                }

            }
        }, 100L);
    }

    public boolean onError(MediaPlayer mediaPlayer, int frameworkErr, int implErr) {
        return this.onErrorListener != null && this.onErrorListener.onError(this, frameworkErr, implErr);
    }

    public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
        if (this.videoSizeChangedListener != null) {
            if (this.effectType == 1) {
                width /= 2;
            }

            this.videoSizeChangedListener.onVideoSizeChanged(width, height);
        }

    }

    public void onRenderTimestampChanged(long timestamp) {
        Log.i("EffectPlayer", "pos : " + timestamp);
        if (this.renderPositionListener != null) {
            this.renderPositionListener.renderPositionChanged(timestamp);
        }

        if (this.videoStickerFilter != null) {
            this.videoStickerFilter.setTimeStamp(timestamp);
        }

    }

    public void setVideoSizeChangedListener(IEffectPlayer.OnVideoSizeChangedListener listener) {
        this.videoSizeChangedListener = listener;
    }

    public void setOnVideoCompleteListener(IEffectPlayer.OnCompletionListener listener) {
        this.completionListener = listener;
    }

    public void setRenderPositionChangedListener(RenderPositionChangedListener listener) {
        this.renderPositionListener = listener;
    }

    public void setOnErrorListener(IEffectPlayer.OnErrorListener listener) {
        this.onErrorListener = listener;
    }
}
