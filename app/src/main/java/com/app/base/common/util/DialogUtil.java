package com.app.base.common.util;

import android.app.Dialog;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.app.base.common.base.BaseActivity;
import com.app.base.common.widget.dialog.LoadingDialog;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:33 AM
 */
public class DialogUtil {
    private static Dialog mCurrentDialog;
    private static final String DIALOG_TAG = "DIALOG_TAG";
    private static final String TAG = DialogUtil.class.getSimpleName();

    private static DialogFragment getCurrentDialog(BaseActivity activity) {
        return (DialogFragment) activity.getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);
    }

    private static Dialog getCurrentDialog() {
        return mCurrentDialog;
    }

    /**
     * close current dialog
     */
    public static void closeCurrentDialog(BaseActivity activity) {
        DialogFragment prev = getCurrentDialog(activity);
        if (prev != null && prev.getShowsDialog()) {
            prev.dismissAllowingStateLoss();
        }
        if (mCurrentDialog != null && mCurrentDialog.isShowing()) {
            try {
                mCurrentDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void executePendingTransactions(BaseActivity context) {
        try {
            context.getSupportFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            LogUtil.w(TAG, e.getMessage());
        }
    }

    /**
     * show progress dialog
     *
     * @param isCancelable
     * @author imt-vantan
     */
    public static void showLoadingDialog(BaseActivity activity, boolean isCancelable) {
        closeCurrentDialog(activity);
        if (!activity.isAllowShowFragmentDialog) {
            return;
        }
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setCancelable(isCancelable);
        loadingDialog.show(activity.getSupportFragmentManager(), DIALOG_TAG);
        executePendingTransactions(activity);
    }
}
