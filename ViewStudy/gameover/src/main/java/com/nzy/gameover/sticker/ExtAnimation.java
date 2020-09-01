package com.nzy.gameover.sticker;

import android.animation.TimeInterpolator;

public abstract class ExtAnimation<T extends IAnimationable> implements Cloneable {
    protected static final long NO_START_TIME = -1L;
    protected T mTarget;
    protected long mDuration = 0L;
    protected TimeInterpolator mInterpolator;
    protected long mStartDelay;
    protected long mStartTime = -1L;
    protected boolean mRunning;

    public ExtAnimation(T target) {
        this.mTarget = target;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public ExtAnimation setStartDelay(long startDelay) {
        this.mStartDelay = startDelay;
        return this;
    }

    public ExtAnimation setDuration(long duration) {
        this.mDuration = duration;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getTotalDuration() {
        return this.getStartDelay() + this.getDuration();
    }

    public ExtAnimation setInterpolator(TimeInterpolator value) {
        this.mInterpolator = value;
        return this;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void start() {
        this.mRunning = true;
        this.mStartTime = 0L;
    }

    public void stop() {
        this.mRunning = false;
        this.mStartTime = -1L;
    }

    public boolean animateBasedOnTime(long currentTime) {
        if (!this.mRunning) {
            return false;
        } else {
            if (this.mStartTime == -1L) {
                this.mStartTime = currentTime;
            }

            long playTime = currentTime - this.mStartTime - this.mStartDelay;
            if (playTime <= 0L) {
                return false;
            } else {
                float fraction = this.mDuration > 0L ? (float)playTime / (float)this.mDuration : 1.0F;
                fraction = this.clampFraction(fraction);
                if (null != this.mInterpolator) {
                    fraction = this.mInterpolator.getInterpolation(fraction);
                }

                this.onAnimateValue(fraction);
                if (fraction >= 1.0F) {
                    this.stop();
                }

                return true;
            }
        }
    }

    protected float clampFraction(float fraction) {
        return Math.max(0.0F, Math.min(fraction, 1.0F));
    }

    protected abstract void onAnimateValue(float var1);

    public ExtAnimation clone() {
        try {
            ExtAnimation anim = (ExtAnimation)super.clone();
            return anim;
        } catch (CloneNotSupportedException var2) {
            throw new AssertionError();
        }
    }
}
