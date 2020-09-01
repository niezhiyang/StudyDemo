package com.nzy.gameover.sticker;

import android.animation.TimeInterpolator;

public class PathPoint {
    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int QUAD_CURVE = 2;
    public static final int CUBIC_CURVE = 3;
    public float x;
    public float y;
    public float c0x;
    public float c0y;
    public float c1x;
    public float c1y;
    public int operation;
    public TimeInterpolator interpolator;

    PathPoint(int operation, float x, float y) {
        this.x = x;
        this.y = y;
        this.operation = operation;
    }

    PathPoint(float c0x, float c0y, float x, float y) {
        this.x = x;
        this.y = y;
        this.c0x = c0x;
        this.c0y = c0y;
        this.operation = 2;
    }

    PathPoint(float c0x, float c0y, float c1x, float c1y, float x, float y) {
        this.x = x;
        this.y = y;
        this.c0x = c0x;
        this.c0y = c0y;
        this.c1x = c1x;
        this.c1y = c1y;
        this.operation = 3;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public String toString() {
        return "PathPoint{x=" + this.x + ", y=" + this.y + ", c0x=" + this.c0x + ", c0y=" + this.c0y + ", c1x=" + this.c1x + ", c1y=" + this.c1y + ", operation=" + this.operation + '}';
    }
}
