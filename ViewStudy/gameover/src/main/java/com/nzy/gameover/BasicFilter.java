package com.nzy.gameover;

public abstract class BasicFilter extends GLTextureOutputRenderer implements GLTextureInputRenderer {

    public BasicFilter parentFilter;

    /* (non-Javadoc)
     * @see project.android.imageprocessing.output.GLTextureInputRenderer#newTextureReady(int, project.android.imageprocessing.input.GLTextureOutputRenderer)
     */
    @Override
    public void newTextureReady(int texture, GLTextureOutputRenderer source, boolean newData) {
        if (newData) {
            markAsDirty();
        }
        texture_in = texture;
        setWidth(source.getWidth());
        setHeight(source.getHeight());
        onDrawFrame();
        source.unlockRenderBuffer();
    }

}