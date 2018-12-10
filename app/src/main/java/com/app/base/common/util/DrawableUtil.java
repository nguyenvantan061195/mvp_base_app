package com.app.base.common.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.TextView;

import com.app.base.global.Constant;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:52 PM
 */
public class DrawableUtil {
    //region Helper method for PreLollipop TextView & Buttons Vector Images
    public static Drawable setVectorForPreLollipop(int resourceId, Context activity) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(activity.getResources(), resourceId, activity.getTheme());
        } else {
            icon = activity.getResources().getDrawable(resourceId, activity.getTheme());
        }

        return icon;
    }


    public static void setVectorForPreLollipop(TextView textView, int resourceId, Context activity, int position) {
        Drawable icon;
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon = VectorDrawableCompat.create(activity.getResources(), resourceId,
                    activity.getTheme());
        } else {
            icon = activity.getResources().getDrawable(resourceId, activity.getTheme());
        }
        switch (position) {
            case Constant.DRAWABLE_LEFT:
                textView.setCompoundDrawablesWithIntrinsicBounds(icon, null, null,
                        null);
                break;

            case Constant.DRAWABLE_RIGHT:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon,
                        null);
                break;

            case Constant.DRAWABLE_TOP:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, icon, null,
                        null);
                break;

            case Constant.DRAWABLE_BOTTOM:
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                        icon);
                break;
        }
    }

//endregion
}

