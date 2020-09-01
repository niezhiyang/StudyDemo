package com.nzy.gameover.sticker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.text.TextUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.nzy.gameover.sticker.animation.HoneyAlphaAnimation;
import com.nzy.gameover.sticker.animation.HoneyScaleAnimation;
import com.nzy.gameover.sticker.animation.HoneyTranslateXAnimation;
import com.nzy.gameover.sticker.animation.HoneyTranslateYAnimation;
import com.nzy.gameover.sticker.extanimation.ExtAlphaAnimation;
import com.nzy.gameover.sticker.extanimation.ExtPathAnimation;
import com.nzy.gameover.sticker.extanimation.ExtRotateAnimation;
import com.nzy.gameover.sticker.extanimation.ExtScaleAnimation;
import com.nzy.gameover.sticker.extanimation.ExtTranslateAnimation;
import com.nzy.gameover.sticker.inter.EaseCubicInterpolator;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StickerGiftItem implements HoneyChangeValue, IAlphaable, ITranslateable, IScaleable, IRotateable, IPathable {
    public VideoSticker sticker;
    public float stickerScale;
    public FloatBuffer vertexBuffer;
    public float[] texData0;
    public final float[] mViewMatrix = new float[16];
    protected float[] mProjectionMatrix;
    public float imageWidth = 0.0F;
    public float imageHeight = 0.0F;
    public long mStickerDuration = -1L;
    public Object maskLock = new Object();
    protected Context context;
    long mRenderTime = -1L;
    public FloatBuffer fvertexBuffer;
    public FloatBuffer vertexBuffer2;
    public List<float[]> mvpLists = Collections.synchronizedList(new ArrayList());
    public boolean isDraw = false;
    public int texture_sticker = 0;
    private long startTime = -1L;
    private Bitmap curBitmap;
    public boolean useCircle;
    private HoneyAnimationSet animationSet;
    private ExtAnimationSet animationNewSet;
    public StickerGiftItem.Params params = new StickerGiftItem.Params();
    private float elementWidth;
    private float elementHeight;
    private StickerGiftItem.RenderFinishListener finishListener;

    public StickerGiftItem(VideoSticker inSticker, Context context) {
        this.sticker = inSticker;
        this.context = context;
        this.initCoordinate();
    }

    public StickerGiftItem(VideoSticker inSticker, Context context, Element element) {
        this.sticker = inSticker;
        this.context = context;
        this.elementWidth = element.getWidth();
        this.elementHeight = element.getHeight();
        this.initCoordinate();
        if (element.getNew_anim() != null) {
            this.initNewAnimation(element);
        } else {
            this.initAnimation(element);
        }

    }

    protected void initAnimation(Element element) {
        float scaleRatio = element.getWidth() / 0.5F;
        List<ElementAnimation> anim = element.getAnim();
        if (anim != null && anim.size() > 0) {
            this.animationSet = new HoneyAnimationSet();
            HoneyAnimationExt xAnimation = new HoneyTranslateXAnimation(this, new LinearInterpolator());
            xAnimation.setDuration(element.getDuration());
            HoneyAnimationExt yAnimation = new HoneyTranslateYAnimation(this, new LinearInterpolator());
            yAnimation.setDuration(element.getDuration());
            HoneyAnimationExt scaleAnimation = new HoneyScaleAnimation(this, new LinearInterpolator());
            scaleAnimation.setDuration(element.getDuration());
            HoneyAnimationExt alphaAnimation = new HoneyAlphaAnimation(this, new LinearInterpolator());
            alphaAnimation.setDuration(element.getDuration());
            int size = anim.size();

            int i;
            for(i = 0; i < size; ++i) {
                Interpolator interpolator = null;
                if (!TextUtils.isEmpty(((ElementAnimation)anim.get(i)).getInterpolator())) {
                    interpolator = this.parseInterpolator(((ElementAnimation)anim.get(i)).getInterpolator());
                }

                xAnimation.addKeyFrame(((ElementAnimation)anim.get(i)).getTime(), ((ElementAnimation)anim.get(i)).getX(), interpolator);
                yAnimation.addKeyFrame(((ElementAnimation)anim.get(i)).getTime(), ((ElementAnimation)anim.get(i)).getY(), interpolator);
                scaleAnimation.addKeyFrame(((ElementAnimation)anim.get(i)).getTime(), ((ElementAnimation)anim.get(i)).getScale() * scaleRatio, interpolator);
                alphaAnimation.addKeyFrame(((ElementAnimation)anim.get(i)).getTime(), ((ElementAnimation)anim.get(i)).getAlpha(), interpolator);
            }

            i = size - 1;
            xAnimation.addKeyFrame(element.getDuration(), ((ElementAnimation)anim.get(i)).getX(), (Interpolator)null);
            yAnimation.addKeyFrame(element.getDuration(), ((ElementAnimation)anim.get(i)).getY(), (Interpolator)null);
            scaleAnimation.addKeyFrame(element.getDuration(), ((ElementAnimation)anim.get(i)).getScale() * scaleRatio, (Interpolator)null);
            alphaAnimation.addKeyFrame(element.getDuration(), ((ElementAnimation)anim.get(i)).getAlpha(), (Interpolator)null);
            this.animationSet.addAnimation(xAnimation);
            this.animationSet.addAnimation(yAnimation);
            this.animationSet.addAnimation(scaleAnimation);
            this.animationSet.addAnimation(alphaAnimation);
        }

    }

    protected Interpolator parseInterpolator(String interpolatorString) {
        if (TextUtils.isEmpty(interpolatorString)) {
            return null;
        } else {
            String[] values = interpolatorString.split(",");
            if (values != null && values.length >= 4) {
                try {
                    return new EaseCubicInterpolator(Float.valueOf(values[0]), Float.valueOf(values[1]), Float.valueOf(values[2]), Float.valueOf(values[3]));
                } catch (Exception var4) {
                    var4.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    protected void initNewAnimation(Element element) {
        List<ElementNewAnimation> anims = element.getNew_anim();
        if (anims != null) {
            float scaleRatio = element.getWidth() / 0.5F;
            float vWidth = (float)element.vWidth;
            float vHeight = (float)element.vHeight;
            this.animationNewSet = new ExtAnimationSet();
            StickerGiftItem.Params var10000 = this.params;
            var10000.scale *= scaleRatio;
            Iterator var6 = anims.iterator();

            while(true) {
                ElementNewAnimation animation;
                do {
                    if (!var6.hasNext()) {
                        this.animationNewSet.start();
                        return;
                    }

                    animation = (ElementNewAnimation)var6.next();
                } while(TextUtils.isEmpty(animation.getType()));

                Interpolator interpolator = this.parseInterpolator(animation.getInterpolator());
                ExtAnimation extAnimation = null;
                String var10 = animation.getType();
                byte var11 = -1;
                switch(var10.hashCode()) {
                case -925180581:
                    if (var10.equals("rotate")) {
                        var11 = 3;
                    }
                    break;
                case 3433509:
                    if (var10.equals("path")) {
                        var11 = 4;
                    }
                    break;
                case 92909918:
                    if (var10.equals("alpha")) {
                        var11 = 0;
                    }
                    break;
                case 109250890:
                    if (var10.equals("scale")) {
                        var11 = 2;
                    }
                    break;
                case 1052832078:
                    if (var10.equals("translate")) {
                        var11 = 1;
                    }
                }

                switch(var11) {
                case 0:
                    extAnimation = new ExtAlphaAnimation(this, animation.getAlpha(), animation.getToAlpha(), interpolator);
                    break;
                case 1:
                    extAnimation = new ExtTranslateAnimation(this, animation.getX(), animation.getY(), animation.getToX(), animation.getToY(), interpolator);
                    break;
                case 2:
                    extAnimation = new ExtScaleAnimation(this, animation.getScale() * scaleRatio, animation.getToScale() * scaleRatio, interpolator);
                    break;
                case 3:
                    extAnimation = new ExtRotateAnimation(this, animation.getDegress(), animation.getToDegress(), interpolator);
                    break;
                case 4:
                    label96: {
                        PathSet pathSet = new PathSet();
                        if (animation.getPoints() != null && animation.getPoints().size() > 0) {
                            List<PointSet> pointSetList = animation.getPoints();
                            if (pointSetList == null || pointSetList.size() == 0) {
                                break label96;
                            }

                            PointSet firstPoint = (PointSet)pointSetList.get(0);
                            pathSet.moveTo((Float)firstPoint.getPoint().get(0) / vWidth, 1.0F - (Float)firstPoint.getPoint().get(1) / vHeight, this.parseInterpolator(firstPoint.getInterpolator()));

                            for(int i = 1; i < animation.getPoints().size(); ++i) {
                                PointSet pointSet = (PointSet)animation.getPoints().get(i);
                                List<Float> group = pointSet.getPoint();
                                Interpolator parseInterpolator = this.parseInterpolator(pointSet.getInterpolator());
                                if (group.size() == 2) {
                                    pathSet.lineTo((Float)group.get(0) / vWidth, 1.0F - (Float)group.get(1) / vHeight, parseInterpolator);
                                } else if (group.size() == 4) {
                                    pathSet.quadBezierTo((Float)group.get(0) / vWidth, 1.0F - (Float)group.get(1) / vHeight, (Float)group.get(2) / vWidth, 1.0F - (Float)group.get(3) / vHeight, parseInterpolator);
                                } else if (group.size() == 6) {
                                    pathSet.cubicBezierTo((Float)group.get(0) / vWidth, 1.0F - (Float)group.get(1) / vHeight, (Float)group.get(2) / vWidth, 1.0F - (Float)group.get(3) / vHeight, (Float)group.get(4) / vWidth, 1.0F - (Float)group.get(5) / vHeight, parseInterpolator);
                                }
                            }
                        }

                        extAnimation = ExtPathAnimation.toPath(this, new PathEvaluator(), pathSet.getPathPoints().toArray());
                    }
                }

                if (null != extAnimation) {
                    ((ExtAnimation)extAnimation).setDuration(animation.getDuration());
                    ((ExtAnimation)extAnimation).setStartDelay(animation.getStartDelay());
                    this.animationNewSet.addAnimation((ExtAnimation)extAnimation);
                }
            }
        }
    }

    public void initCoordinate() {
        float realWidth = 0.5F;
        this.stickerScale = this.sticker.getHeight() / this.sticker.getWidth();
        float[] squareCoords = new float[]{-realWidth, realWidth * this.stickerScale, -realWidth, -realWidth * this.stickerScale, realWidth, -realWidth * this.stickerScale, realWidth, realWidth * this.stickerScale};
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        this.vertexBuffer = bb.asFloatBuffer();
        this.vertexBuffer.put(squareCoords);
        this.vertexBuffer.position(0);
        ByteBuffer bb2 = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        this.vertexBuffer2 = bb2.asFloatBuffer();
        this.vertexBuffer2.put(squareCoords);
        this.vertexBuffer2.position(0);
        this.texData0 = new float[]{-realWidth, realWidth * this.stickerScale, 0.0F, 1.0F, -realWidth, -realWidth * this.stickerScale, 0.0F, 1.0F, realWidth, -realWidth * this.stickerScale, 0.0F, 1.0F, realWidth, realWidth * this.stickerScale, 0.0F, 1.0F};
        Matrix.setIdentityM(this.mViewMatrix, 0);
        this.mViewMatrix[0] = -1.0F;
    }

    public void setProjectionMatrix(float[] projectionMatrix) {
        this.mProjectionMatrix = projectionMatrix;
    }

    public float getStickerAspectRatio() {
        return this.sticker != null ? this.sticker.getHeight() * 1.0F / this.sticker.getWidth() : 1.0F;
    }

    public Bitmap getDelegateBitmap() {
        if ((this.curBitmap == null || this.curBitmap.isRecycled()) && this.sticker.getImageProvider() != null) {
            this.curBitmap = this.sticker.getImageProvider().getBitmap();
        }

        return this.curBitmap;
    }

    public void setAnimation(float x, float y, HoneyAnimationSet animationSet) {
        this.animationSet = animationSet;
        this.params.x = x;
        this.params.y = y;
    }

    public Object getLockObject() {
        return this.maskLock;
    }

    public void setParamForMatrix(float w, float h, PointF centerPoint, float angle) {
        if (centerPoint != null) {
            float tX = -(centerPoint.x * 2.0F - 1.0F);
            float tY = -centerPoint.y * 2.0F + 1.0F;
            float[] mvpMatrix = new float[16];
            float[] newResult = new float[16];
            Matrix.setIdentityM(newResult, 0);
            Matrix.translateM(newResult, 0, -tX, tY, 0.0F);
            Matrix.multiplyMM(mvpMatrix, 0, this.mViewMatrix, 0, this.mProjectionMatrix, 0);
            Matrix.multiplyMM(mvpMatrix, 0, newResult, 0, mvpMatrix, 0);
            Matrix.rotateM(mvpMatrix, 0, angle, 0.0F, 0.0F, 1.0F);
            Matrix.scaleM(mvpMatrix, 0, w * 1.0F, h * 1.0F, 1.0F);
            Matrix.multiplyMM(newResult, 0, mvpMatrix, 0, this.texData0, 0);
            float[] finalResult = new float[]{newResult[0], newResult[1], newResult[4], newResult[5], newResult[8], newResult[9], newResult[12], newResult[13]};
            this.mvpLists.add(finalResult);
        }
    }

    public void clearPoints() {
        synchronized(this.maskLock) {
            if (this.mvpLists != null) {
                this.mvpLists.clear();
            }

        }
    }

    public void setScale(float value) {
        this.params.scale = value;
    }

    public void setX(float x) {
        this.params.x = x;
    }

    public void setY(float y) {
        this.params.y = y;
    }

    public void setAlpha(float alpha) {
        this.params.alpha = alpha;
    }

    public void setDegress(float degress) {
        this.params.degress = degress;
    }

    public void setCenterPoint(PathPoint point) {
        this.params.x = point.x;
        this.params.y = point.y;
    }

    public void setProgress(float progress) {
        if (this.startTime == -1L) {
            this.startTime = System.currentTimeMillis();
        }

        if (this.mStickerDuration > -1L && this.startTime != -1L && System.currentTimeMillis() - this.startTime > this.mStickerDuration) {
            this.clearPoints();
            if (null != this.finishListener) {
                this.finishListener.stickerRenderFinished();
            }

        } else {
            if (this.animationSet != null) {
                this.animationSet.setProgress(progress);
            }

        }
    }

    protected void invalidate() {
        this.setParamForMatrix(this.params.scale, this.params.scale, new PointF(this.params.x, this.params.y), this.params.degress);
    }

    public void setRenderTime(long renderTime) {
        this.mRenderTime = renderTime;
        if (this.animationNewSet != null) {
            this.animationNewSet.animateBasedOnTime(renderTime);
        }

        this.invalidate();
    }

    public void destroy() {
        if (this.texture_sticker != 0) {
            int[] tex = new int[]{this.texture_sticker};
            GLES20.glDeleteTextures(1, tex, 0);
            this.texture_sticker = 0;
        }

        if (this.curBitmap != null && !this.curBitmap.isRecycled()) {
            this.curBitmap.recycle();
        }

    }

    public void setFinishListener(StickerGiftItem.RenderFinishListener renderFinishListener) {
        this.finishListener = renderFinishListener;
    }

    public interface RenderFinishListener {
        void stickerRenderFinished();
    }

    public class Params {
      public   float alpha = 1.0F;
        public float scale = 1.0F;
        public  float x = -0.5F;
        public  float y = -0.5F;
        public  float degress = 0.0F;

        Params() {
        }

        public String toString() {
            return "Params{alpha=" + this.alpha + ", scale=" + this.scale + ", x=" + this.x + ", y=" + this.y + ", degress=" + this.degress + '}';
        }
    }
}
