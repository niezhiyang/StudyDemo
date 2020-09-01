package com.nzy.gameover;

import android.view.animation.Interpolator;

public class OvershootBounceInterpolator implements Interpolator {
    float overshoot = 0.3f;
    float bounce = 2f;
    float friction = 5f;
    float linearProportion = 0.16666f;

    public OvershootBounceInterpolator() {
    }

    public OvershootBounceInterpolator(float overshoot, float bounce, float friction, float linearProportion) {
        this.overshoot = overshoot;
        this.bounce = bounce;
        this.friction = friction;
        this.linearProportion = linearProportion;
    }

    @Override
    public float getInterpolation(float input) {
        float interpolation;
        if (input < linearProportion) {
            // 直线加速
            interpolation = input / linearProportion;
        } else {
            // 衰减正弦
            double value = input - linearProportion;
            double all = 1f - linearProportion;

            interpolation = dampedSineWave(value / all);
        }

        return interpolation;
    }

    protected float dampedSineWave(double x) {
        return  (float) (overshoot * Math.sin(bounce * x * 2 * Math.PI) * Math.exp(-x * friction) + 1f);
    }
}
