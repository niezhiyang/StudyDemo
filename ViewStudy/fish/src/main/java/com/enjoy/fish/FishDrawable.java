package com.enjoy.fish;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishDrawable extends Drawable {

    private Path mPath;
    private Paint mPaint;

    private int OTHER_ALPHA = 110;
    private int BODY_ALPHA = 160;

    // 鱼的重心
    private PointF middlePoint;
    // 鱼的主要朝向角度
    private float fishMainAngle = 90;

    /**
     * 鱼的长度值
     */
    // 绘制鱼头的半径
    private float HEAD_RADIUS = 50;
    // 鱼身长度
    private float BODY_LENGTH = HEAD_RADIUS * 3.2f;
    // 寻找鱼鳍起始点坐标的线长
    private float FIND_FINS_LENGTH = 0.9f * HEAD_RADIUS;
    // 鱼鳍的长度
    private float FINS_LENGTH = 1.3f * HEAD_RADIUS;
    // 大圆的半径
    private float BIG_CIRCLE_RADIUS = 0.7f * HEAD_RADIUS;
    // 中圆的半径
    private float MIDDLE_CIRCLE_RADIUS = 0.6f * BIG_CIRCLE_RADIUS;
    // 小圆半径
    private float SMALL_CIRCLE_RADIUS = 0.4f * MIDDLE_CIRCLE_RADIUS;
    // --寻找尾部中圆圆心的线长
    private final float FIND_MIDDLE_CIRCLE_LENGTH = BIG_CIRCLE_RADIUS * (0.6f + 1);
    // --寻找尾部小圆圆心的线长
    private final float FIND_SMALL_CIRCLE_LENGTH = MIDDLE_CIRCLE_RADIUS * (0.4f + 2.7f);
    // --寻找大三角形底边中心点的线长
    private final float FIND_TRIANGLE_LENGTH = MIDDLE_CIRCLE_RADIUS * 2.7f;


    float currentValue = 0;
    private PointF headPoint;

    public FishDrawable() {
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setARGB(OTHER_ALPHA, 244, 92, 71);

        middlePoint = new PointF(4.19f * HEAD_RADIUS, 4.19f * HEAD_RADIUS);

        // 1.2  1.5 ==》 10
        // 1.2*n = 整数 1.5 *n = 整数 ==》 公倍数
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 3600f);
        // 动画周期
        valueAnimator.setDuration(15 * 1000);
        // 重复的模式：重新开始
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        // 重复的次数
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // 插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                currentValue = (float) animator.getAnimatedValue();

                invalidateSelf();
            }
        });
        valueAnimator.start();
    }

    private float frequence = 1f;

    @Override
    public void draw(@NonNull Canvas canvas) {
        // sin(currentValue*2) ==> 0-3600 1s, 10次，20次
        float fishAngle = (float) (fishMainAngle + Math.sin(Math.toRadians(currentValue * 1.2 * frequence)) * 10);

        // 鱼头的圆心坐标
        headPoint = calculatePoint(middlePoint, BODY_LENGTH / 2, fishAngle);
        canvas.drawCircle(headPoint.x, headPoint.y, HEAD_RADIUS, mPaint);

        // 画右鱼鳍
        PointF rightFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle - 110);
        makeFins(canvas, rightFinsPoint, fishAngle, true);

        // 画左鱼鳍
        PointF leftFinsPoint = calculatePoint(headPoint, FIND_FINS_LENGTH, fishAngle + 110);
        makeFins(canvas, leftFinsPoint, fishAngle, false);

        PointF bodyBottomCenterPoint = calculatePoint(headPoint, BODY_LENGTH, fishAngle - 180);
        // 画节肢1
        PointF middleCenterPoint = makeSegment(canvas, bodyBottomCenterPoint, BIG_CIRCLE_RADIUS, MIDDLE_CIRCLE_RADIUS,
                FIND_MIDDLE_CIRCLE_LENGTH, fishAngle, true);
        // 画节肢2
        makeSegment(canvas, middleCenterPoint, MIDDLE_CIRCLE_RADIUS, SMALL_CIRCLE_RADIUS,
                FIND_SMALL_CIRCLE_LENGTH, fishAngle, false);

        float findEdgeLength = (float) Math.abs(Math.sin(Math.toRadians(currentValue * 1.5 * frequence)) * BIG_CIRCLE_RADIUS);

        // 尾巴
        makeTriangel(canvas, middleCenterPoint, FIND_TRIANGLE_LENGTH, findEdgeLength, fishAngle);
        makeTriangel(canvas, middleCenterPoint, FIND_TRIANGLE_LENGTH - 10,
                findEdgeLength - 20, fishAngle);

        // 身体
        makeBody(canvas, headPoint, bodyBottomCenterPoint, fishAngle);
    }

    private void makeBody(Canvas canvas, PointF headPoint, PointF bodyBottomCenterPoint, float fishAngle) {
        // 身体的四个点求出来
        PointF topLeftPoint = calculatePoint(headPoint, HEAD_RADIUS, fishAngle + 90);
        PointF topRightPoint = calculatePoint(headPoint, HEAD_RADIUS, fishAngle - 90);
        PointF bottomLeftPoint = calculatePoint(bodyBottomCenterPoint, BIG_CIRCLE_RADIUS,
                fishAngle + 90);
        PointF bottomRightPoint = calculatePoint(bodyBottomCenterPoint, BIG_CIRCLE_RADIUS,
                fishAngle - 90);

        // 二阶贝塞尔曲线的控制点 --- 决定鱼的胖瘦
        PointF controlLeft = calculatePoint(headPoint, BODY_LENGTH * 0.56f,
                fishAngle + 130);
        PointF controlRight = calculatePoint(headPoint, BODY_LENGTH * 0.56f,
                fishAngle - 130);

        // 绘制
        mPath.reset();
        mPath.moveTo(topLeftPoint.x, topLeftPoint.y);
        mPath.quadTo(controlLeft.x, controlLeft.y, bottomLeftPoint.x, bottomLeftPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        mPath.quadTo(controlRight.x, controlRight.y, topRightPoint.x, topRightPoint.y);
        mPaint.setAlpha(BODY_ALPHA);
        canvas.drawPath(mPath, mPaint);
    }

    private void makeTriangel(Canvas canvas, PointF startPoint, float findCenterLength,
                              float findEdgeLength, float fishAngle) {

        float triangelAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * 1.5 * frequence)) * 35);

        // 三角形底边的中心坐标
        PointF centerPoint = calculatePoint(startPoint, findCenterLength, triangelAngle - 180);
        // 三角形底边两点
        PointF leftPoint = calculatePoint(centerPoint, findEdgeLength, triangelAngle + 90);
        PointF rightPoint = calculatePoint(centerPoint, findEdgeLength, triangelAngle - 90);

        mPath.reset();
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.lineTo(leftPoint.x, leftPoint.y);
        mPath.lineTo(rightPoint.x, rightPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    private PointF makeSegment(Canvas canvas, PointF bottomCenterPoint, float bigRadius, float smallRadius,
                               float findSmallCircleLength, float fishAngle, boolean hasBigCircle) {


        float segmentAngle;
        if (hasBigCircle) {
            // 节肢1
            segmentAngle = (float) (fishAngle + Math.cos(Math.toRadians(currentValue * 1.5 * frequence)) * 15);
        } else {
            // 节肢2
            segmentAngle = (float) (fishAngle + Math.sin(Math.toRadians(currentValue * 1.5 * frequence)) * 35);
        }


        // 梯形上底圆的圆心
        PointF upperCenterPoint = calculatePoint(bottomCenterPoint, findSmallCircleLength,
                segmentAngle - 180);
        // 梯形的四个点
        PointF bottomLeftPoint = calculatePoint(bottomCenterPoint, bigRadius, segmentAngle + 90);
        PointF bottomRightPoint = calculatePoint(bottomCenterPoint, bigRadius, segmentAngle - 90);
        PointF upperLeftPoint = calculatePoint(upperCenterPoint, smallRadius, segmentAngle + 90);
        PointF upperRightPoint = calculatePoint(upperCenterPoint, smallRadius, segmentAngle - 90);

        if (hasBigCircle) {
            // 画大圆 --- 只在节肢1 上才绘画
            canvas.drawCircle(bottomCenterPoint.x, bottomCenterPoint.y, bigRadius, mPaint);
        }
        // 画小圆
        canvas.drawCircle(upperCenterPoint.x, upperCenterPoint.y, smallRadius, mPaint);

        // 画梯形
        mPath.reset();
        mPath.moveTo(upperLeftPoint.x, upperLeftPoint.y);
        mPath.lineTo(upperRightPoint.x, upperRightPoint.y);
        mPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
        mPath.lineTo(bottomLeftPoint.x, bottomLeftPoint.y);
        canvas.drawPath(mPath, mPaint);

        return upperCenterPoint;
    }

    /**
     * 画鱼鳍
     *
     * @param startPoint 起始坐标
     * @param isRight    是否是右鱼鳍
     */
    private void makeFins(Canvas canvas, PointF startPoint, float fishAngle, boolean isRight) {
        float controlAngle = 115;

        // 鱼鳍的终点 --- 二阶贝塞尔曲线的终点
        PointF endPoint = calculatePoint(startPoint, FINS_LENGTH, fishAngle - 180);
        // 控制点
        PointF controlPoint = calculatePoint(startPoint, FINS_LENGTH * 1.8f,
                isRight ? fishAngle - controlAngle : fishAngle + controlAngle);
        // 绘制
        mPath.reset();
        // 将画笔移动到起始点
        mPath.moveTo(startPoint.x, startPoint.y);
        // 二阶贝塞尔曲线
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * @param startPoint 起始点坐标
     * @param length     要求的点到起始点的直线距离 -- 线长
     * @param angle      鱼当前的朝向角度
     * @return
     */
    public PointF calculatePoint(PointF startPoint, float length, float angle) {
        // x坐标
        float deltaX = (float) (Math.cos(Math.toRadians(angle)) * length);
        // y坐标
        float deltaY = (float) (Math.sin(Math.toRadians(angle - 180)) * length);

        return new PointF(startPoint.x + deltaX, startPoint.y + deltaY);
    }


    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    public PointF getMiddlePoint() {
        return middlePoint;
    }

    public PointF getHeadPoint() {
        return headPoint;
    }

    public float getHEAD_RADIUS() {
        return HEAD_RADIUS;
    }

    public float getFrequence() {
        return frequence;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }

    public float getFishMainAngle() {
        return fishMainAngle;
    }

    public void setFishMainAngle(float fishMainAngle) {
        this.fishMainAngle = fishMainAngle;
    }
}
