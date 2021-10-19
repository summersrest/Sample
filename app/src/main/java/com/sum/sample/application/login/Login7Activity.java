package com.sum.sample.application.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityLogin7Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:23
 * Desc: 登录灵感5
 */
public class Login7Activity extends BaseActivity<ActivityLogin7Binding> {
    @Override
    protected ActivityLogin7Binding getViewBinding() {
        return ActivityLogin7Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.topView).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
