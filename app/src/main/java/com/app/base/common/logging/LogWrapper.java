package com.app.base.common.logging;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:39 AM
 */
public interface LogWrapper {
    int d(String tag, String msg);

    int d(String tag, String msg, Throwable tr);

    int v(String tag, String msg);

    int v(String tag, String msg, Throwable tr);

    int w(String tag, String msg);

    int w(String tag, String msg, Throwable tr);

    int i(String tag, String msg);

    int i(String tag, String msg, Throwable tr);

    int e(String tag, String msg);

    int e(String tag, String msg, Throwable tr);
}
