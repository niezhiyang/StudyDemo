package com.nzy.gameover.sticker.animation;

import android.view.animation.Interpolator;

import com.nzy.gameover.sticker.HoneyAnimationExt;
import com.nzy.gameover.sticker.HoneyChangeValue;

public class HoneyTranslateYAnimation extends HoneyAnimationExt {
    public HoneyTranslateYAnimation(HoneyChangeValue data, Interpolator interpolator) {
        super(data, interpolator);
    }

    protected void valueChanged(float value) {
        this.data.setY(value);
    }
}