package com.app.base.common.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.app.base.R;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 2:57 PM
 */
public class BaseDialog {
    private Dialog dialog;
    private TextView dialogButtonOk, dialogButtonNo;
    private TextView tvTitle, tvMessage;
    private View separator;
    private BaseDialogClickListener positiveListener;
    private BaseDialogClickListener negativeListener;
    private boolean negativeExist;
    private Object data;

    public BaseDialog(Context context, String title, String subtitle, boolean bold, Typeface typeFace, boolean cancelable) {
        negativeExist = false;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_confirm);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initViews();
        dialog.setCancelable(cancelable);
        setTitle(title);
        setSubtitle(subtitle);
        setBoldPositiveLabel(bold);
        setTypefaces(typeFace);
        initEvents();
    }

    public void setPositive(String okLabel, BaseDialogClickListener listener) {
        this.positiveListener = listener;
        setPositiveLabel(okLabel);
    }

    public void setNegative(String koLabel, BaseDialogClickListener listener) {
        if (listener != null) {
            this.negativeListener = listener;
            negativeExist = true;
            setNegativeLabel(koLabel);
        }
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public void show() {
        dialogButtonNo.setVisibility(View.GONE);
        separator.setVisibility(View.GONE);
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setSubtitle(String subtitle) {
        tvMessage.setText(subtitle);
    }

    private void setPositiveLabel(String positive) {
        dialogButtonOk.setText(positive);
    }

    private void setNegativeLabel(String negative) {
        dialogButtonNo.setText(negative);
    }

    private void setBoldPositiveLabel(boolean bold) {
        if (bold)
            dialogButtonOk.setTypeface(null, Typeface.BOLD);
        else
            dialogButtonOk.setTypeface(null, Typeface.NORMAL);
    }

    private void setTypefaces(Typeface appleFont) {
        if (appleFont != null) {
            tvTitle.setTypeface(appleFont);
            tvMessage.setTypeface(appleFont);
            dialogButtonOk.setTypeface(appleFont);
            dialogButtonNo.setTypeface(appleFont);
        }
    }

    public void setData(Object data) {
        this.data = data;
    }

    private void initViews() {
        tvTitle = dialog.findViewById(R.id.dialog_title);
        tvMessage = dialog.findViewById(R.id.subtitle);
        dialogButtonOk = dialog.findViewById(R.id.dialogButtonOK);
        dialogButtonNo = dialog.findViewById(R.id.dialogButtonNO);
        separator = dialog.findViewById(R.id.separator);
    }

    private void initEvents() {
        dialogButtonOk.setOnClickListener(view -> {
            if (positiveListener != null) {
                positiveListener.onClick(BaseDialog.this, data);
            }
        });
        dialogButtonNo.setOnClickListener(view -> {
            if (negativeListener != null) {
                negativeListener.onClick(BaseDialog.this, data);
            }
        });
    }

    public interface BaseDialogClickListener {
        void onClick(BaseDialog dialog, Object data);
    }
}
