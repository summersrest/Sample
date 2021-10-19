package com.sum.sample.base.utils.compress;

import android.content.Context;

import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/28 17:34
 * Desc: 压缩图片工具类
 */
public class CompressUtils {
    private CompressUtils() {
    }

    private static CompressUtils compressUtils;

    public static CompressUtils instance() {
        if (compressUtils == null) {
            synchronized (CompressUtils.class) {
                if (compressUtils == null) {
                    compressUtils = new CompressUtils();
                }
            }
        }
        return compressUtils;
    }

    /**
     * 压缩
     *
     * @param context
     * @param list
     * @param listener
     */
    public void compress(Context context, final List<LocalMedia> list, final OnCompressListener listener) {
        if (list.size() == 0) {
            listener.onImageEmpty();
            return;
        }
        LocalMedia media = list.get(0);
        //图片压缩
        String mimeType = media.getMimeType();
        if (PictureMimeType.getMimeType(mimeType) == PictureConfig.TYPE_IMAGE) {
            Luban.with(context)
                    .loadMediaData(list)
                    .ignoreBy(PictureSelectionConfig.getInstance().minimumCompressSize)
                    .setTargetDir(PictureSelectionConfig.getInstance().compressSavePath)
                    .setCompressListener(new com.luck.picture.lib.compress.OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(List<LocalMedia> list) {
                            listener.onCompressSuccess(list);
                        }

                        @Override
                        public void onError(Throwable e) {
                            listener.onCompressFail(list, e.getMessage());
                        }
                    }).launch();
        }

    }
} 
