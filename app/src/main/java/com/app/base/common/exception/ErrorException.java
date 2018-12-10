package com.app.base.common.exception;

import java.io.IOException;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 5:10 PM
 */
public class ErrorException extends IOException {
    private int code;

    public ErrorException() {
        super();
        code = 0;
    }

    public ErrorException(String message) {
        super(message);
        this.code = 0;
    }

    public ErrorException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorException(Throwable cause) {
        super(cause);
        this.code = 0;
    }

    public ErrorException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
