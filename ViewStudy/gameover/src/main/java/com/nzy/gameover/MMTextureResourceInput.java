package com.nzy.gameover;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class MMTextureResourceInput extends GLTextureOutputRenderer {

    private final static String TAG = MMTextureResourceInput.class.getName();
    private static final String UNIFORM_CAM_MATRIX = "u_Matrix";
    private int matrixHandle;
    public SurfaceTexture mText = null;
    protected int updateText = 0;
    private int mHeightOutput = 0;
    private int mWidthOutput = 0;
    protected boolean mSizeChanged = false;
    private boolean mIsOES = true;
    private Bitmap mBitmap = null;
    private int mBitmapWidth = -1;
    private int mBitmapHeight = -1;
    private int mTextureIDBitmap = 0;
    private SurfaceTexture mSurfaceTextureBitmap = null;
    private int[] mTextureTex = null;
    protected int textureHandle1;
    private int mOESLocation;
    private float mOES = 1.0f;
    public static final String UNIFORM_TEXTURE1 = UNIFORM_TEXTUREBASE + 1;

    private int mDisplayMode = 1;
    private int mInputWidth = 0;
    private int mInputHeight = 0;

    public static final float[] identityMatrix =
              new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1};

    public static final int FLIP_VERTICAL = 0;
    public static final int FLIP_HORIZONTAL = 1;
    public static final int FLIP_BOTH = 2;
    public static final int FLIP_NONE = 3;

    /**
     * Creates a GLImageToTextureRenderer using the given bitmap as the image input.
     * The bitmap which contains the image.
     */
    public MMTextureResourceInput() {
        textureVertices = new FloatBuffer[4];

        float[] texData0 = new float[]{
                0.0f, 1.0f,
                1.0f, 1.0f,
                0.0f, 0.0f,
                1.0f, 0.0f,
        };
        textureVertices[0] = ByteBuffer.allocateDirect(texData0.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[0].put(texData0).position(0);

        float[] texData1 = new float[]{
                1.0f, 1.0f,
                1.0f, 0.0f,
                0.0f, 1.0f,
                0.0f, 0.0f,
        };
        textureVertices[1] = ByteBuffer.allocateDirect(texData1.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[1].put(texData1).position(0);

        float[] texData2 = new float[]{
                1.0f, 0.0f,
                0.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };
        textureVertices[2] = ByteBuffer.allocateDirect(texData2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[2].put(texData2).position(0);

        float[] texData3 = new float[]{
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
        };
        textureVertices[3] = ByteBuffer.allocateDirect(texData3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[3].put(texData3).position(0);

        mTextureIDBitmap = 0;
        mSurfaceTextureBitmap = null;
        mIsOES = true;
        mBitmap = null;
    }

    public void changeCurRotation(int rotation) {
        curRotation = 0;
        rotateClockwise90Degrees(rotation/ 90);
    }

    public void flipPosition(int flipDirection) {
        if (flipDirection == FLIP_NONE) {
            setRenderVertices(new float[]{
                    -1f, -1f,
                    1f, -1f,
                    -1f, 1f,
                    1f, 1f
            });
            textureVertices = new FloatBuffer[4];
            float[] texData0 = new float[]{
                    0.0f, 0.0f,
                    1.0f, 0.0f,
                    0.0f, 1.0f,
                    1.0f, 1.0f,
            };

            textureVertices[0] = ByteBuffer.allocateDirect(texData0.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVertices[0].put(texData0).position(0);

            float[] texData1 = new float[]{
                    0.0f, 1.0f,
                    0.0f, 0.0f,
                    1.0f, 1.0f,
                    1.0f, 0.0f,
            };
            textureVertices[1] = ByteBuffer.allocateDirect(texData1.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVertices[1].put(texData1).position(0);

            float[] texData2 = new float[]{
                    1.0f, 1.0f,
                    0.0f, 1.0f,
                    1.0f, 0.0f,
                    0.0f, 0.0f,
            };
            textureVertices[2] = ByteBuffer.allocateDirect(texData2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVertices[2].put(texData2).position(0);

            float[] texData3 = new float[]{
                    1.0f, 0.0f,
                    1.0f, 1.0f,
                    0.0f, 0.0f,
                    0.0f, 1.0f,
            };
            textureVertices[3] = ByteBuffer.allocateDirect(texData3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVertices[3].put(texData3).position(0);
            return;
        }
        float minX = 0f;
        float maxX = 1f;
        float minY = 0f;
        float maxY = 1f;
        switch (flipDirection) {
            case FLIP_VERTICAL:
                minX = 1f;
                maxX = 0f;
                break;
            case FLIP_HORIZONTAL:
                minY = 1f;
                maxY = 0f;
                break;
            case FLIP_BOTH:
                minX = 1f;
                maxX = 0f;
                minY = 1f;
                maxY = 0f;
                break;
        }

        float[] texData0 = new float[]{
                minX, minY,
                maxX, minY,
                minX, maxY,
                maxX, maxY,
        };
        textureVertices[0] = ByteBuffer.allocateDirect(texData0.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[0].put(texData0).position(0);

        float[] texData1 = new float[]{
                minY, maxX,
                minY, minX,
                maxY, maxX,
                maxY, minX,
        };
        textureVertices[1] = ByteBuffer.allocateDirect(texData1.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[1].put(texData1).position(0);

        float[] texData2 = new float[]{
                maxX, maxY,
                minX, maxY,
                maxX, minY,
                minX, minY,
        };
        textureVertices[2] = ByteBuffer.allocateDirect(texData2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[2].put(texData2).position(0);

        float[] texData3 = new float[]{
                maxY, minX,
                maxY, maxX,
                minY, minX,
                minY, maxX,
        };
        textureVertices[3] = ByteBuffer.allocateDirect(texData3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        textureVertices[3].put(texData3).position(0);

    }

    @Override
    public void setRenderSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }

        if (width != mWidthOutput || height != mHeightOutput) {
//            Log.e(TAG, "----size: [" + width + ", " + height+"]<---["+mWidthOutput+","+mHeightOutput+"]");
            mSizeChanged = true;
            mHeightOutput = height;
            mWidthOutput = width;
        }

        if (mSizeChanged)
            super.setRenderSize(width, height);
    }

    public void drawImageFrame()
    {
        drawFrame();
    }

    @Override
    public void drawFrame() {
        if(mText != null && updateText ==1) {
            try {
//                Log.e("TextureInput", "updateTexImage >>>>>>>>>>>");
                mText.updateTexImage();
            } catch (RuntimeException e) {
//                Log.d(TAG, " mSurfaceTexture.updateTexImage exception ");
            }
            updateText = 0;
        }

        if (mSizeChanged) {
            handleSizeChange();
            mSizeChanged = false;
        }

        super.drawFrame();
    }

    /* (non-Javadoc)
     * @see project.android.imageprocessing.input.GLTextureOutputRenderer#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        if (null != mText){
            mText.release();
        }
        if (texture_in != 0) {
            int[] tex = new int[1];
            tex[0] = texture_in;
            GLES20.glDeleteTextures(1, tex, 0);
        }
        if (mSurfaceTextureBitmap != null) {
            mSurfaceTextureBitmap.release();
            mSurfaceTextureBitmap = null;
        }


        mText = null;
        mTextureIDBitmap = 0;
        mSurfaceTextureBitmap = null;
        mIsOES = true;
        mBitmap = null;
    }

    @Override
    public void releaseFrameBuffer() {
        super.releaseFrameBuffer();
        if (texture_in != 0) {
            int[] tex = new int[1];
            tex[0] = texture_in;
            GLES20.glDeleteTextures(1, tex, 0);
        }
        mText = null;
        mIsOES = true;
        mBitmap = null;


    }
    public void loadTexture(int textId, SurfaceTexture text) {
        if (mIsOES) {
            texture_in = textId;
            mText = text;
            updateText = 1;
//            Log.e("TextureInput", "loadTexture updateText == 1>>>>>>>>>>>");
            markAsDirty();
        } else {
//            Log.e(TAG, "----loadTexture:(error) oes="+mIsOES+"; id="+textId+"; "+text);
        }
    }

    private int bitmapToTexture(Bitmap bitmap) {
        //int[] tex = new int[1];
        //GLES20.glGenTextures(1, tex, 0);
        //GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex[0]);
        if (mTextureTex != null) {
            GLES20.glDeleteTextures(1, mTextureTex, 0);
            mTextureTex = null;
        }

        mTextureTex = new int[1];
        GLES20.glGenTextures(1, mTextureTex, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureTex[0]);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        if (bitmap != null && !bitmap.isRecycled()) {
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        } else {
//            Log.e(TAG, "(load)bitmap no exist!!!");
        }
        //return tex[0];
        return mTextureTex[0];
    }

    public static void updateBitmap(Bitmap bitmap, int textid) {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textid);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,
                GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
        if (bitmap != null && !bitmap.isRecycled()) {
            GLUtils.texSubImage2D(GLES20.GL_TEXTURE_2D, 0, 0, 0, bitmap);
        } else {
//            Log.e(TAG, "(update)bitmap no exist!!!");
        }
    }

    public void loadTexture(Bitmap bitmap) {
        if (mIsOES) {
        } else {
            if (bitmap != null && !bitmap.isRecycled()) {
                if (mTextureIDBitmap == 0 || mSurfaceTextureBitmap == null) {
                    mBitmapWidth = bitmap.getWidth();
                    mBitmapHeight = bitmap.getHeight();
                    setRenderSize(mBitmapWidth, mBitmapHeight);
                    mTextureIDBitmap = bitmapToTexture(bitmap);
                    if(mTextureIDBitmap > 0) {
                        mSurfaceTextureBitmap = new SurfaceTexture(mTextureIDBitmap);
                        bitmap.recycle();
                    }
                } else {
                    int height = bitmap.getHeight();
                    int width = bitmap.getWidth();
                    if (height != mBitmapHeight || width != mBitmapWidth) {
                        mBitmapWidth = width;
                        mBitmapHeight = height;
                        setRenderSize(mBitmapWidth, mBitmapHeight);
//                        Log.e(TAG, "----loadTexture: <load>"+bitmap+","+mBitmapWidth+","+mBitmapHeight);
                        mTextureIDBitmap = 0;
                        if (mSurfaceTextureBitmap != null) {
                            mSurfaceTextureBitmap.release();
                            mSurfaceTextureBitmap = null;
                        }
                        mTextureIDBitmap = bitmapToTexture(bitmap);
                        mSurfaceTextureBitmap = new SurfaceTexture(mTextureIDBitmap);
                    } else {
//                        Log.e(TAG, "----loadTexture: <update>"+bitmap+","+mBitmapWidth+","+mBitmapHeight);
                        updateBitmap(bitmap, mTextureIDBitmap);
                    }
                    bitmap.recycle();
                }
            }

            texture_in = mTextureIDBitmap;
            mText = mSurfaceTextureBitmap;

            if (mText == null) {
                return;
            }

            updateText = 1;
            markAsDirty();
        }
    }

    public int getBitmapTextureID(){
        return mTextureIDBitmap;
    }

    public SurfaceTexture getBitmapSurfaceTexture(){
        return mSurfaceTextureBitmap;
    }

    private void bindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        if (mIsOES) {
            GLES20.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, texture_in);
        } else {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0+1);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture_in);
        }
    }

    @Override
    protected void passShaderValues() {
        renderVertices.position(0);
        GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, 8, renderVertices);
        GLES20.glEnableVertexAttribArray(positionHandle);
        textureVertices[curRotation].position(0);
        GLES20.glVertexAttribPointer(texCoordHandle, 2, GLES20.GL_FLOAT, false, 8, textureVertices[curRotation]);
        GLES20.glEnableVertexAttribArray(texCoordHandle);
        if (mIsOES)
            GLES20.glUniform1f(mOESLocation, 1.0f);
        else
            GLES20.glUniform1f(mOESLocation, 0.0f);
        bindTexture();
        GLES20.glUniform1i(textureHandle, 0);
        GLES20.glUniform1i(textureHandle1, 1);
        GLES20.glUniformMatrix4fv(matrixHandle, 1, false, identityMatrix, 0);
    }

    @Override
    protected void initShaderHandles() {
        super.initShaderHandles();
        matrixHandle = GLES20.glGetUniformLocation(programHandle, UNIFORM_CAM_MATRIX);
        textureHandle1 = GLES20.glGetUniformLocation(programHandle, UNIFORM_TEXTURE1);
        mOESLocation = GLES20.glGetUniformLocation(programHandle, "oesFlag");
    }

    @Override
    public void initWithGLContext() {
        super.initWithGLContext();
    }
    public void updateYUVBuffer(ByteBuffer yByteBuffer, ByteBuffer uvByteBufer) {
    }
    public void setOESMode(boolean isOES) {
        if (mIsOES != isOES) {
            ;
        }
        mIsOES = isOES;
    }

    protected void initFBO() {
        if (null != glFrameBuffer) {
            //GLRenderBufferCache.getInstance().removeFrameBufferBySize(glFrameBuffer.getBufferWidth(), glFrameBuffer.getBufferHigh());
            glFrameBuffer.destoryBuffer();
        }
        glFrameBuffer = new GLFrameBuffer(getWidth(), getHeight());
        glFrameBuffer.activityFrameBuffer(getWidth(), getHeight());
        int status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
        if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException(this + ": Failed to set up render buffer with status " + status + " and error " + GLES20.glGetError());
        }

        if (mSizeChanged) {
            mSizeChanged = false;
        }
        //long start = System.currentTimeMillis();
    }

    @Override
    protected String getFragmentShader() {
        return  "#extension GL_OES_EGL_image_external : require\n"
                + "precision mediump float;\n"
                + "uniform samplerExternalOES " + UNIFORM_TEXTURE0 + ";\n"
                + "uniform sampler2D " + UNIFORM_TEXTURE1 + ";\n"
                + "varying vec2 " + VARYING_TEXCOORD + ";\n"
                + "varying float oes_flag; \n"
                + "void main() {\n"
                + "if (oes_flag ==1.0) {"
                + "   gl_FragColor = texture2D(" + UNIFORM_TEXTURE0 + ", " + VARYING_TEXCOORD + ");\n"
                + "} else { \n"
                + "gl_FragColor = texture2D(" + UNIFORM_TEXTURE1 + ", " + VARYING_TEXCOORD + ");} \n"
                + "}\n";
    }

    @Override
    protected String getVertexShader() {
        return
                "uniform mat4 " + UNIFORM_CAM_MATRIX + ";\n"
                        + "attribute vec4 " + ATTRIBUTE_POSITION + ";\n"
                        + "attribute vec2 " + ATTRIBUTE_TEXCOORD + ";\n"
                        + "varying vec2 " + VARYING_TEXCOORD + ";\n"
                        + "uniform float oesFlag; \n"
                        + "varying float oes_flag; \n"
                        + "void main() {\n"
                        + "   oes_flag = oesFlag; \n"
                        + "   vec4 texPos = " + UNIFORM_CAM_MATRIX + " * vec4(" + ATTRIBUTE_TEXCOORD + ", 1, 1);\n"
                        + "   " + VARYING_TEXCOORD + " = texPos.xy;\n"
                        + "   gl_Position = " + ATTRIBUTE_POSITION + ";\n"
                        + "}\n";
    }

    @Override
    public void setDisplayMode(int width, int height, int type) {
        if (mDisplayMode != type || mInputHeight != height || mInputWidth != width) {

            mDisplayMode = type;
            mInputHeight = height;
            mInputWidth = width;
            super.setDisplayMode(width, height, type);
        }
    }
}

