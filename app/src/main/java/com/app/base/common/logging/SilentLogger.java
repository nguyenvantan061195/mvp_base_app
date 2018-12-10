package com.app.base.common.logging;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:14 AM
 */
public class SilentLogger implements LogWrapper {
    @Override
    public int d(String tag, String msg) {
        return 0;
    }

    @Override
    public int d(String tag, String msg, Throwable tr) {
        return 0;
    }

    @Override
    public int v(String tag, String msg) {
        return 0;
    }

    @Override
    public int v(String tag, String msg, Throwable tr) {
        return 0;
    }

    @Override
    public int w(String tag, String msg) {
        return 0;
    }

    @Override
    public int w(String tag, String msg, Throwable tr) {
        return 0;
    }

    @Override
    public int i(String tag, String msg) {
        return 0;
    }

    @Override
    public int i(String tag, String msg, Throwable tr) {
        return 0;
    }

    @Override
    public int e(String tag, String msg) {
        return 0;
    }

    @Override
    public int e(String tag, String msg, Throwable tr) {
        return 0;
    }
}
