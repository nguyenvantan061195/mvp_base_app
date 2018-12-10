package com.app.base.common.anim;

import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public class RotateAnimation extends Animation {

    private int repeatCount = -1;
    private android.view.animation.RotateAnimation rotate;
    private int m_EndDegree = 360;
    private int m_StartDegree = 0;

    private boolean isCCW;

    public RotateAnimation setCCW(boolean isCCW) {
        this.isCCW = isCCW;
        return this;
    }

    public RotateAnimation setStartRotate(int _sDegree) {
        m_StartDegree = _sDegree;
        return this;
    }

    public RotateAnimation setEndRotate(int _sDegree) {
        m_EndDegree = _sDegree;
        return this;
    }

    public RotateAnimation(View view) {
        this.view = view;
        this.isCCW = false;
        duration = DURATION_DEFAULT;

    }

    public RotateAnimation SetRepeatCount(int count) {
        this.repeatCount = count;
        return this;
    }

    @Override
    public void startAnim() {
        if (isCCW) {
            rotate = new android.view.animation.RotateAnimation(m_StartDegree, -m_EndDegree, view.getWidth() / 2,
                    view.getHeight() / 2);
        } else {
            rotate = new android.view.animation.RotateAnimation(m_StartDegree, m_EndDegree, view.getWidth() / 2,
                    view.getHeight() / 2);
        }
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(duration);
        rotate.setRepeatCount(repeatCount);
        AnimationSet as = new AnimationSet(false);
        as.setFillAfter(true);
        as.addAnimation(rotate);
        view.startAnimation(as);
    }

    @Override
    public void stopAnim() {
        view.clearAnimation();
    }

}
