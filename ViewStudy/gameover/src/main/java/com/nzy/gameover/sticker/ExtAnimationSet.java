package com.nzy.gameover.sticker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtAnimationSet {
    private List<ExtAnimation> mList = new ArrayList();

    public ExtAnimationSet() {
    }

    public void addAnimation(ExtAnimation animation) {
        this.mList.add(animation);
    }

    public void animateBasedOnTime(long time) {
        Iterator var3 = this.mList.iterator();

        while(var3.hasNext()) {
            ExtAnimation animation = (ExtAnimation)var3.next();
            animation.animateBasedOnTime(time);
        }

    }

    public void start() {
        Iterator var1 = this.mList.iterator();

        while(var1.hasNext()) {
            ExtAnimation animation = (ExtAnimation)var1.next();
            animation.start();
        }

    }
}
