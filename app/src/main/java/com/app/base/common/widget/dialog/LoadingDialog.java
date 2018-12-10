package com.app.base.common.widget.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.app.base.R;
import com.app.base.common.anim.Animation;
import com.app.base.common.anim.RotateAnimation;
import com.app.base.common.base.BaseDialogFragment;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 2:47 PM
 */
public class LoadingDialog extends BaseDialogFragment implements View.OnTouchListener {

    private ImageView mIndicator;
    private Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        mIndicator = dialog.getWindow().findViewById(R.id.image_indicator);
        dialog.setOnShowListener(dialog -> new RotateAnimation(mIndicator).SetDuration(Animation.DURATION_LONG).startAnim());
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
