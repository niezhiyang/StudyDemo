package com.nzy.gameover;

public interface IEffectProcessingPipeline {
    void startRendering(Object var1);

    void addRootRenderer(GLRenderer var1);

    EffectSurfaceRender getRootRender();

    EffectSurfaceRender getRenderByFilter(GLRenderer var1);
}