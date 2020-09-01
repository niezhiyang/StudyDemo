package com.nzy.gameover;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLScreenEndpoint extends GLRenderer implements GLTextureInputRenderer {
    private float inputScale;
    private float zoomScale = 1;
    private float sourceWidth;
    private float sourceHeight;
    private boolean enableFinish = true;

    /**
     * Creates a GLTextureToScreenRenderer.
     * If it is not set to full screen mode, the reference to the render context is allowed to be null.
     *
     *                        Whether or not to use the input filter size as the render size or to render full screen.
     */
    public GLScreenEndpoint() {
        super();
    }


    public void setZoomScale(float zoomScale) {
        this.zoomScale = zoomScale;
    }

    public int getTextIn() {
        return texture_in;
    }

    @Override
    protected void initWithGLContext() {
//        setRenderSize(rendererContext.getWidth(), rendererContext.getHeight());
        super.initWithGLContext();
    }

    public float getInputScale() {
        return inputScale;
    }

    public void setInputScale(float inputScale) {
        this.inputScale = inputScale;
    }

    public boolean isEnableFinish() {
        return enableFinish;
    }

    public void setEnableFinish(boolean enbleFinish) {
        this.enableFinish = enbleFinish;
    }

    /* (non-Javadoc)
         * @see project.android.imageprocessing.output.GLTextureInputRenderer#newTextureReady(int, project.android.imageprocessing.input.GLTextureOutputRenderer)
         */
    @Override
    public void newTextureReady(int texture, GLTextureOutputRenderer source, boolean newData) {
        texture_in = texture;
        setWidth(source.getWidth());
        setHeight(source.getHeight());
        if (curRotation % 2 == 1) {
            sourceWidth = source.getHeight();
            sourceHeight = source.getWidth();
            inputScale = source.getWidth() / (float) source.getHeight();
        } else {
            sourceWidth = source.getWidth();
            sourceHeight = source.getHeight();
            inputScale = source.getHeight() / (float) source.getWidth();
        }
        onDrawFrame();
        source.unlockRenderBuffer();
    }

    @Override
    public void setRenderSize(int width, int height) {
        super.setRenderSize(width, height);
    }

    protected void drawFrame() {
        if (texture_in == 0) {
            return;
        }

        int dstWidth = getWidth();
        int dstHeight = getHeight();
        checkZoomIn(dstWidth, dstHeight);
        if (getHeight() * 1.0f / getWidth() != this.inputScale) {
            float outputScale = getHeight() * 1.0f / getWidth();
            drawCut(outputScale);
        } else {
            setCutTextureCord(0.0f, 1.0f, 0.0f, 1.0f);
        }
        GLES20.glViewport(0, 0, getWidth(), getHeight());

        GLES20.glUseProgram(programHandle);
        //GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(getBackgroundRed(), getBackgroundGreen(), getBackgroundBlue(), getBackgroundAlpha());



        passShaderValues();




        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);


        //GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        if (enableFinish)
            GLES20.glFinish();



    }

    @Override
    public void destroy() {
        super.destroy();
    }

    protected String getFragmentShader() {
        return
                "precision mediump float;\n"
                        + "uniform sampler2D " + UNIFORM_TEXTURE0 + ";\n"
                        + "varying vec2 " + VARYING_TEXCOORD + ";\n"
                        + "varying vec2 xy_step;\n"
                        + "varying float smooth; \n"
                        + "void main(){\n"
                        + "vec2 point = "+VARYING_TEXCOORD+"; \n"
                        + "vec4 image_c = texture2D(" + UNIFORM_TEXTURE0 + ",point); \n"
                        + "vec4 image = image_c;\n"
                        + "if (smooth >= 0.1) { \n"
                        + "vec4 image_lt=texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2(-xy_step.x,  xy_step.y), 0.0, 1.0)); \n"
                        + "vec4 image_lb=texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2(-xy_step.x, -xy_step.y), 0.0, 1.0)); \n"
                        + "vec4 image_rt=texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2( xy_step.x,  xy_step.y), 0.0, 1.0)); \n"
                        + "vec4 image_rb=texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2( xy_step.x, -xy_step.y), 0.0, 1.0)); \n"
                        + "vec4 image_l =texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2(-xy_step.x,     0.0), 0.0, 1.0)); \n"
                        + "vec4 image_r =texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2( xy_step.x,     0.0), 0.0, 1.0)); \n"
                        + "vec4 image_t =texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2(    0.0,  xy_step.y), 0.0, 1.0)); \n"
                        + "vec4 image_b =texture2D(" + UNIFORM_TEXTURE0 + ", clamp(point+vec2(    0.0, -xy_step.y), 0.0, 1.0)); \n"
                        + "image = clamp(image_c*0.25+(image_lt+image_lb+image_rt+image_rb)*0.125+(image_l+image_t+image_b+image_r)*0.0625, 0.0, 1.0); \n" //gauss3*3
                        + "} \n"
                        + "gl_FragColor = image;\n"
                        + "}\n";
    }
    protected String getVertexShader(){
        return
                "attribute vec4 " + ATTRIBUTE_POSITION + ";\n"
                        + "attribute vec2 " + ATTRIBUTE_TEXCOORD + ";\n"
                        + "varying vec2 " + VARYING_TEXCOORD + ";\n"
                        + "uniform float widthStep; \n"
                        + "uniform float heightStep; \n"
                        + "uniform float smoothMode; \n"
                        + "varying float smooth; \n"
                        + "varying vec2 xy_step;\n"
                        + "void main() {\n"
                        + "smooth = smoothMode; \n"
                        + "  " + VARYING_TEXCOORD + " = " + ATTRIBUTE_TEXCOORD + ";\n"
                        + "xy_step = vec2(widthStep, heightStep);\n"
                        + "gl_Position = " + ATTRIBUTE_POSITION + ";\n"
                        + "}\n";
    }

    private int mImageWidthStepLocation;
    private int mImageHeightStepLocation;
    private int mImageSmoothLocation;

    public static final int SMOOTH_NONE = 0;
    public static final int SMOOTH_MEAN = 1;
    public static final int SMOOTH_GAUSS = 2;
    public static final float SMOOTH_THRESHOLD = 0.5f;
    private int mSmoothMode = SMOOTH_NONE;

    private FloatBuffer[] textureVerticesCut;
    private float[] texData0;
    private float[] texData1;
    private float[] texData2;
    private float[] texData3;
    private boolean isSetTextureVertices = false;

    protected void initShaderHandles() {
        textureHandle = GLES20.glGetUniformLocation(programHandle, UNIFORM_TEXTURE0);
        positionHandle = GLES20.glGetAttribLocation(programHandle, ATTRIBUTE_POSITION);
        texCoordHandle = GLES20.glGetAttribLocation(programHandle, ATTRIBUTE_TEXCOORD);
        mImageWidthStepLocation = GLES20.glGetUniformLocation(programHandle, "widthStep");
        mImageHeightStepLocation = GLES20.glGetUniformLocation(programHandle, "heightStep");
        mImageSmoothLocation = GLES20.glGetUniformLocation(programHandle, "smoothMode");
    }

    protected void passShaderValues() {
        int index = curRotation;
        renderVertices.position(0);

        float step_x = 1.0f/sourceWidth;
        float step_y = 1.0f/sourceHeight;

        GLES20.glUniform1f(mImageWidthStepLocation, step_x);
        GLES20.glUniform1f(mImageHeightStepLocation, step_y);
        GLES20.glUniform1f(mImageSmoothLocation, mSmoothMode/10.0f);

        index = index%4;
        GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, 8, renderVertices);
        GLES20.glEnableVertexAttribArray(positionHandle);
        textureVerticesCut[index].position(0);
        GLES20.glVertexAttribPointer(texCoordHandle, 2, GLES20.GL_FLOAT, false, 8, textureVerticesCut[index]);
        GLES20.glEnableVertexAttribArray(texCoordHandle);
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture_in);
        GLES20.glUniform1i(textureHandle, 0);
    }

    private void checkZoomIn(int dstWidth, int dstHeight){
        float rate_x = dstWidth*1.0f/sourceWidth;
        float rate_y = dstHeight*1.0f/sourceHeight;
        if (rate_x <= SMOOTH_THRESHOLD && rate_y <= SMOOTH_THRESHOLD) {
            mSmoothMode = SMOOTH_GAUSS;
        } else {
            mSmoothMode = SMOOTH_NONE;
        }
    }

    private void setCutTextureCord(float left, float right, float top, float bottom) {
        if(!isSetTextureVertices) {
            textureVerticesCut = new FloatBuffer[4];
            texData0 = new float[8];
            texData1 = new float[8];
            texData2 = new float[8];
            texData3 = new float[8];
            isSetTextureVertices = true;
        }

        if (isSetTextureVertices) {
            texData0[0] = left;
            texData0[1] = top;
            texData0[2] = right;
            texData0[3] = top;
            texData0[4] = left;
            texData0[5] = bottom;
            texData0[6] = right;
            texData0[7] = bottom;
            textureVerticesCut[0] = ByteBuffer.allocateDirect(texData0.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVerticesCut[0].put(texData0).position(0);

            texData1[0] = left;
            texData1[1] = bottom;
            texData1[2] = left;
            texData1[3] = top;
            texData1[4] = right;
            texData1[5] = bottom;
            texData1[6] = right;
            texData1[7] = top;
            textureVerticesCut[1] = ByteBuffer.allocateDirect(texData1.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVerticesCut[1].put(texData1).position(0);

            texData2[0] = right;
            texData2[1] = bottom;
            texData2[2] = left;
            texData2[3] = bottom;
            texData2[4] = right;
            texData2[5] = top;
            texData2[6] = left;
            texData2[7] = top;
            textureVerticesCut[2] = ByteBuffer.allocateDirect(texData2.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVerticesCut[2].put(texData2).position(0);

            texData3[0] = right;
            texData3[1] = top;
            texData3[2] = right;
            texData3[3] = bottom;
            texData3[4] = left;
            texData3[5] = top;
            texData3[6] = left;
            texData3[7] = bottom;
            textureVerticesCut[3] = ByteBuffer.allocateDirect(texData3.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            textureVerticesCut[3].put(texData3).position(0);
        }
    }

    private void drawCut(float outputScale) {
        int new_w = (int)(sourceHeight / outputScale);
        int new_h = (int)(sourceWidth * outputScale);
        if (new_w < sourceWidth) {
            float diff_x = (sourceWidth - new_w) * 0.5f / sourceWidth;
            float left = diff_x;
            float right = 1.0f - diff_x;
            float top = 0.0f;
            float bottom = 1.0f;
            setCutTextureCord(left, right, top, bottom);
        } else {
            float diff_y = (sourceHeight - new_h) * 0.5f / sourceHeight;
            float left = 0.0f;
            float right = 1.0f;
            float top = diff_y;
            float bottom = 1.0f - diff_y;
            setCutTextureCord(left, right, top, bottom);
        }
    }
}
