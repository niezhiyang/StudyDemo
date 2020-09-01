package com.nzy.gameover.sticker.inter;

import android.graphics.PointF;
import android.view.animation.Interpolator;

public class EaseCubicInterpolator implements Interpolator {
    private static final int ACCURACY = 4096;
    private int mLastI = 0;
    private final PointF mControlPoint1 = new PointF();
    private final PointF mControlPoint2 = new PointF();

    public EaseCubicInterpolator(float x1, float y1, float x2, float y2) {
        this.mControlPoint1.x = x1;
        this.mControlPoint1.y = y1;
        this.mControlPoint2.x = x2;
        this.mControlPoint2.y = y2;
    }

    public float getInterpolation(float input) {
        float t = input;

        for(int i = this.mLastI; i < 4096; ++i) {
            t = 1.0F * (float)i / 4096.0F;
            double x = cubicCurves((double)t, 0.0D, (double)this.mControlPoint1.x, (double)this.mControlPoint2.x, 1.0D);
            if (x >= (double)input) {
                this.mLastI = i;
                break;
            }
        }

        double value = cubicCurves((double)t, 0.0D, (double)this.mControlPoint1.y, (double)this.mControlPoint2.y, 1.0D);
        if (value > 0.999D) {
            value = 1.0D;
            this.mLastI = 0;
        }

        return (float)value;
    }

    public static double cubicCurves(double t, double value0, double value1, double value2, double value3) {
        double u = 1.0D - t;
        double tt = t * t;
        double uu = u * u;
        double uuu = uu * u;
        double ttt = tt * t;
        double value = uuu * value0;
        value += 3.0D * uu * t * value1;
        value += 3.0D * u * tt * value2;
        value += ttt * value3;
        return value;
    }
}
