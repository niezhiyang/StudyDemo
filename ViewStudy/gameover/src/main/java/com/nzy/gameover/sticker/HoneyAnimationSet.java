package com.nzy.gameover.sticker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HoneyAnimationSet {
    private List<HoneyAnimation> mList = new ArrayList();

    public HoneyAnimationSet() {
    }

    public void addAnimation(HoneyAnimation animation) {
        this.mList.add(animation);
    }

    public void setProgress(float progress) {
        Iterator var2 = this.mList.iterator();

        while(var2.hasNext()) {
            HoneyAnimation animation = (HoneyAnimation)var2.next();
            animation.setValue(progress);
        }

    }
}