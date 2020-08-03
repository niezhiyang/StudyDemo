package com.nzy.viewstudy.skin;

/**
 * @author niezhiyang
 * since 2020/7/29
 */
public class SkinResources {

    private static class SkinResourcesInstance{
        public static SkinResources mSkinResources = new SkinResources();
    }

    public SkinResources getInstance(){
        return SkinResourcesInstance.mSkinResources;
    }

    /**
     * tong
     */
}
