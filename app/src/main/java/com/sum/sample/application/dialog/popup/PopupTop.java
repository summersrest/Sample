package com.sum.sample.application.dialog.popup;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.databinding.PopupTopBinding;


public class PopupTop extends PopupWindow {
    public PopupTop(final Activity activity) {
        PopupTopBinding viewBinding = PopupTopBinding.inflate(LayoutInflater.from(activity));
        //设置PopupWindow的View
        this.setContentView(viewBinding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.TopDialog);
        //解决屏幕底部edittext被软键盘挡住的问题
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //此方法可以让布局延伸到状态栏和导航栏
        this.setClippingEnabled(false);
        //popupwindow.setClippingEnabled(false)这个方法，此方法会导致有导航栏的手机底部被导航栏覆盖，放弃沉浸式吧
        ImmersionBar.setTitleBar(activity, viewBinding.toolbar);
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
        showAtLocation(view, Gravity.TOP, 0, 0);
        //背景置灰
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
