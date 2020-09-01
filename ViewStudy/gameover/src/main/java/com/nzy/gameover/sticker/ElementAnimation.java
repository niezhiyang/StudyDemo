package com.nzy.gameover.sticker;

public class ElementAnimation {
    private int time;
    private float x;
    private float y;
    private float scale;
    private float alpha;
    private String interpolator;

    public ElementAnimation() {
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public String getInterpolator() {
        return this.interpolator;
    }

    public ElementAnimation setInterpolator(String interpolator) {
        this.interpolator = interpolator;
        return this;
    }
}
