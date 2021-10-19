package com.sum.sample.base.http;


import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.PutRequest;

import java.io.File;
import java.util.Map;

/**
 * @author liujiang
 * Desc: OkGo网络框架
 */
public class Okgo {

    /**
     * get请求
     * @param object
     * @param url
     * @param parameter
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T getRequest(Object object, String url, Map<String, Object> parameter, JsonCallback<T> jsonCallback) {
        GetRequest<T> get = OkGo.get(url);
        get.headers("X-Access-Token", "");
        if (null != object)
            get.tag(object);
        for (String key : parameter.keySet()) {
            Object value = parameter.get(key);
            if (value instanceof String) {
                get.params(key, (String) value);
            } else if (value instanceof Integer) {
                get.params(key, (Integer) value);
            } else if (value instanceof Long) {
                get.params(key, (Long) value);
            } else if (value instanceof Float) {
                get.params(key, (Float) value);
            } else if (value instanceof Double) {
                get.params(key, (Double) value);
            } else if (value instanceof Boolean) {
                get.params(key, (Boolean) value);
            }
        }
        get.execute(jsonCallback);
        return null;
    }

    /**
     * post请求
     * @param object
     * @param url
     * @param parameter
     * @param body
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T postRequest(Object object, String url, Map<String, Object> parameter, Object body, JsonCallback<T> jsonCallback) {
        PostRequest<T> post = OkGo.post(url);
        if (null != object)
            post.tag(object);
        for (String key : parameter.keySet()) {
            Object value = parameter.get(key);
            if (null != value) {
                if (value instanceof String) {
                    post.params(key, (String) value);
                } else if (value instanceof Integer) {
                    post.params(key, (Integer) value);
                } else if (value instanceof Long) {
                    post.params(key, (Long) value);
                } else if (value instanceof Float) {
                    post.params(key, (Float) value);
                } else if (value instanceof Double) {
                    post.params(key, (Double) value);
                } else if (value instanceof Boolean) {
                    post.params(key, (Boolean) value);
                } else {
                    post.params(key, (File) value);
                }
            }
        }
        if (null != body)
            post.upJson(JSON.toJSONString(body));
        post.execute(jsonCallback);
        return null;
    }

    /**
     * put请求
     * @param object
     * @param url
     * @param parameter
     * @param body
     * @param jsonCallback
     * @param <T>
     * @return
     */
    public <T> T putRequest(Object object, String url, Map<String, Object> parameter, Object body, JsonCallback<T> jsonCallback) {
        PutRequest<T> put = OkGo.put(url);
        if (null != object)
            put.tag(object);
        for (String key : parameter.keySet()) {
            Object value = parameter.get(key);
            if (null != value) {
                if (value instanceof String) {
                    put.params(key, (String) value);
                } else if (value instanceof Integer) {
                    put.params(key, (Integer) value);
                } else if (value instanceof Long) {
                    put.params(key, (Long) value);
                } else if (value instanceof Float) {
                    put.params(key, (Float) value);
                } else if (value instanceof Double) {
                    put.params(key, (Double) value);
                } else if (value instanceof Boolean) {
                    put.params(key, (Boolean) value);
                } else {
                    put.params(key, (File) value);
                }
            }
        }
        if (null != body)
            put.upJson(JSON.toJSONString(body));
        put.execute(jsonCallback);
        return null;
    }
}
