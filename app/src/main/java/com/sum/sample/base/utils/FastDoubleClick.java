package com.sum.sample.base.utils;

/**
 * @author liujiang
 * created at: 2021/9/8 9:12
 * Desc: 防止重复点击
 */
public class FastDoubleClick {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
} 
