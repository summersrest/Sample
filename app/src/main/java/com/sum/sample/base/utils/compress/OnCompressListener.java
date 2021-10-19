package com.sum.sample.base.utils.compress;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/28 17:40
 * Desc:
 */
public interface OnCompressListener {
    void onCompressSuccess(List<LocalMedia> images);

    void onCompressFail(List<LocalMedia> images, String msg);

    void onImageEmpty();
}
