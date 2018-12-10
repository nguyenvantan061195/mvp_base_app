package com.app.base.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:04 AM
 */
public class PermissionUtil {
    public static final int PERMISSION_REQUEST_CODE = 1;
    public static final int PERMISSION_REQUEST_CODE_SINGLE = 2;
    private static final int ANDROID_MARSHMALLOW_API_LEVEL = Build.VERSION_CODES.M;
    private static int numberOfRequest = 0;
    /*Define App Permission*/
    private static final String[] REQUIRED_APP_PERMISSION = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    /**
     * Request all permission
     *
     * @param activity
     */
    public static void requestAllPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= ANDROID_MARSHMALLOW_API_LEVEL) {
            ActivityCompat.requestPermissions(activity, REQUIRED_APP_PERMISSION, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Request 1 permission
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean checkAndRequestPermission(Activity activity, String permission) {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT >= ANDROID_MARSHMALLOW_API_LEVEL && ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;

            if (showExplanation(activity, permission)) {
                // hien thi thong tin request permission

            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{permission}, PERMISSION_REQUEST_CODE_SINGLE);
            }
        }
        return isGranted;
    }

    private static boolean showExplanation(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static boolean checkPermission(Activity activity, String permission) {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT >= ANDROID_MARSHMALLOW_API_LEVEL && ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            isGranted = false;
            showExplanation(activity, permission);
        }
        return isGranted;
    }

    /**
     * Receive permission and process it
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                boolean permissionGranted = true;
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result == PackageManager.PERMISSION_DENIED) {
                            permissionGranted = false;
                            break;
                        }
                    }
                }

                if (permissionGranted) {
                    // neu da cap het quyen thi khong lam gi ca
                } else if (numberOfRequest <= 1) {
                    numberOfRequest++;
                    // neu khong cap quyen thi request lai
                    requestAllPermission(activity);
                } else {
                    numberOfRequest = 0;
                    // thong bao neu khong cap quyen du ung dung se khong the thuc hien chuc nang cu the
                    // DialogUtils.showMessageDialog(StringUtil.getString(R.string.TEXT_NOT_ENOUGH_PERMISSION));
                }
                break;
            }
        }
    }
}
