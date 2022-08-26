package com.sum.sample.application.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sum.sample.R;
import com.sum.sample.base.utils.SoftKeyboardUtils;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.databinding.DialogCenterBinding;
import androidx.annotation.NonNull;

/**
 * @author liujiang
 * created at: 2021/9/17 10:45
 * Desc: 中央弹窗
 */
public class DialogCenter  extends Dialog {
    private Context context;
    DialogCenterBinding viewBinding;

    public DialogCenter(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public DialogCenter(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DialogCenterBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.masterMcb.setChecked(true);
        viewBinding.masterLayout.setOnClickListener(new OnCheckedEven());
        viewBinding.lesseeLayout.setOnClickListener(new OnCheckedEven());

        //设置弹出动画
        getWindow().setWindowAnimations(R.style.CenterDialog);

        //获取焦点改变边框颜色
        viewBinding.accountEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    viewBinding.accountEt.setBackgroundResource(R.drawable.bg_et_app_selected);
                } else {
                    viewBinding.accountEt.setBackgroundResource(R.drawable.bg_et_app);
                }
            }
        });
        viewBinding.phoneEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    viewBinding.phoneEt.setBackgroundResource(R.drawable.bg_et_app_selected);
                } else {
                    viewBinding.phoneEt.setBackgroundResource(R.drawable.bg_et_app);
                }
            }
        });

        //取消
        viewBinding.cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyboardUtils.hideSoftInput(context, viewBinding.accountEt);
                SoftKeyboardUtils.hideSoftInput(context, viewBinding.phoneEt);
                dismiss();
            }
        });

        //确定
        viewBinding.okLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = viewBinding.accountEt.getText().toString().trim();
                String phoneStr = viewBinding.phoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(nameStr)) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }
                if (TextUtils.isEmpty(phoneStr)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (phoneStr.length() != 11) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                //成员类型 2 家庭成员，3 租户
                int identity = viewBinding.masterMcb.isChecked() ? 2 : 3;
                SoftKeyboardUtils.hideSoftInput(context, viewBinding.accountEt);
                SoftKeyboardUtils.hideSoftInput(context, viewBinding.phoneEt);
                dismiss();
            }
        });
    }

    private class OnCheckedEven implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.master_layout) {
                viewBinding.masterMcb.setChecked(!viewBinding.masterMcb.isChecked());
                if (viewBinding.lesseeMcb.isChecked() == viewBinding.masterMcb.isChecked())
                    viewBinding.lesseeMcb.setChecked(!viewBinding.masterMcb.isChecked());
            } else if (view.getId() == R.id.lessee_layout) {
                viewBinding.lesseeMcb.setChecked(!viewBinding.lesseeMcb.isChecked());
                if (viewBinding.masterMcb.isChecked() == viewBinding.lesseeMcb.isChecked())
                    viewBinding.masterMcb.setChecked(!viewBinding.lesseeMcb.isChecked());
            }

        }
    }

}
