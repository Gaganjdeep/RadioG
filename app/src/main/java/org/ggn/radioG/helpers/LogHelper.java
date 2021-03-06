package org.ggn.radioG.helpers;

import android.support.v4.BuildConfig;
import android.util.Log;


/**
 * LogHelper class
 */
public final class LogHelper
{

    private final static boolean mTesting = false;

    public static void d(final String tag, String message)
    {
        // include logging only in debug versions
        if (BuildConfig.DEBUG || mTesting)
        {
            Log.d(tag, message);
        }
    }


    public static void v(final String tag, String message)
    {
        // include logging only in debug versions
        if (BuildConfig.DEBUG || mTesting)
        {
            Log.v(tag, message);
        }
    }


    public static void e(final String tag, String message)
    {
        Log.e(tag, message);
    }


    public static void i(final String tag, String message)
    {
        Log.i(tag, message);
    }


    public static void w(final String tag, String message)
    {
        Log.w(tag, message);
    }

}
