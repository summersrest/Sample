package com.sum.generatorlib.utils;

/**
 * @author liujiang
 * created at: 2021/10/20 11:26
 * Desc:
 */
public class ToolUtils {
    /**
     * 将字符串的首字母转大写
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static String capitalFirst(String str) {
        //判断首字母是否大写
        if (isUppercase(str)) {
            return str;
        }
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 将字符串的首字母转小写
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static String lowerFirst(String str) {
        //判断首字母是否大写
        if (!isUppercase(str)) {
            return str;
        }
        char[] cs = str.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);
    }

    /**
     * 判断首字母是否大写
     *
     * @param str
     * @return
     */
    private static boolean isUppercase(String str) {
        char c = str.charAt(0);
        return Character.isUpperCase(c);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str) || str.length() <= 0;
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     *
     * @param param
     * @return
     */
    private static final char UNDERLINE = '_';
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * 获取文件顶部导包路径
     * @param basePackage
     * @param appPath
     * @param path
     * @param filePath
     * @return
     */
    public static String getImportPackage(String basePackage, String appPath, String path, String filePath) {
        //com.sum.demo
        String packageImport = basePackage;
        //com.sum.demo.application
        if (!ToolUtils.isEmpty(appPath)) {
            packageImport = packageImport + "." + appPath;
        }
        //com.sum.demo.application.order.history
        if (!ToolUtils.isEmpty(path)) {
            packageImport = packageImport + "." + path;
        }
        //com.sum.demo.application.order.history.activity
        if (!ToolUtils.isEmpty(filePath)) {
            packageImport = packageImport + "." + filePath;
        }
        return packageImport;
    }

    /**
     * 获取文件路径
     *
     * @param basePackage   包名
     * @param path          模块路径
     * @param appPath       应用代码路径
     * @param filePath      文件目录
     * @return
     */
    public static String getFilePath(String basePackage, String appPath, String path, String filePath) {
        //module当前目录(D:\android\workplace\TempTest)
        String modulePath = System.getProperty("user.dir");
        //目录拼接
        modulePath = modulePath + "\\app\\src\\main\\java\\";
        //拼接包名
        modulePath = modulePath + basePackage.replace(".", "\\");
        //拼接应用代码路径
        if (!isEmpty(appPath)) {
            modulePath = modulePath + "\\" + appPath.replace(".", "\\");
        }
        //拼接模块目录
        if (!isEmpty(path)) {
            modulePath = modulePath + "\\" + path.replace(".", "\\");
        }
        //拼接文件目录（activity，presenter，view，model 文件夹）
        if (!isEmpty(filePath)) {
            modulePath = modulePath + "\\" + filePath.replace(".", "\\");
        }
        return modulePath;
    }
}
