package com.sum.sample.base.utils;

/**
 * @author liujiang
 * Desc: 网络请求地址
 */
public class Config {
    public static final String BASE_SERVICE = "http://202.111.6.72:8981/";    //开发环境

    public static final String SERVER_HOME = BASE_SERVICE + "e-car/";

    /**
     * 登录接口
     */
    public static final String LOGIN = SERVER_HOME + "sys/userLogin";
} 
