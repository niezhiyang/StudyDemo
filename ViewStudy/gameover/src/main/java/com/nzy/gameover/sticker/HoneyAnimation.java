package com.nzy.gameover.sticker;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class HoneyAnimation {
    protected HoneyChangeValue data;
    private Interpolator interpolator;
    private int duration;
    protected LinkedHashMap<Integer, Float> map;

    public HoneyAnimation(HoneyChangeValue data, Interpolator interpolator) {
        this.map = new LinkedHashMap();
        this.data = data;
        this.interpolator = interpolator;
    }

    public void setData(HoneyChangeValue data) {
        this.data = data;
    }

    public HoneyAnimation(HoneyChangeValue data) {
        this(data, new LinearInterpolator());
    }

    public HoneyAnimation() {
        this((HoneyChangeValue)null, new LinearInterpolator());
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addKeyFrame(int key, float value) {
        this.map.put(key, value);
    }

    public void setValue(float progress) {
        if (this.data != null) {
            int frontKey = -1;
            int behindKey = -1;
            float frontValue = -1.0F;
            float behindValue = -1.0F;
            ArrayList<Integer> keys = new ArrayList(this.map.keySet());

            for(int i = 0; i < this.map.size(); ++i) {
                if ((float)(Integer)keys.get(i) <= progress * (float)this.duration) {
                    frontKey = (Integer)keys.get(i);
                    frontValue = (Float)this.map.get(frontKey);
                }

                if ((float)(Integer)keys.get(i) > progress * (float)this.duration) {
                    behindKey = (Integer)keys.get(i);
                    behindValue = (Float)this.map.get(behindKey);
                    break;
                }
            }

            float spanProgress = 0.0F;
            float value = 0.0F;
            if (frontKey >= 0 && behindKey >= 0) {
                spanProgress = (progress * (float)this.duration - (float)frontKey) / (float)(behindKey - frontKey);
                Interpolator interpolator = this.getInterpolator(frontKey);
                if (interpolator != null) {
                    spanProgress = interpolator.getInterpolation(spanProgress);
                }

                value = (behindValue - frontValue) * spanProgress + frontValue;
            } else if (frontKey >= 0 || behindKey < 0) {
                if (frontKey >= 0 && behindKey < 0) {
                    value = frontValue;
                } else if (frontKey < 0 && behindKey < 0) {
                }
            }

            this.valueChanged(value);
        }
    }

    protected Interpolator getInterpolator(int key) {
        return this.interpolator;
    }

    protected abstract void valueChanged(float var1);
}
