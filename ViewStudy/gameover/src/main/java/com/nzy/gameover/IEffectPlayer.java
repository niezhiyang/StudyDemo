package com.nzy.gameover;

import android.content.res.AssetFileDescriptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author niezhiyang
 * since 2020/8/29
 */
public interface IEffectPlayer {
    int MERGE_ALPHA = 1;
    int GREEN_SCREEN_KEYING = 2;

    void setRenderSize(int var1, int var2);

    void setDataSource(String var1, int var2);

    void setDataSource(AssetFileDescriptor var1, int var2);

    void prepare();

    void startPlay(Object var1);

    void stopPlay();

//    void addStickerItem(StickerGiftItem var1);
//
//    void addStickerElement(VideoSticker var1);

    void setVideoSizeChangedListener(IEffectPlayer.OnVideoSizeChangedListener var1);

    void setOnVideoCompleteListener(IEffectPlayer.OnCompletionListener var1);

    void setRenderPositionChangedListener(IEffectPlayer.RenderPositionChangedListener var1);

    void setOnErrorListener(IEffectPlayer.OnErrorListener var1);

    public interface OnErrorListener {
        boolean onError(IEffectPlayer var1, int var2, int var3);
    }

    public interface OnCompletionListener {
        void onCompletion();
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(int var1, int var2);
    }

    public interface RenderPositionChangedListener {
        void renderPositionChanged(long var1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EffectType {
    }
}
