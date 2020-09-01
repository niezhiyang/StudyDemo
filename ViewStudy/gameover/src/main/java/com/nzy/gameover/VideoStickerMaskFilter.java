package com.nzy.gameover;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.view.animation.LinearInterpolator;

import com.nzy.gameover.sticker.Element;
import com.nzy.gameover.sticker.HoneyAnimation;
import com.nzy.gameover.sticker.HoneyAnimationSet;
import com.nzy.gameover.sticker.StickerGiftItem;
import com.nzy.gameover.sticker.VideoSticker;
import com.nzy.gameover.sticker.animation.HoneyAlphaAnimation;
import com.nzy.gameover.sticker.animation.HoneyScaleAnimation;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoStickerMaskFilter extends BasicFilter implements IVideoTrackTime {
    protected static final String ATTRIBUTE_MVP_MATRIX = "uMVPMatrix";
    protected static final String ATTRIBUTE_DECORATION_SIZE = "decorationSize";
    protected static final String ATTRIBUTE_POSITION_2 = "position2";
    protected static final String UNIFORM_ISALPHA = "isAlpha";
    protected static final String UNIFORM_ALPHA = "alpha";
    protected List<StickerGiftItem> stickerItemList;
    protected List<StickerGiftItem> stickerItemDestroyList;
    protected final short[] drawOrder = new short[]{0, 1, 2, 0, 2, 3};
    protected static final int COORDS_PER_VERTEX = 2;
    protected final int vertexStride = 8;
    private int alphaHandler;
    protected int decorateHandler;
    private int mVPMatrixHandler;
    protected int positionHandle2;
    public ShortBuffer drawListBuffer;
    protected final float[] mProjectionMatrix = new float[16];
    protected FloatBuffer vertexBufer;
    protected float[] textureCoord;
    protected Context context;
    private long mTotalTime = 6000L;
    private int circleProgram;
    protected int circleTextureHandle;
    protected int circleTexCoordHandle;
    private int circleAlphaHandler;
    private int circleDecorateHandler;
    private int circleMVPMatrixHandler;
    protected int circlePositionHandle;
    protected int circlePositionHandle2;
    private long lastTimestap = -1L;

    protected String getVertexShader() {
        return "uniform mat4 uMVPMatrix;attribute vec4 position;\nattribute vec4   position2 ; varying vec2 textureCoordinate;\nuniform vec2 decorationSize;\nvoid main() {  gl_Position = position;  vec2 coord = position2.xy;  coord = (coord) / decorationSize;\n  textureCoordinate = vec2(1.0 - (coord.x + 0.5),1.0-(coord.y + 0.5));\n}";
    }

    protected String getFragmentShader() {
        return "precision mediump float;uniform sampler2D inputImageTexture0;varying vec2 textureCoordinate;\nuniform bool isAlpha;\nuniform float alpha;\nvoid main() {   vec4 color1  = texture2D(inputImageTexture0,textureCoordinate);\n   color1 = color1 * alpha; \n   gl_FragColor = color1; \n}";
    }

    private String getCircleVertexShader() {
        return "uniform mat4 uMVPMatrix;attribute vec4 position;\nattribute vec4   position2 ; varying vec2 textureCoordinate;\nuniform vec2 decorationSize;\nvoid main() {  gl_Position = position;   vec2 coord = position2.xy;  coord = (coord) / decorationSize;\n  textureCoordinate = vec2(1.0 - (coord.x + 0.5),1.0-(coord.y + 0.5));\n}";
    }

    private String getCircleFragmentShader() {
        return "precision mediump float;uniform sampler2D inputImageTexture0;varying vec2 textureCoordinate;\nuniform float alpha;\nfloat Circle(vec2 uv,vec2 p, float r,float blur){\n    float d = length(uv - p);\n    float c = smoothstep(r,r-blur,d);\n    return c;\n}void main() {   vec2 uv = textureCoordinate.xy;\n   vec4 color = texture2D(inputImageTexture0, uv).rgba;\n   uv -= 0.5;\n   float mask = Circle(uv, vec2(0.0,0.0), 0.5, 0.01);\n   vec4 colorMask = vec4(1.0,1.0,1.0,1.0);\n   vec4 ret = mix(colorMask*mask, color, 1.0 - smoothstep(0.46, 0.48, length(uv-vec2(0.0,0.0))));\n   float dis = distance(textureCoordinate,vec2(0.5,0.5));   if (dis > 0.49) {       ret.r = 1.0;       ret.b = 1.0;       ret.g = 1.0;       ret.a = 1.0 * (1.0 - smoothstep(0.49, 0.5, dis));   }\n   gl_FragColor = ret * alpha; }";
    }

    public VideoStickerMaskFilter(Context context) {
        this.context = context;
        this.stickerItemList = new ArrayList();
        this.stickerItemDestroyList = new ArrayList();
        ByteBuffer dlb = ByteBuffer.allocateDirect(this.drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        this.drawListBuffer = dlb.asShortBuffer();
        this.drawListBuffer.put(this.drawOrder);
        this.drawListBuffer.position(0);
    }

    protected void initShaderHandles() {
        super.initShaderHandles();
        this.positionHandle2 = GLES20.glGetAttribLocation(this.programHandle, "position2");
        this.mVPMatrixHandler = GLES20.glGetUniformLocation(this.programHandle, "uMVPMatrix");
        this.decorateHandler = GLES20.glGetUniformLocation(this.programHandle, "decorationSize");
        this.alphaHandler = GLES20.glGetUniformLocation(this.programHandle, "alpha");
        if (this.circleProgram == 0) {
            this.circleProgram = ShaderHelper.buildProgram(this.getCircleVertexShader(), this.getCircleFragmentShader());
        }

        this.circleTextureHandle = GLES20.glGetUniformLocation(this.circleProgram, "inputImageTexture0");
        this.circleTexCoordHandle = GLES20.glGetAttribLocation(this.circleProgram, "inputTextureCoordinate");
        this.circlePositionHandle = GLES20.glGetAttribLocation(this.circleProgram, "position");
        this.circlePositionHandle2 = GLES20.glGetAttribLocation(this.circleProgram, "position2");
        this.circleMVPMatrixHandler = GLES20.glGetUniformLocation(this.circleProgram, "uMVPMatrix");
        this.circleDecorateHandler = GLES20.glGetUniformLocation(this.circleProgram, "decorationSize");
        this.circleAlphaHandler = GLES20.glGetUniformLocation(this.circleProgram, "alpha");
    }

    protected void passStickerShaderValues(StickerGiftItem item, float[] mvpMatrix) {
        if (item.fvertexBuffer == null) {
            ByteBuffer bb = ByteBuffer.allocateDirect(mvpMatrix.length * 4);
            bb.order(ByteOrder.nativeOrder());
            item.fvertexBuffer = bb.asFloatBuffer();
        }

        item.fvertexBuffer.position(0);
        item.fvertexBuffer.put(mvpMatrix);
        item.fvertexBuffer.position(0);
        GLES20.glVertexAttribPointer(this.positionHandle, 2, 5126, false, 8, item.fvertexBuffer);
        GLES20.glVertexAttribPointer(this.positionHandle2, 2, 5126, false, 8, item.vertexBuffer);
        GLES20.glEnableVertexAttribArray(this.positionHandle);
        GLES20.glEnableVertexAttribArray(this.positionHandle2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, item.texture_sticker);
        GLES20.glUniform1i(this.textureHandle, 0);
        GLES20.glUniform1f(this.alphaHandler, item.params.alpha);
    }

    protected void bindShaderAttributes() {
        super.bindShaderAttributes();
        GLES20.glBindAttribLocation(this.programHandle, 2, "uMVPMatrix");
        GLES20.glBindAttribLocation(this.programHandle, 3, "decorationSize");
        GLES20.glBindAttribLocation(this.circleProgram, 2, "uMVPMatrix");
        GLES20.glBindAttribLocation(this.circleProgram, 3, "decorationSize");
    }

    private void drawBackgroundImage() {
        this.renderVertices.position(0);
        GLES20.glVertexAttribPointer(this.positionHandle, 2, 5126, false, 8, this.renderVertices);
        GLES20.glEnableVertexAttribArray(this.positionHandle);
        GLES20.glUniform2f(this.decorateHandler, 1.0F, 1.0F);
        if (this.textureCoord == null) {
            this.textureCoord = new float[8];
        }

        this.textureVertices[this.curRotation].position(0);
        this.textureVertices[this.curRotation].get(this.textureCoord);
        if (this.vertexBufer == null) {
            this.vertexBufer = ByteBuffer.allocateDirect(this.textureCoord.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        }

        for(int i = 0; i < 8; ++i) {
            this.textureCoord[i] = this.textureCoord[i] * -1.0F + 0.5F;
        }

        this.vertexBufer.position(0);
        this.vertexBufer.put(this.textureCoord);
        this.vertexBufer.position(0);
        GLES20.glVertexAttribPointer(this.positionHandle2, 2, 5126, false, 8, this.vertexBufer);
        GLES20.glEnableVertexAttribArray(this.positionHandle2);
        GLES20.glUniform1f(this.alphaHandler, 1.0F);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.texture_in);
        GLES20.glUniform1i(this.textureHandle, 0);
        GLES20.glDrawArrays(5, 0, 4);
    }

    public void drawSub() {
        GLES20.glViewport(0, 0, this.getWidth(), this.getHeight());
        GLES20.glUseProgram(this.programHandle);
        GLES20.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);
        GLES20.glClear(16640);
        this.drawBackgroundImage();
        GLES20.glEnable(3042);
        GLES20.glBlendFuncSeparate(770, 771, 1, 771);
        if (this.stickerItemDestroyList.size() > 0) {
            List<StickerGiftItem> destroyList = new ArrayList(this.stickerItemDestroyList);
            Iterator itemIterator = destroyList.iterator();

            while(itemIterator.hasNext()) {
                StickerGiftItem StickerGiftItem = (StickerGiftItem)itemIterator.next();
                StickerGiftItem.destroy();
                this.stickerItemDestroyList.remove(StickerGiftItem);
                this.stickerItemList.remove(StickerGiftItem);
            }

            destroyList.clear();
        }

        Iterator var8 = this.stickerItemList.iterator();

        while(var8.hasNext()) {
            StickerGiftItem stickerItem = (StickerGiftItem)var8.next();
            synchronized(stickerItem.getLockObject()) {
                stickerItem.imageWidth = (float)this.getWidth();
                stickerItem.imageHeight = (float)this.getHeight();
                if (stickerItem.mvpLists != null && stickerItem.mvpLists.size() > 0) {
                    Bitmap curBitmap = stickerItem.getDelegateBitmap();
                    if (curBitmap != null && !curBitmap.isRecycled()) {
                        if (stickerItem.texture_sticker > 0) {
                            this.updateBitmap(curBitmap, stickerItem.texture_sticker);
                        } else {
                            stickerItem.texture_sticker = this.bitmapToTexture(curBitmap);
                        }
                    }
                }

                if (stickerItem.texture_sticker != 0) {
                    float[] mvpMatrix;
                    Iterator var10;
                    if (stickerItem.useCircle) {
                        GLES20.glUseProgram(this.circleProgram);
                        var10 = stickerItem.mvpLists.iterator();

                        while(var10.hasNext()) {
                            mvpMatrix = (float[])var10.next();
                            this.passCircleStickerShaderValues(stickerItem, mvpMatrix);
                            this.drawCircleSticker(stickerItem.getStickerAspectRatio());
                        }
                    } else {
                        GLES20.glUseProgram(this.programHandle);
                        var10 = stickerItem.mvpLists.iterator();

                        while(var10.hasNext()) {
                            mvpMatrix = (float[])var10.next();
                            this.passStickerShaderValues(stickerItem, mvpMatrix);
                            this.drawSticker(stickerItem.getStickerAspectRatio());
                        }
                    }

                    stickerItem.clearPoints();
                }
            }
        }

        GLES20.glDisable(3042);
    }

    private int bitmapToTexture(Bitmap bitmap) {
        int[] tex = new int[1];
        GLES20.glGenTextures(1, tex, 0);
        GLES20.glBindTexture(3553, tex[0]);
        GLES20.glTexParameterf(3553, 10240, 9729.0F);
        GLES20.glTexParameterf(3553, 10241, 9729.0F);
        GLES20.glTexParameterf(3553, 10242, 33071.0F);
        GLES20.glTexParameterf(3553, 10243, 33071.0F);
        if (bitmap != null) {
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        }

        return tex[0];
    }

    private void updateBitmap(Bitmap bitmap, int textid) {
        GLES20.glBindTexture(3553, textid);
        GLES20.glTexParameterf(3553, 10240, 9729.0F);
        GLES20.glTexParameterf(3553, 10241, 9729.0F);
        GLES20.glTexParameterf(3553, 10242, 33071.0F);
        GLES20.glTexParameterf(3553, 10243, 33071.0F);
        if (bitmap != null) {
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
        }

    }

    public void passCircleStickerShaderValues(StickerGiftItem item, float[] mvpMatrix) {
        if (item.fvertexBuffer == null) {
            ByteBuffer bb = ByteBuffer.allocateDirect(mvpMatrix.length * 4);
            bb.order(ByteOrder.nativeOrder());
            item.fvertexBuffer = bb.asFloatBuffer();
        }

        item.fvertexBuffer.position(0);
        item.fvertexBuffer.put(mvpMatrix);
        item.fvertexBuffer.position(0);
        GLES20.glVertexAttribPointer(this.circlePositionHandle, 2, 5126, false, 8, item.fvertexBuffer);
        GLES20.glVertexAttribPointer(this.circlePositionHandle2, 2, 5126, false, 8, item.vertexBuffer);
        GLES20.glEnableVertexAttribArray(this.circlePositionHandle);
        GLES20.glEnableVertexAttribArray(this.circlePositionHandle2);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, item.texture_sticker);
        GLES20.glUniform1i(this.circleTextureHandle, 0);
        GLES20.glUniform1f(this.circleAlphaHandler, item.params.alpha);
    }

    private void drawSticker(float aspectRatio) {
        GLES20.glUniform2f(this.decorateHandler, 1.0F, aspectRatio);
        GLES20.glDrawElements(4, this.drawOrder.length, 5123, this.drawListBuffer);
        GLES20.glDisableVertexAttribArray(this.positionHandle);
    }

    private void drawCircleSticker(float aspectRatio) {
        GLES20.glUseProgram(this.circleProgram);
        GLES20.glUniform2f(this.circleDecorateHandler, 1.0F, aspectRatio);
        GLES20.glDrawElements(4, this.drawOrder.length, 5123, this.drawListBuffer);
        GLES20.glDisableVertexAttribArray(this.circlePositionHandle);
    }

    public void setTotalTime(long totalTime) {
        if (totalTime > 0L) {
            this.mTotalTime = totalTime;
        }

    }

    public void addStickerWithDefaultAnimation(StickerGiftItem item) {
        item.isDraw = true;
        item.setProjectionMatrix(this.mProjectionMatrix);
        this.stickerItemList.add(item);
        HoneyAnimation bounceAnimation = new HoneyScaleAnimation(item, new DesignBounceInterpolator(6.0F, 30.0F, 80.0F));
        bounceAnimation.setDuration(6000);
        bounceAnimation.addKeyFrame(0, 0.0F);
        bounceAnimation.addKeyFrame(1300, 0.0F);
        bounceAnimation.addKeyFrame(2000, 0.6F);
        bounceAnimation.addKeyFrame(5700, 0.6F);
        bounceAnimation.addKeyFrame(6000, 0.0F);
        HoneyAnimationSet animationSet = new HoneyAnimationSet();
        animationSet.addAnimation(bounceAnimation);
        HoneyAnimation alphaAnimation = new HoneyAlphaAnimation(item, new LinearInterpolator());
        alphaAnimation.addKeyFrame(0, 1.0F);
        alphaAnimation.addKeyFrame(6000, 1.0F);
        animationSet.addAnimation(alphaAnimation);
        float x = item.sticker.getX();
        float y = item.sticker.getY();
        item.setAnimation(x, y, animationSet);
    }

    public void addSticker(VideoSticker sticker) {
        Element element = sticker.element;
        final StickerGiftItem item = new StickerGiftItem(sticker, this.context, element);
        item.isDraw = true;
        item.useCircle = element.useCircle;
        item.setProjectionMatrix(this.mProjectionMatrix);
        item.setFinishListener(new StickerGiftItem.RenderFinishListener() {
            public void stickerRenderFinished() {
                VideoStickerMaskFilter.this.removeStickerItem(item);
            }
        });
        this.stickerItemList.add(item);
    }

    public void newTextureReady(int texture, GLTextureOutputRenderer source, boolean newData) {
        if (newData) {
            this.markAsDirty();
        }

        this.texture_in = texture;
        this.setWidth(source.getWidth());
        this.setHeight(source.getHeight());
        float ratio = (float)this.getWidth() / (float)this.getHeight();
        Matrix.orthoM(this.mProjectionMatrix, 0, -1.0F, 1.0F, -1.0F / ratio, 1.0F / ratio, 3.0F, 7.0F);
        this.onDrawFrame();
        source.unlockRenderBuffer();
    }

    public void setTimeStamp(long timeStamp) {
        if (timeStamp >= this.lastTimestap) {
            if (this.stickerItemList != null) {
                float progress = (float)timeStamp / (float)this.mTotalTime;
                Iterator var4 = this.stickerItemList.iterator();

                while(var4.hasNext()) {
                    StickerGiftItem item = (StickerGiftItem)var4.next();
                    item.setRenderTime(timeStamp);
                    item.setProgress(progress);
                }
            }

            this.lastTimestap = timeStamp;
        }
    }

    private void removeStickerItem(StickerGiftItem item) {
        synchronized(this.getLockObject()) {
            this.stickerItemDestroyList.remove(item);
        }
    }

    private void removeStickerItem(String stickerType) {
        Iterator var2 = this.stickerItemList.iterator();

        while(var2.hasNext()) {
            StickerGiftItem item = (StickerGiftItem)var2.next();
            if (stickerType.equals(item.sticker.getStickerType())) {
                this.stickerItemDestroyList.add(item);
            }
        }

    }

    public void destroy() {
        super.destroy();
        Iterator var1;
        StickerGiftItem item;
        if (this.stickerItemList != null && this.stickerItemList.size() > 0) {
            var1 = this.stickerItemList.iterator();

            while(var1.hasNext()) {
                item = (StickerGiftItem)var1.next();
                item.destroy();
            }
        }

        if (this.stickerItemDestroyList != null && this.stickerItemDestroyList.size() > 0) {
            var1 = this.stickerItemDestroyList.iterator();

            while(var1.hasNext()) {
                item = (StickerGiftItem)var1.next();
                item.destroy();
            }
        }

    }
}
