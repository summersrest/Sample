package com.sum.sample.application.dialog.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sum.sample.R;
import com.sum.sample.base.utils.ScreenUtils;
import com.sum.sample.databinding.DialogCenterBinding;


public class PopupCenter extends PopupWindow {
    public PopupCenter(final Activity activity) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        DialogCenterBinding viewBinding = DialogCenterBinding.inflate(inflater);
        //取消
        viewBinding.cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //确定
        viewBinding.okLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //设置PopupWindow的View
        this.setContentView(viewBinding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ScreenUtils.getScreenWidth() * 4 / 5);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.CenterDialog);
        //解决屏幕底部edittext被软键盘挡住的问题
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
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
        showAtLocation(view, Gravity.CENTER, 0, 0);
        //背景置灰
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.3f; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
