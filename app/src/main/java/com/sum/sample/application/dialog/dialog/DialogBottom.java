package com.sum.sample.application.dialog.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.sum.sample.R;
import com.sum.sample.databinding.DialogTopBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

/**
 * @author liujiang
 * created at: 2021/9/17 10:43
 * Desc: 底部弹窗
 */
public class DialogBottom extends AlertDialog {
    private Context context;
    public DialogBottom(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public DialogBottom(@NonNull Context context, int themeResId) {
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
        mDialogWindow.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        mDialogWindow.setWindowAnimations(R.style.BottomDialog);
        //设置宽高
        mDialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}