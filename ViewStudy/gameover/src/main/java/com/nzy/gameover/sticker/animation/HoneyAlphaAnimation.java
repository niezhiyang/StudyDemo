package com.nzy.gameover.sticker.animation;

import android.view.animation.Interpolator;

import com.nzy.gameover.sticker.HoneyAnimationExt;
import com.nzy.gameover.sticker.HoneyChangeValue;

public class HoneyAlphaAnimation extends HoneyAnimationExt {
    public HoneyAlphaAnimation(HoneyChangeValue data, Interpolator interpolator) {
        super(data, interpolator);
    }

    protected void valueChanged(float value) {
        this.data.setAlpha(value);
    }
}