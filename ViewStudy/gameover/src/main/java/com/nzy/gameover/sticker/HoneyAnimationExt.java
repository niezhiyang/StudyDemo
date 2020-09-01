package com.nzy.gameover.sticker;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.LinkedHashMap;

public abstract class HoneyAnimationExt extends HoneyAnimation {
    private LinkedHashMap<Integer, Interpolator> mapInterpolator;

    public HoneyAnimationExt(HoneyChangeValue data, Interpolator interpolator) {
        super(data, interpolator);
        this.mapInterpolator = new LinkedHashMap();
    }

    public HoneyAnimationExt(HoneyChangeValue data) {
        this(data, new LinearInterpolator());
    }

    public HoneyAnimationExt() {
        this((HoneyChangeValue)null, new LinearInterpolator());
    }

    public void addKeyFrame(int key, float value, Interpolator interpolator) {
        this.addKeyFrame(key, value);
        if (null != interpolator) {
            this.mapInterpolator.put(key, interpolator);
        }

    }

    protected Interpolator getInterpolator(int key) {
        return this.mapInterpolator.containsKey(key) ? (Interpolator)this.mapInterpolator.get(key) : super.getInterpolator(key);
    }
}
