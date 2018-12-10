package com.app.base.common.anim;

import android.view.View;
/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public abstract class Animation {

    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_UP = 3;
    public static final int DIRECTION_DOWN = 4;
    public static final int DIRECTION_UP_WITH_HIDDEN = 5;

    public static final int DURATION_DEFAULT = 300; //300ms
    public static final int DURATION_SHORT = 100; //100ms
    public static final int DURATION_LONG = 500; //500ms

    public static final int INFINITY = -1;


    View view;
    Animation animation;

    int duration;

    public abstract void startAnim();

    public abstract void stopAnim();

    public Animation SetDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
