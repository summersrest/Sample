package com.sum.sample.base.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * @author liujiang
 * Desc:Date工具类
 */
public class DateUtils {
    /**
     * 得到当前时间戳
     *
     * @return yyyy-MM-dd HH:mm:ss格式的当前时间
     */
    public static long getCurrentStamps() {
        return new Date().getTime();
    }

    /**
     * 得到当前日期
     *
     * @return yyyy-MM-dd格式的当前时间
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(new Date());
    }

    /**
     * 时间转字符串(yyyy-MM)
     *
     * @param date
     * @return
     */
    public static String date2StringYM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 时间转字符串(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String date2StringYMD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 时间转字符串(yyyy-MM-dd HH)
     *
     * @param date
     * @return
     */
    public static String date2StringYMDH(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 时间转字符串(yyyy-MM-dd HH:mm)
     *
     * @param date
     * @return
     */
    public static String date2StringYMDHM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 时间转字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 字符串转Calendar
     *
     * @param dateStr
     * @return
     */
    public static Calendar string2Calendar(String dateStr) {
        if (TextUtils.isEmpty(dateStr)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            return calendar;
        }
        switch (dateStr.length()) {
            case 4:
                dateStr = dateStr + "-01-01 00:00:00";
                break;
            case 7:
                dateStr = dateStr + "-01 00:00:00";
                break;
            case 10:
                dateStr = dateStr + " 00:00:00";
                break;
            case 13:
                dateStr = dateStr + ":00:00";
                break;
            case 16:
                dateStr = dateStr + ":00";
                break;
            default:
                break;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(date));
        return calendar;
    }
} 
