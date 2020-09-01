package com.nzy.gameover;

import android.opengl.GLES20;

import java.util.ArrayList;
import java.util.List;

public abstract class GLTextureOutputRenderer extends GLRenderer {
//	protected int[] frameBuffer;
//	protected int[] texture_out;
//	protected int[] depthRenderBuffer;

    protected GLFrameBuffer glFrameBuffer;

    protected List<GLTextureInputRenderer> targets;
    protected Object listLock = new Object();

    protected boolean dirty;
    private String filterKey;
    private boolean isFloatTexture = false;

    /**
     * Creates a GLTextureOutputRenderer which initially has an empty list of targets.
     */
    public GLTextureOutputRenderer() {
        super();
        targets = new ArrayList<GLTextureInputRenderer>();
        filterKey = toString();
    }

    /**
     * Adds the given target to the list of targets that this renderer sends its output to.
     *
     * @param target The target which should be added to the list of targets that this renderer sends its output to.
     */
    public synchronized void addTarget(GLTextureInputRenderer target) {
        synchronized (listLock) {

            if (targets != null && targets.contains(target)) {
                return;
            }

            targets.add(target);
        }
    }

    /* (non-Javadoc)
     * @see project.android.imageprocessing.GLRenderer#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        if (null != glFrameBuffer) {
            glFrameBuffer.destoryBuffer();
            glFrameBuffer = null;
        }
    }

    @Override
    public void releaseFrameBuffer() {
        super.releaseFrameBuffer();
        if (null != glFrameBuffer) {
            glFrameBuffer.destoryBuffer();
            glFrameBuffer = null;
        }
    }

    @Override
    protected void drawFrame() {
        long startTime = System.currentTimeMillis();
        if (glFrameBuffer == null) {
            if (getWidth() != 0 && getHeight() != 0) {
                initFBO();
            } else {
                return;
            }
        }
        boolean newData = false;
        if ((glFrameBuffer != null) && glFrameBuffer.getFrameBuffer() == null) {
            if (getWidth() != 0 && getHeight() != 0) {
                initFBO();
            } else {
                return;
            }
        }
        if (dirty) {
            newData = true;
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, glFrameBuffer.getFrameBuffer()[0]);
            GLES20.glClearColor(getBackgroundRed(), getBackgroundGreen(), getBackgroundBlue(), getBackgroundAlpha());
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            drawSub();
            GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
        }
        long drawUseTime = System.currentTimeMillis();
        synchronized (listLock) {
            for (GLTextureInputRenderer target : targets) {
                if(target != null && glFrameBuffer != null)
                {
                    target.newTextureReady(glFrameBuffer.getTexture_out()[0], this, newData);
                }
            }
        }
    }

    public void drawSub() {
        drawIndeed();
    }

    private void drawIndeed() {
        super.drawFrame();
    }

//	//private void drawIndeed(){
//		super.drawFrame();
//	}

    /**
     * Returns the object used to lock the target list.  Iterating over or changing the target list
     * should be done in a synchronized block that is locked using the object return.
     *
     * @return lock
     * the object which is used to lock the target list
     */
    public Object getLockObject() {
        return listLock;
    }

    /**
     * Returns a list of all the targets that this renderer should send its output to.  Iterating over or changing this
     * list should be done in a synchronized block, locked using the object returned from getLockObject().
     *
     * @return targets
     */
    public List<GLTextureInputRenderer> getTargets() {
        return targets;
    }

    @Override
    protected void handleSizeChange() {
        initFBO();
    }

    public void setFloatTexture(boolean floatTexture) {
        isFloatTexture = floatTexture;
    }

    protected void initFBO() {
//		if (null != glFrameBuffer){
//			glFrameBuffer.destoryBuffer();
//
//		}
        if (null != glFrameBuffer) {
            //GLRenderBufferCache.getInstance().removeFrameBufferBySize(glFrameBuffer.getBufferWidth(), glFrameBuffer.getBufferHigh());
            glFrameBuffer.destoryBuffer();
        }
        glFrameBuffer = new GLFrameBuffer(getWidth(), getHeight());
        glFrameBuffer.setFloat(isFloatTexture);
        glFrameBuffer.activityFrameBuffer(getWidth(), getHeight());
        int status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
        if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
            if (isFloatTexture) {
                if (null != glFrameBuffer) {
                    //GLRenderBufferCache.getInstance().removeFrameBufferBySize(glFrameBuffer.getBufferWidth(), glFrameBuffer.getBufferHigh());
                    glFrameBuffer.destoryBuffer();
                }
                glFrameBuffer = new GLFrameBuffer(getWidth(), getHeight());
                isFloatTexture = false;
                glFrameBuffer.setFloat(false);
                glFrameBuffer.activityFrameBuffer(getWidth(), getHeight());
                status = GLES20.glCheckFramebufferStatus(GLES20.GL_FRAMEBUFFER);
                if (status != GLES20.GL_FRAMEBUFFER_COMPLETE) {
                    throw new RuntimeException(this + ": Failed to set up render buffer with status " + status + " and error " + GLES20.glGetError());
                }
            } else {
                throw new RuntimeException(this + ": Failed to set up render buffer with status " + status + " and error " + GLES20.glGetError());
            }
        }
        //long start = System.currentTimeMillis();
    }

    /**
     * Removes the given target from the list of targets that this renderer sends its output to.
     *
     * @param target The target which should be removed from the list of targets that this renderer sends its output to.
     */
    public void removeTarget(GLTextureInputRenderer target) {
        synchronized (listLock) {
            targets.remove(target);
        }
    }

    public void clearTarget() {
        synchronized (listLock) {
            targets.clear();
            if (null != glFrameBuffer) {
                glFrameBuffer.destoryBuffer();
                glFrameBuffer = null;
            }
        }
    }

    public void markAsDirty() {
        dirty = true;
    }

    public void unlockRenderBuffer() {
        if (null != glFrameBuffer) {
            glFrameBuffer.unlock();
        }
    }

    public void lockRenderBuffer() {
        if (null != glFrameBuffer) {
            glFrameBuffer.lock();
        }
    }

    public int getTextOutID() {
        if(glFrameBuffer != null)
            return glFrameBuffer.getTexture_out()[0];
        else return 0;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }
}
