package com.nzy.gameover.sticker;

import android.animation.TypeEvaluator;

public class PathEvaluator implements TypeEvaluator<PathPoint> {
    PathPoint mPoint;

    public PathEvaluator() {
    }

    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        if (this.mPoint == null || !this.mPoint.equals(startValue)) {
            this.mPoint = startValue;
        }

        float remain = 1.0F - fraction;
        float x;
        float y;
        if (endValue.operation == 3) {
            x = startValue.x * remain * remain * remain + 3.0F * endValue.c0x * fraction * remain * remain + 3.0F * endValue.c1x * fraction * fraction * remain + endValue.x * fraction * fraction * fraction;
            y = startValue.y * remain * remain * remain + 3.0F * endValue.c0y * fraction * remain * remain + 3.0F * endValue.c1y * fraction * fraction * remain + endValue.y * fraction * fraction * fraction;
        } else if (endValue.operation == 2) {
            x = remain * remain * startValue.x + 2.0F * fraction * remain * endValue.c0x + fraction * fraction * endValue.x;
            y = remain * remain * startValue.y + 2.0F * fraction * remain * endValue.c0y + fraction * fraction * endValue.y;
        } else if (endValue.operation == 1) {
            x = startValue.x + fraction * (endValue.x - startValue.x);
            y = startValue.y + fraction * (endValue.y - startValue.y);
        } else {
            x = endValue.x;
            y = endValue.y;
        }

        return PathPointFactory.movePoint(x, y);
    }
}
