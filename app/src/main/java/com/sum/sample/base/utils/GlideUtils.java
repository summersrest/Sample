package com.sum.sample.base.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author liujiang
 * Desc:
 */
public class GlideUtils {
    public static void load(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        //防止图片背景变绿
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        options.error(R.mipmap.img_error);
//        options.placeholder(R.mipmap.img_error);
        options.dontAnimate();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
} 
