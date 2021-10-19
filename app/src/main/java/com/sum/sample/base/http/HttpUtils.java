package com.sum.sample.base.http;

import com.lzy.okgo.OkGo;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujiang
 * Desc:网络请求
 */
public class HttpUtils {
    private Map<String, Object> parameter = new HashMap<>();

    private Object body;

    private HttpUtils() {
    }

    private static HttpUtils httpUtils;

    public static HttpUtils instance() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public void setParameter(String key, String value) {
        parameter.put(key, value);
    }

    public void setParameter(String key, Integer value) {
        parameter.put(key, value);
    }

    public void setParameter(String key, Long value) {
        parameter.put(key, value);
    }

    public void setParameter(String key, Float value) {
        parameter.put(key, value);
    }

    public void setParameter(String key, Boolean value) {
        parameter.put(key, value);
    }

    public void setParameter(String key, File value) {
        parameter.put(key, value);
    }

    public void setBody(Object body){
        this.body = body;
    }

    public void clearParameter() {
        parameter.clear();
        body = null;
    }

    /**
     * get请求
     * @param tag
     * @param url
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T getRequest(Object tag, String url, JsonCallback<T> jsonCallback) {
        setCommonParameter();
        new Okgo().getRequest(tag, url, parameter, jsonCallback);
        clearParameter();
        return null;
    }

    /**
     * post请求
     * @param tag
     * @param url
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T postRequest(Object tag, String url, JsonCallback<T> jsonCallback) {
        setCommonParameter();
        new Okgo().postRequest(tag, url, parameter, body, jsonCallback);
        clearParameter();
        return null;
    }

    /**
     * put请求
     * @param tag
     * @param url
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T putRequest(Object tag, String url, JsonCallback<T> jsonCallback) {
        setCommonParameter();
        new Okgo().putRequest(tag, url, parameter, body, jsonCallback);
        clearParameter();
        return null;
    }

    /**
     * @author liujiang
     * created at: 2021/5/17 17:24
     * Desc: 添加公共参数
     */
    private void setCommonParameter() {

    }

    /**
     * 取消所有网络请求
     */
    public void cancelAll() {
        //取消所有请求
        OkGo.getInstance().cancelAll();
    }

    /**
     * 球销某一个网络请求
     * @param tag
     */
    public void cancelTag(Object tag) {
        //取消所有请求
        OkGo.getInstance().cancelTag(tag);
    }
} 
