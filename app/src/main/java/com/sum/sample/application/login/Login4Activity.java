package com.sum.sample.application.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.interfaces.SimpleTextWatcher;
import com.sum.sample.databinding.ActivityLogin4Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:14
 * Desc: 登录灵感2
 */
public class Login4Activity extends BaseActivity<ActivityLogin4Binding> {
    @Override
    protected ActivityLogin4Binding getViewBinding() {
        return ActivityLogin4Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.tvTitle).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                viewBinding.tilPhone.setErrorEnabled(false);
            }
        });

        viewBinding.etPwd.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                viewBinding.tilPwd.setErrorEnabled(false);
            }
        });

        viewBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        if (v == viewBinding.btnLogin) {
            String phone = viewBinding.etPhone.getText().toString();
            if (TextUtils.isEmpty(phone) || phone.length() < 11) {
                viewBinding.tilPhone.setError("请输入正确的电话号码");
                return;
            }
            String pwd = viewBinding.etPwd.getText().toString();
            if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {
                viewBinding.tilPwd.setError("密码不能少于6位");
                return;
            }
        }
    }
}
