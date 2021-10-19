package com.sum.sample.application.camera;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sum.sample.R;
import com.sum.sample.databinding.PopupCameraBinding;

/**
 * @author liujiang
 * created at: 2021/9/28 10:27
 * Desc:
 */
public class CameraPopup extends PopupWindow {
    public CameraPopup(final Activity activity, final OnSelectedListener onSelectedListener) {
        PopupCameraBinding binding = PopupCameraBinding.inflate(LayoutInflater.from(activity));
        //选择图片
        binding.pictureBtn.setOnClickListener(v -> {
            onSelectedListener.onSelected(0);
            dismiss();
        });
        //选择视频
        binding.videoBtn.setOnClickListener(v -> {
            onSelectedListener.onSelected(1);
            dismiss();
        });
        //取消
        binding.cancelBtn.setOnClickListener(v -> {
            dismiss();
        });

        //设置PopupWindow的View
        this.setContentView(binding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.BottomDialog);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    public void show(Activity activity, View view) {
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //背景置灰
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }


    public interface OnSelectedListener {
        void onSelected(int type);
    }
}
