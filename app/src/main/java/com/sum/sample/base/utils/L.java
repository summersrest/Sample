package com.sum.sample.base.utils;

import android.util.Log;

/**
 * @author liujiang
 * Desc:日志打印工具类
 */
public class L {
    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "Green";
    // 下面四个是默认tag的函数
    public static void showI(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void showD(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void showE(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void showV(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }


    // 下面是传入自定义tag的函数
    public static void CusI(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void CusD(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void CusE(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void CusV(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }
} 
