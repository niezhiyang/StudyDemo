package com.nzy.gameover.sticker;

import android.graphics.Bitmap;

public class VideoSticker {
    public Element element;
    private float x;
    private float y;
    private float width;
    private float height;
    private String stickerType;
    private long duration;
    private VideoSticker.ImageProvider imageProvider;

    public VideoSticker() {
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getStickerType() {
        return this.stickerType;
    }

    public void setStickerType(String stickerType) {
        this.stickerType = stickerType;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public VideoSticker.ImageProvider getImageProvider() {
        return this.imageProvider;
    }

    public void setImageProvider(VideoSticker.ImageProvider imageProvider) {
        this.imageProvider = imageProvider;
    }

    public interface ImageProvider {
        Bitmap getBitmap();
    }
}
