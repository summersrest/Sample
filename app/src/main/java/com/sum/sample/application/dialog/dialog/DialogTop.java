package com.sum.sample.application.dialog.dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.databinding.DialogTopBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

/**
 * @author liujiang
 * created at: 2021/9/17 10:50
 * Desc:
 */
public class DialogTop extends AlertDialog {
    private Context context;
    public DialogTop(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public DialogTop(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogTopBinding viewBinding = DialogTopBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        Window mDialogWindow = getWindow();
        //解决无法弹出输入法的问题
        mDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //设置位置
        mDialogWindow.setGravity(Gravity.TOP);
        //设置弹出动画
        mDialogWindow.setWindowAnimations(R.style.TopDialog);
        //设置宽高
        mDialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置沉浸式
        ImmersionBar.with((Activity) context, DialogTop.this)
                .titleBar(viewBinding.toolbar)
                .init();
    }
}
