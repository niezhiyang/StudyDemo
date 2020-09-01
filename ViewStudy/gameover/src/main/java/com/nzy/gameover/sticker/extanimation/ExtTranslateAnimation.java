package com.nzy.gameover.sticker.extanimation;

import android.animation.TimeInterpolator;
import android.graphics.PointF;

import com.nzy.gameover.sticker.ExtAnimation;
import com.nzy.gameover.sticker.ITranslateable;

public class ExtTranslateAnimation extends ExtAnimation<ITranslateable> {
    private final PointF firstPoint;
    private final PointF lastPoint;
    private TimeInterpolator mInterpolator;

    public ExtTranslateAnimation(ITranslateable target, float x, float y, float toX, float toY) {
        super(target);
        this.firstPoint = new PointF(x, y);
        this.lastPoint = new PointF(toX, toY);
    }

    public ExtTranslateAnimation(ITranslateable target, float x, float y, float toX, float toY, TimeInterpolator interpolator) {
        super(target);
        this.firstPoint = new PointF(x, y);
        this.lastPoint = new PointF(toX, toY);
        this.mInterpolator = interpolator;
    }

    protected void onAnimateValue(float fraction) {
        if (null != this.mTarget) {
            if (this.mInterpolator != null) {
                fraction = this.mInterpolator.getInterpolation(fraction);
            }

            float x = this.firstPoint.x + (this.lastPoint.x - this.firstPoint.x) * fraction;
            float y = this.firstPoint.y + (this.lastPoint.y - this.firstPoint.y) * fraction;
            ((ITranslateable)this.mTarget).setX(x);
            ((ITranslateable)this.mTarget).setY(y);
        }

    }
}
