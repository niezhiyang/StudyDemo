package com.nzy.gameover;

import android.media.MediaPlayer;

public interface IMediaPlayer {
    public interface OnErrorListener {
        boolean onError(MediaPlayer var1, int var2, int var3);
    }

    public interface RenderTimestampListener {
        void onRenderTimestampChanged(long var1);
    }

    public interface OnVideoSizeChangedListener {
        void onVideoSizeChanged(IMediaPlayer var1, int var2, int var3, int var4, int var5);
    }

    public interface OnCompletionListener {
        void onCompletion(IMediaPlayer var1);
    }
}