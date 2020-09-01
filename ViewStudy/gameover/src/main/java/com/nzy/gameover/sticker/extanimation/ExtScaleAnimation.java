package com.nzy.gameover.sticker.extanimation;

import android.animation.TimeInterpolator;

import com.nzy.gameover.sticker.ExtAnimation;
import com.nzy.gameover.sticker.IScaleable;

public class ExtScaleAnimation extends ExtAnimation<IScaleable> {
    private final float mScale;
    private final float mToScale;
    private TimeInterpolator mInterpolator;

    public ExtScaleAnimation(IScaleable target, float scale, float toScale) {
        super(target);
        this.mScale = scale;
        this.mToScale = toScale;
    }

    public ExtScaleAnimation(IScaleable target, float scale, float toScale, TimeInterpolator interpolator) {
        super(target);
        this.mScale = scale;
        this.mToScale = toScale;
        this.mInterpolator = interpolator;
    }

    protected void onAnimateValue(float fraction) {
        if (null != this.mTarget) {
            if (this.mInterpolator != null) {
                fraction = this.mInterpolator.getInterpolation(fraction);
            }

            float scale = this.mScale + (this.mToScale - this.mScale) * fraction;
            ((IScaleable)this.mTarget).setScale(scale);
        }

    }
}
