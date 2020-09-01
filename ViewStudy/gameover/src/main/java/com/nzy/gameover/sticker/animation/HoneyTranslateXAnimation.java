package com.nzy.gameover.sticker.animation;

import android.view.animation.Interpolator;

import com.nzy.gameover.sticker.HoneyAnimationExt;
import com.nzy.gameover.sticker.HoneyChangeValue;

public class HoneyTranslateXAnimation extends HoneyAnimationExt {
    public HoneyTranslateXAnimation(HoneyChangeValue data, Interpolator interpolator) {
        super(data, interpolator);
    }

    protected void valueChanged(float value) {
        this.data.setX(value);
    }
}