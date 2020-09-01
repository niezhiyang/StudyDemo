package com.nzy.gameover.sticker;

import java.util.List;

public class ElementNewAnimation {
    private String type;
    private long startDelay;
    private long duration;
    private float x;
    private float y;
    private float scale;
    private float alpha;
    private float degress;
    private float toX;
    private float toY;
    private float toScale;
    private float toAlpha;
    private float toDegress;
    private List<PointSet> points;
    private String interpolator;

    public ElementNewAnimation() {
    }

    public String getType() {
        return this.type;
    }

    public ElementNewAnimation setType(String type) {
        this.type = type;
        return this;
    }

    public long getStartDelay() {
        return this.startDelay;
    }

    public ElementNewAnimation setStartDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public long getDuration() {
        return this.duration;
    }

    public ElementNewAnimation setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public float getX() {
        return this.x;
    }

    public ElementNewAnimation setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return this.y;
    }

    public ElementNewAnimation setY(float y) {
        this.y = y;
        return this;
    }

    public float getScale() {
        return this.scale;
    }

    public ElementNewAnimation setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public ElementNewAnimation setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public float getToX() {
        return this.toX;
    }

    public ElementNewAnimation setToX(float toX) {
        this.toX = toX;
        return this;
    }

    public float getToY() {
        return this.toY;
    }

    public ElementNewAnimation setToY(float toY) {
        this.toY = toY;
        return this;
    }

    public float getToScale() {
        return this.toScale;
    }

    public ElementNewAnimation setToScale(float toScale) {
        this.toScale = toScale;
        return this;
    }

    public float getToAlpha() {
        return this.toAlpha;
    }

    public ElementNewAnimation setToAlpha(float toAlpha) {
        this.toAlpha = toAlpha;
        return this;
    }

    public List<PointSet> getPoints() {
        return this.points;
    }

    public void setPoints(List<PointSet> points) {
        this.points = points;
    }

    public String getInterpolator() {
        return this.interpolator;
    }

    public void setInterpolator(String interpolator) {
        this.interpolator = interpolator;
    }

    public float getDegress() {
        return this.degress;
    }

    public void setDegress(float degress) {
        this.degress = degress;
    }

    public float getToDegress() {
        return this.toDegress;
    }

    public void setToDegress(float toDegress) {
        this.toDegress = toDegress;
    }
}
