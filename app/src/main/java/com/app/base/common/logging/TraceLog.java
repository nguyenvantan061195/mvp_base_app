package com.app.base.common.logging;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:27 AM
 */
public class TraceLog implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler unExceptionHandler;

    public TraceLog() {
        this.unExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        StackTraceElement[] arr = e.getStackTrace();
        StringBuilder report = new StringBuilder(e.toString() + "\n\n");
        report.append("--------- Stack trace ---------\n\n");
        for (StackTraceElement anArr : arr) {
            report.append("    ").append(anArr.toString()).append("\n");
        }
        report.append("-------------------------------\n\n");

        // If the exception was thrown in a background thread inside
        // AsyncTask, then the actual exception can be found with getCause
        report.append("--------- Cause ---------\n\n");
        Throwable cause = e.getCause();
        if (cause != null) {
            report.append(cause.toString()).append("\n\n");
            arr = cause.getStackTrace();
            for (StackTraceElement anArr : arr) {
                report.append("    ").append(anArr.toString()).append("\n");
            }
        }
        report.append("-------------------------------\n\n");

        // send log to server - Handle log after here

        unExceptionHandler.uncaughtException(t, e);
    }

    /**
     * trace Throwable to String Log
     *
     * @param e
     * @return
     */
    public static String getReportFromThrowable(Throwable e) {
        StackTraceElement[] arr = e.getStackTrace();
        StringBuilder report = new StringBuilder(e.toString() + "\n\n");
        report.append("--------- Stack trace ---------\n\n");
        for (StackTraceElement anArr : arr) {
            report.append("    ").append(anArr.toString()).append("\n");
        }
        // If the exception was thrown in a background thread inside
        // AsyncTask, then the actual exception can be found with getCause
        report.append("--------- Cause ---------\n\n");
        Throwable cause = e.getCause();
        if (cause != null) {
            report.append(cause.toString()).append("\n\n");
            arr = cause.getStackTrace();
            for (StackTraceElement anArr : arr) {
                report.append("    ").append(anArr.toString()).append("\n");
            }
        }
        return report.toString();
    }
}
