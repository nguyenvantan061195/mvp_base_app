package com.app.base.common.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.app.base.R;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 4:01 PM
 */
public class SnackbarUtil {

    public static void showLong(Activity activity, int messageId) {
        SnackbarUtil.show(toView(activity), messageId, Snackbar.LENGTH_LONG);
    }

    public static void showLong(Activity activity, String message) {
        SnackbarUtil.show(toView(activity), message, Snackbar.LENGTH_LONG);
    }

    public static void showLong(View view, int messageId) {
        SnackbarUtil.show(view, messageId, Snackbar.LENGTH_LONG);
    }

    public static void showLong(View view, String message) {
        SnackbarUtil.show(view, message, Snackbar.LENGTH_LONG);
    }

    public static void showShort(Activity activity, int messageId) {
        SnackbarUtil.show(toView(activity), messageId, Snackbar.LENGTH_SHORT);
    }

    public static void showShort(Activity activity, String message) {
        SnackbarUtil.show(toView(activity), message, Snackbar.LENGTH_SHORT);
    }

    public static void showShort(View view, int messageId) {
        SnackbarUtil.show(view, messageId, Snackbar.LENGTH_SHORT);
    }

    public static void showShort(View view, String message) {
        SnackbarUtil.show(view, message, Snackbar.LENGTH_SHORT);
    }

    private static void show(View view, int messageId, int duration) {
        Snackbar.make(view, messageId, duration).show();
    }

    private static void show(View view, String message, int duration) {
        Snackbar.make(view, message, duration).show();
    }

    private static View toView(Activity activity) {
        View view = null;
        try {
            view = activity.findViewById(R.id.coordinator);
            if (view == null) {
                view = activity.findViewById(android.R.id.content);
            }
        } catch (Exception ignored) {
        }
        return view;
    }
}