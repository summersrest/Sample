package com.sum.sample.base.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.sum.sample.base.App;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @author liujiang
 * Desc: sharepreferece
 */
public class SPUtils {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

    private static final int MODE_PRIVATE = ContextWrapper.MODE_PRIVATE;

    /**
     * 设置boolen类型的全局参数
     *
     * @param key
     * @param value
     */
    public static void setBoolen(String key, boolean value) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 根据key值获取boolean参数的value
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key, boolean def) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getBoolean(key, def);

    }

    /**
     * 设置int类型的全局参数
     *
     * @param key
     * @param value
     */
    public static void setInt(String key, int value) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 根据key值获取int参数的value
     *
     * @param key
     * @return
     */
    public static int getInt(String key, int def) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getInt(key, def);
    }

    /**
     * 设置String类型的全局参数
     *
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 设置String类型的全局参数
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }


    /**
     * 根据key值获取String参数的value
     *
     * @param key
     * @return
     */
    public static String getString(String key, String def) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getString(key, def);
    }

    /**
     * 根据key值获取String参数的value
     * @param context
     * @param key
     * @param def
     * @return
     */
    public static String getString(Context context, String key, String def) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getString(key, def);
    }

    /**
     * 设置float类型的全局参数
     *
     * @param key
     * @param value
     */
    public static void setFloat(String key, float value) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * 根据key值获取float参数的value
     *
     * @param key
     * @return
     */
    public static float getFloat(String key, float def) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getFloat(key, def);
    }

    /**
     * 设置long类型的全局参数
     *
     * @param key
     * @param value
     */
    public static void setLong(String key, long value) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    /**
     * 根据key值获取long参数的value
     *
     * @param key
     * @return
     */
    public static long getLong(String key, long def) {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return sp.getLong(key, def);
    }

    /**
     * 清空全部信息
     *
     * @author LiuJiang
     */
    public static void clear() {
        SharedPreferences sp = App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sp.edit().clear().apply();
    }

//    /**
//     * 存储个人信息
//     *
//     * @author LiuJiang
//     */
//    public static void setUserInfo(UserBean userBean) {
//        setObject(userBean, "USER", "MEMEBER");
//    }
//
//    /**
//     * 清除个人信息
//     *
//     * @author LiuJiang
//     */
//    public static void clearUserInfo() {
//        SharedPreferences.Editor editor = App.instance().getSharedPreferences("USER", 0).edit();
//        editor.clear().commit();
//    }
//
//    /**
//     * 读取个人信息
//     *
//     * @author LiuJiang
//     */
//    public static UserBean getUserInfo(AppCompatActivity activity) {
//        UserBean bean = getObject("USER", "MEMEBER");
//        if (null == bean) {
//            //用户信息为空，重新登录
//            Intent intent = new Intent(activity, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            activity.startActivity(intent);
//            activity.finish();
//        }
//        return bean;
//    }

    private static <T> void setObject(T obj, String Ukey, String key) {
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(obj);
            // 将字节流编码成base64的字符窜

            String value = new String(Base64.encodeBase64(baos.toByteArray()));
            SharedPreferences.Editor editor = App.instance().getSharedPreferences(Ukey, 0).edit();
            editor.putString(key, value);
            editor.apply();
        } catch (IOException e) {

        }
    }

    private static <T> T getObject(String Ukey, String key) {
        T obj = null;
        String productBase64 = App.instance().getSharedPreferences(Ukey, 0).getString(key, null);
        if (productBase64 == null) {
            return null;
        }
        // 读取字节
        byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
        // 封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            // 再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            try {
                // 读取对象
                Object o = bis.readObject();
                obj = (T) o;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
} 
