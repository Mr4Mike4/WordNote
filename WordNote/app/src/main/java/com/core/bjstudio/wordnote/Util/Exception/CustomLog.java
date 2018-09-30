package com.core.bjstudio.wordnote.Util.Exception;

import android.util.Log;

import com.core.bjstudio.wordnote.Android.BJStudioApplication;

public class CustomLog {

    private static final String TAG = "Bjstudio";

    /**
     * Log Level Error
     **/
    public static final void error(String message) {
        if (BJStudioApplication.isDebug) {
            Log.e(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Warning
     **/
    public static final void warn(String message) {
        if (BJStudioApplication.isDebug) {
            Log.w(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Information
     **/
    public static final void info(String message) {
        if (BJStudioApplication.isDebug) {
            Log.i(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Debug
     **/
    public static final void debug(String message) {
        if (BJStudioApplication.isDebug) {
            Log.d(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Verbose
     **/
    public static final void verbose(String message) {
        if (BJStudioApplication.isDebug) {
            Log.v(TAG, buildLogMsg(message));
        }
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();

    }


}
