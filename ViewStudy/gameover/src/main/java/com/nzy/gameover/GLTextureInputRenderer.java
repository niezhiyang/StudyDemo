package com.nzy.gameover;

public interface GLTextureInputRenderer {
    /**
     * Signals that a new texture is available and the image should be reprocessed.
     *
     * @param texture The texture id to be used as input.
     * @param source  The GLTextureOutputRenderer which produced the texture.
     */
    public void newTextureReady(int texture, GLTextureOutputRenderer source, boolean newData);
}