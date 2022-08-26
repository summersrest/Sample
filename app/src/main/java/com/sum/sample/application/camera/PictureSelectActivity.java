package com.sum.sample.application.camera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.sum.sample.R;
import com.sum.sample.application.camera.utils.GlideEngine;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.L;
import com.sum.sample.base.utils.compress.CompressUtils;
import com.sum.sample.base.utils.compress.OnCompressListener;
import com.sum.sample.databinding.ActivityPictureSelectBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/28 10:20
 * Desc: 图片选择器
 */
public class PictureSelectActivity extends BaseActivity<ActivityPictureSelectBinding> {
    //已选图片
    List<LocalMedia> photoList = new ArrayList<>();
    //拍摄的视频
    private LocalMedia videoMedia;

    @Override
    protected ActivityPictureSelectBinding getViewBinding() {
        return ActivityPictureSelectBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //多张图片选择
        initMultiplePicture();
        //单张图片选择并压缩
        initSinglePicture();
        //单独拍照
        initCamera();
        //录制视频
        initVideo();
        //多个视频选择
        initVideoMultiple();

        //压缩图片
        viewBinding.btnCompress.setOnClickListener(v -> {
            compress(context, photoList);
        });
    }

    /**
     * 多张图片选择
     */
    private void initMultiplePicture() {
        //多张图片新增
        viewBinding.psvMultiple.setOnPictureAddListener(() -> {
            checkPermission(new SimpleCheckPermListener() {
                                @Override
                                public void superPermission() {
                                    photoMultipleSelected();
                                }
                            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        });
        //多张图片预览
        viewBinding.psvMultiple.setOnPictureClickListener((position, path, paths) -> {
            PictureSelector.create(this)
                    .themeStyle(R.style.picture_default_style)
                    .isNotPreviewDownload(true)
                    .imageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(position, photoList);
        });
        //删除图片
        viewBinding.psvMultiple.setOnPictureDeleteListener((position -> {
            viewBinding.psvMultiple.remove(position);
            photoList.remove(position);
        }));
    }

    /**
     * 单张图片选择并压缩
     */
    private void initSinglePicture() {
        //单张图片新增
        viewBinding.psvSingle.setOnPictureAddListener(() -> {
            checkPermission(new SimpleCheckPermListener() {
                                @Override
                                public void superPermission() {
                                    photoSingleSelect();
                                }
                            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        });
        //单张图片查看
        viewBinding.psvSingle.setOnPictureClickListener((position, path, paths) -> {
            List<LocalMedia> result = new ArrayList<>();
            LocalMedia media = new LocalMedia();
            media.setCompressPath(path);
            media.setCompressed(true);
            result.add(media);
            PictureSelector.create(this)
                    .themeStyle(R.style.picture_default_style)
                    .isNotPreviewDownload(true)
                    .imageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(position, result);
        });
    }

    /**
     * 单独拍照
     */
    private void initCamera() {
        //拍照
        viewBinding.psvCamera.setOnPictureAddListener(() -> {
            checkPermission(new SimpleCheckPermListener() {
                                @Override
                                public void superPermission() {
                                    photoCamera();
                                }
                            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        });
        //单张图片查看
        viewBinding.psvCamera.setOnPictureClickListener((position, path, paths) -> {
            List<LocalMedia> result = new ArrayList<>();
            LocalMedia media = new LocalMedia();
            media.setCompressPath(path);
            media.setCompressed(true);
            result.add(media);
            PictureSelector.create(this)
                    .themeStyle(R.style.picture_default_style)
                    .isNotPreviewDownload(true)
                    .imageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(position, result);
        });
    }

    /**
     * 录制视频
     */
    private void initVideo() {
        //录制视频
        viewBinding.psvVideo.setOnPictureAddListener(() -> {
            checkPermission(new SimpleCheckPermListener() {
                                @Override
                                public void superPermission() {
                                    video();
                                }
                            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        });
        //视频预览
        viewBinding.psvVideo.setOnPictureClickListener((position, path, paths) -> {
            String videoPath = TextUtils.isEmpty(videoMedia.getAndroidQToPath()) ? videoMedia.getPath() : videoMedia.getAndroidQToPath();
            // 预览视频
            PictureSelector.create(this)
                    .themeStyle(R.style.picture_default_style)
                    .externalPictureVideo(videoPath);
        });
    }

    /**
     * 视频单选
     */
    private void initVideoMultiple() {
        //录制视频
        viewBinding.psvVideoSingle.setOnPictureAddListener(() -> {
            checkPermission(new SimpleCheckPermListener() {
                                @Override
                                public void superPermission() {
                                    videoSingle();
                                }
                            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        });
        //视频预览
        viewBinding.psvVideoSingle.setOnPictureClickListener((position, path, paths) -> {
            String videoPath = TextUtils.isEmpty(videoMedia.getAndroidQToPath()) ? videoMedia.getPath() : videoMedia.getAndroidQToPath();
            // 预览视频
            PictureSelector.create(this)
                    .themeStyle(R.style.picture_default_style)
                    .externalPictureVideo(videoPath);
        });
    }

    /**
     * 多张图片选择
     */
    public void photoMultipleSelected() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .maxSelectNum(6)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                .isAndroidQTransform(true)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选（PictureConfig.MULTIPLE : PictureConfig.SINGLE）
//                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isPreviewImage(true)// 是否可预览图片
//                .isPreviewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .isEnableCrop(false)// 是否裁剪
                .isCompress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .isGif(false)// 是否显示gif图片
                .isOpenClickSound(false)// 是否开启点击声音
                .selectionData(photoList)// 是否传入已选图片
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        if (null != result && 0 < result.size()) {
                            photoList.clear();
                            photoList.addAll(result);
                            List<String> paths = new ArrayList<>();
                            for (LocalMedia media : photoList) {
                                paths.add(media.getPath());
                            }
                            viewBinding.psvMultiple.clearAndAdd(paths);
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });

    }

    /**
     * 单张图片选择并压缩
     */
    private void photoSingleSelect() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .imageSpanCount(4)// 每行显示个数
                .isAndroidQTransform(true)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && .isEnableCrop(false);有效,默认处理
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选（PictureConfig.MULTIPLE : PictureConfig.SINGLE）
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isPreviewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .isEnableCrop(false)// 是否裁剪
                .isCompress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .isGif(false)// 是否显示gif图片
                .isOpenClickSound(false)// 是否开启点击声音
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        if (null != result && 0 < result.size()) {
                            String path = result.get(0).getCompressPath();
                            viewBinding.psvSingle.add(path);
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * 单独拍照
     */
    private void photoCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                //.setOutputCameraPath()// 自定义相机输出目录
                .isPreviewImage(true)// 是否可预览图片
                .isCompress(true)// 是否压缩
                .compressQuality(60)// 图片压缩后输出质量
                .isAutoScalePreviewImage(true)// 如果图片宽度不能充满屏幕则自动处理成充满模式
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        if (null != result && 0 < result.size()) {
                            String path = result.get(0).getCompressPath();
                            viewBinding.psvCamera.add(path);
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * 视频录制
     */
    private void video() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofVideo())// 单独拍照
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                //.setOutputCameraPath()// 自定义相机输出目录
                .recordVideoSecond(15)//视频最大时长
                .recordVideoMinSecond(10)//视频最小时长
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        if (null != result && 0 < result.size()) {
                            videoMedia = result.get(0);
                            String path = videoMedia.getPath();
                            viewBinding.psvVideo.add(path);

                            L.showD("size： " + videoMedia.getSize() + "");
                            L.showD("path： " + videoMedia.getPath());
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * 视频单选
     */
    private void videoSingle() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofVideo())
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选（PictureConfig.MULTIPLE : PictureConfig.SINGLE）
                .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .maxSelectNum(6)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .videoMinSecond(5)// 查询多少秒以内的视频
                .videoMaxSecond(10)// 查询多少秒以内的视频
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // 结果回调
                        if (null != result && 0 < result.size()) {
                            videoMedia = result.get(0);
                            viewBinding.psvVideoSingle.clearAndAdd(videoMedia.getPath());
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * 压缩图片
     * @param context
     * @param list
     */
    private void compress(Context context, List<LocalMedia> list) {
        showDialog("正在压缩图片");
        CompressUtils.instance().compress(context, list, new OnCompressListener() {
            @Override
            public void onCompressSuccess(List<LocalMedia> images) {
                hintDialog();
                for (LocalMedia media : images)
                    L.showD("压缩后的路径----->" + media.getCompressPath());
            }

            @Override
            public void onCompressFail(List<LocalMedia> images, String msg) {
                hintDialog("压缩失败");
            }

            @Override
            public void onImageEmpty() {
                hintDialog("无图片");
            }
        });
    }

}
