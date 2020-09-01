package com.nzy.gameover;

public class DesignBounceInterpolator extends OvershootBounceInterpolator {
    public DesignBounceInterpolator(float designOvershoot, float designBounce, float designFriction) {
        super(designOvershoot * 0.02962f, designBounce / 20f, designFriction / 20f, 0.16666f);
    }

    public DesignBounceInterpolator() {
        super();
    }
}