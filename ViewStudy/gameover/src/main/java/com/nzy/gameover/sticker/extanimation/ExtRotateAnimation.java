package com.nzy.gameover.sticker.extanimation;

import android.animation.TimeInterpolator;

import com.nzy.gameover.sticker.ExtAnimation;
import com.nzy.gameover.sticker.IRotateable;

public class ExtRotateAnimation extends ExtAnimation<IRotateable> {
    private final float mDegress;
    private final float mToDegress;
    private TimeInterpolator mInterpolator;

    public ExtRotateAnimation(IRotateable target, float degress, float toDegress) {
        super(target);
        this.mDegress = degress;
        this.mToDegress = toDegress;
    }

    public ExtRotateAnimation(IRotateable target, float degress, float toDegress, TimeInterpolator interpolator) {
        super(target);
        this.mDegress = degress;
        this.mToDegress = toDegress;
        this.mInterpolator = interpolator;
    }

    protected void onAnimateValue(float fraction) {
        if (null != this.mTarget) {
            if (this.mInterpolator != null) {
                fraction = this.mInterpolator.getInterpolation(fraction);
            }

            ((IRotateable)this.mTarget).setDegress(this.mDegress + (this.mToDegress - this.mDegress) * fraction);
        }

    }
}
