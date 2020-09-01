package com.nzy.gameover.sticker.extanimation;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Path;
import android.graphics.PathMeasure;

import com.nzy.gameover.sticker.ExtAnimation;
import com.nzy.gameover.sticker.IPathable;
import com.nzy.gameover.sticker.PathPoint;

import java.util.Arrays;
import java.util.List;

public class ExtPathAnimation extends ExtAnimation<IPathable> {
    List<PathKeyframe> mPathKeyframes;
    ExtPathAnimation.PathKeyframe mFirstKeyframe;
    ExtPathAnimation.PathKeyframe mLastKeyframe;
    int mNumKeyframes;
    TypeEvaluator mEvaluator;
    TimeInterpolator mInterpolator;

    private ExtPathAnimation(IPathable target, ExtPathAnimation.PathKeyframe... keyframes) {
        super(target);
        this.mNumKeyframes = keyframes.length;
        this.mFirstKeyframe = keyframes[0];
        this.mLastKeyframe = keyframes[this.mNumKeyframes - 1];
        this.mPathKeyframes = Arrays.asList(keyframes);
        this.mInterpolator = this.mLastKeyframe.getInterpolator();
    }

    public static ExtPathAnimation toPath(IPathable target, TypeEvaluator evaluator, Object... values) {
        int numKeyframes = values.length;
        ExtPathAnimation.PathKeyframe[] pathKeyframes = new ExtPathAnimation.PathKeyframe[Math.max(numKeyframes, 2)];
        if (numKeyframes == 1) {
            pathKeyframes[0] = ExtPathAnimation.PathKeyframe.ofPathObject(0.0F, (PathPoint)null);
            pathKeyframes[1] = ExtPathAnimation.PathKeyframe.ofPathObject(1.0F, (PathPoint)values[0]);
        } else {
            pathKeyframes[0] = ExtPathAnimation.PathKeyframe.ofPathObject(0.0F, (PathPoint)values[0]);
            float pathLength = getPathLength(values);

            for(int i = 1; i < numKeyframes; ++i) {
                float unitLen = getPathLength(values[i - 1], values[i]);
                pathKeyframes[i] = ExtPathAnimation.PathKeyframe.ofPathObject(unitLen / pathLength, (PathPoint)values[i]);
            }
        }

        ExtPathAnimation pathAnimation = new ExtPathAnimation(target, pathKeyframes);
        pathAnimation.setEvaluator(evaluator);
        return pathAnimation;
    }

    private static float getPathLength(Object... values) {
        Path path = new Path();
        PathPoint originPoint = (PathPoint)values[0];
        path.moveTo(originPoint.x, originPoint.y);

        for(int i = 1; i < values.length; ++i) {
            PathPoint value = (PathPoint)values[i];
            switch(value.operation) {
            case 0:
                path.moveTo(value.x, value.y);
                break;
            case 1:
                path.lineTo(value.x, value.y);
                break;
            case 2:
                path.quadTo(value.c0x, value.c0y, value.x, value.y);
                break;
            case 3:
                path.cubicTo(value.c0x, value.c0y, value.c1x, value.c1y, value.x, value.y);
            }
        }

        PathMeasure pathMeasure = new PathMeasure(path, false);
        return pathMeasure.getLength();
    }

    private void setEvaluator(TypeEvaluator evaluator) {
        this.mEvaluator = evaluator;
    }

    protected void onAnimateValue(float fraction) {
        if (this.mNumKeyframes == 2) {
            if (this.mInterpolator != null) {
                fraction = this.mInterpolator.getInterpolation(fraction);
            }

            ((IPathable)this.mTarget).setCenterPoint((PathPoint)this.mEvaluator.evaluate(fraction, this.mFirstKeyframe.getValue(), this.mLastKeyframe.getValue()));
        } else {
            ExtPathAnimation.PathKeyframe prevKeyframe;
            TimeInterpolator interpolator;
            float prevFraction;
            float intervalFraction;
            if (fraction <= 0.0F) {
                prevKeyframe = (ExtPathAnimation.PathKeyframe)this.mPathKeyframes.get(1);
                interpolator = prevKeyframe.getInterpolator();
                if (interpolator != null) {
                    fraction = interpolator.getInterpolation(fraction);
                }

                prevFraction = this.mFirstKeyframe.getFraction();
                intervalFraction = (fraction - prevFraction) / (prevKeyframe.getFraction() - prevFraction);
                ((IPathable)this.mTarget).setCenterPoint((PathPoint)this.mEvaluator.evaluate(intervalFraction, this.mFirstKeyframe.getValue(), prevKeyframe.getValue()));
            } else if (fraction >= 1.0F) {
                prevKeyframe = (ExtPathAnimation.PathKeyframe)this.mPathKeyframes.get(this.mNumKeyframes - 2);
                interpolator = this.mLastKeyframe.getInterpolator();
                if (interpolator != null) {
                    fraction = interpolator.getInterpolation(fraction);
                }

                prevFraction = prevKeyframe.getFraction();
                intervalFraction = (fraction - prevFraction) / (this.mLastKeyframe.getFraction() - prevFraction);
                ((IPathable)this.mTarget).setCenterPoint((PathPoint)this.mEvaluator.evaluate(intervalFraction, prevKeyframe.getValue(), this.mLastKeyframe.getValue()));
            } else {
                prevKeyframe = this.mFirstKeyframe;

                for(int i = 1; i < this.mNumKeyframes; ++i) {
                    ExtPathAnimation.PathKeyframe nextKeyframe = (ExtPathAnimation.PathKeyframe)this.mPathKeyframes.get(i);
                    if (fraction < nextKeyframe.getFraction()) {
                        TimeInterpolator interpolator2 = nextKeyframe.getInterpolator();
                        float prevFraction2 = prevKeyframe.getFraction();
                        float intervalFraction2 = (fraction - prevFraction2) / (nextKeyframe.getFraction() - prevFraction2);
                        if (interpolator2 != null) {
                            prevFraction2 = interpolator2.getInterpolation(prevFraction2);
                        }

                        ((IPathable)this.mTarget).setCenterPoint((PathPoint)this.mEvaluator.evaluate(prevFraction2, prevKeyframe.getValue(), nextKeyframe.getValue()));
                        return;
                    }

                    prevKeyframe = nextKeyframe;
                }

            }
        }
    }

    static class PathKeyframe {
        float fraction;
        PathPoint value;
        TimeInterpolator interpolator;

        PathKeyframe(float fraction, PathPoint value) {
            this.fraction = fraction;
            this.value = value;
            if (value != null) {
                this.interpolator = value.interpolator;
            }

        }

        static ExtPathAnimation.PathKeyframe ofPathObject(float fraction, PathPoint pathPoint) {
            return new ExtPathAnimation.PathKeyframe(fraction, pathPoint);
        }

        public float getFraction() {
            return this.fraction;
        }

        PathPoint getValue() {
            return this.value;
        }

        public TimeInterpolator getInterpolator() {
            return this.interpolator;
        }
    }
}
