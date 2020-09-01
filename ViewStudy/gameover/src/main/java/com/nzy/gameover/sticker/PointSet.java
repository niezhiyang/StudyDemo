package com.nzy.gameover.sticker;

import java.util.List;

public class PointSet {
    private List<Float> point;
    private String interpolator;

    public PointSet() {
    }

    public List<Float> getPoint() {
        return this.point;
    }

    public void setPoint(List<Float> point) {
        this.point = point;
    }

    public String getInterpolator() {
        return this.interpolator;
    }

    public void setInterpolator(String interpolator) {
        this.interpolator = interpolator;
    }
}
