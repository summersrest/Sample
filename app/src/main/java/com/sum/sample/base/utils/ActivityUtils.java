package com.sum.sample.base.utils;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

/**
 * @author liujiang
 * Desc: Activity工具类
 */
public class ActivityUtils {
    /**
     * 启动Activity
     *
     * @param activity 当前Activity
     * @param cls      要启动的Activity Class
     */
    public static void startActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动一个Activity并关闭当前Activity
     * @param activity
     * @param cls
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     * 启动activity并传递int类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, int data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递int类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, int data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递String类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, String data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递String类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, String data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递long类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, long data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递long类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, long data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递float类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, float data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递float类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, float data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递double类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, double data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递double类型数据并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, double data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递序列化对象Serializable
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivity(Activity activity, Class<?> cls, Serializable data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动activity传递序列化对象Serializable并且关闭当前activity
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityAndFinish(Activity activity, Class<?> cls, Serializable data1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        activity.startActivity(intent);
        activity.finish();
        startActivityAnim(activity);
    }

    /**
     * 启动Activity
     * @param activity
     * @param cls
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     * 启动activity并传递int类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, int data1, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     * 启动activity并传递long类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, long data1, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     * 启动activity并传递float类型数据
     * @param activity
     * @param cls
     * @param data1
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, float data1, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递double类型数据
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, double data1, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     *  启动activity并传递序列化对象Serializable
     * @param activity
     * @param cls
     * @param data1
     */
    public static void startActivityForResult(Activity activity, Class<?> cls, Serializable data1, int requestCode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra("data1", data1);
        intent.setFlags(requestCode);
        activity.startActivityForResult(intent, requestCode);
        startActivityAnim(activity);
    }

    /**
     * 启动一个Activity并关清空所有Activity
     *
     * @param activity 当前Activity
     * @param cls      要启动的Activity
     */
    public static void startActivityAndClear(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        startActivityAnim(activity);
    }

    /**
     * 启动Activity动画
     *
     * @param activity
     */
    public static void startActivityAnim(Activity activity) {
//        activity.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
} 
