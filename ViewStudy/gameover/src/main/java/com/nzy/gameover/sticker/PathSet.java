package com.nzy.gameover.sticker;

import android.animation.TimeInterpolator;

import java.util.ArrayList;
import java.util.List;

public class PathSet {
    private List<PathPoint> pathPoints = new ArrayList();

    public PathSet() {
    }

    public List<PathPoint> getPathPoints() {
        return this.pathPoints;
    }

    public void moveTo(float x, float y, TimeInterpolator interpolator) {
        this.pathPoints.add(PathPointFactory.movePoint(x, y, interpolator));
    }

    public void lineTo(float x, float y, TimeInterpolator interpolator) {
        this.pathPoints.add(PathPointFactory.linePoint(x, y, interpolator));
    }

    public void quadBezierTo(float c0x, float c0y, float x, float y, TimeInterpolator interpolator) {
        this.pathPoints.add(PathPointFactory.lineQuadPoint(c0x, c0y, x, y, interpolator));
    }

    public void cubicBezierTo(float c0x, float c0y, float c1x, float c1y, float x, float y, TimeInterpolator interpolator) {
        this.pathPoints.add(PathPointFactory.lineCubicPoint(c0x, c0y, c1x, c1y, x, y, interpolator));
    }
}
