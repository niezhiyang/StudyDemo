package com.nzy.gameover.sticker;

import android.animation.TimeInterpolator;

public class PathPointFactory {
    public PathPointFactory() {
    }

    public static PathPoint movePoint(float x, float y) {
        PathPoint pathPoint = new PathPoint(0, x, y);
        pathPoint.operation = 0;
        return pathPoint;
    }

    public static PathPoint movePoint(float x, float y, TimeInterpolator interpolator) {
        PathPoint pathPoint = movePoint(x, y);
        pathPoint.setInterpolator(interpolator);
        return pathPoint;
    }

    public static PathPoint linePoint(float x, float y, TimeInterpolator interpolator) {
        PathPoint pathPoint = new PathPoint(0, x, y);
        pathPoint.operation = 1;
        pathPoint.setInterpolator(interpolator);
        return pathPoint;
    }

    public static PathPoint lineQuadPoint(float c0x, float c0y, float x, float y, TimeInterpolator interpolator) {
        PathPoint pathPoint = new PathPoint(c0x, c0y, x, y);
        pathPoint.operation = 2;
        pathPoint.setInterpolator(interpolator);
        return pathPoint;
    }

    public static PathPoint lineCubicPoint(float c0x, float c0y, float c1x, float c1y, float x, float y, TimeInterpolator interpolator) {
        PathPoint pathPoint = new PathPoint(c0x, c0y, c1x, c1y, x, y);
        pathPoint.operation = 3;
        pathPoint.setInterpolator(interpolator);
        return pathPoint;
    }
}
