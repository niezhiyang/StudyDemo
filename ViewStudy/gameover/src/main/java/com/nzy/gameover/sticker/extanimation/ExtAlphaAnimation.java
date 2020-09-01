package com.nzy.gameover.sticker.extanimation;

import android.animation.TimeInterpolator;

import com.nzy.gameover.sticker.ExtAnimation;
import com.nzy.gameover.sticker.IAlphaable;

public class ExtAlphaAnimation extends ExtAnimation<IAlphaable> {
    float mAlpha;
    float mToAlpha;
    TimeInterpolator mInterpolator;

    public ExtAlphaAnimation(IAlphaable target, float alpha, float toAlpha) {
        super(target);
        this.mTarget = target;
        this.mAlpha = alpha;
        this.mToAlpha = toAlpha;
    }

    public ExtAlphaAnimation(IAlphaable target, float alpha, float toAlpha, TimeInterpolator interpolator) {
        super(target);
        this.mAlpha = alpha;
        this.mToAlpha = toAlpha;
        this.mInterpolator = interpolator;
    }

    protected void onAnimateValue(float fraction) {
        if (null != this.mTarget) {
            if (this.mInterpolator != null) {
                fraction = this.mInterpolator.getInterpolation(fraction);
            }

            ((IAlphaable)this.mTarget).setAlpha(this.mAlpha + (this.mToAlpha - this.mAlpha) * fraction);
        }

    }
}
