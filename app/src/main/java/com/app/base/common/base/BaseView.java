package com.app.base.common.base;

import android.content.Context;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 3:05 PM
 */
public interface BaseView {
    byte LOADING_OFF = 0;
    byte LOADING_ON = 1;

    void showError(Throwable err);

    /**
     * show long task loading view
     *
     * @param loadingType 0: hide loading view, 1: show loading more view, 2: show refresh loading view
     */
    void showLoading(byte loadingType);

    void hideKeyboard();

    Context getContext();
}
