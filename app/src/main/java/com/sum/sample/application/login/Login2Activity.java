package com.sum.sample.application.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityLogin2Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:11
 * Desc: 仿数控谷登录页面样式
 */
public class Login2Activity extends BaseActivity<ActivityLogin2Binding> {
    @Override
    protected ActivityLogin2Binding getViewBinding() {
        return ActivityLogin2Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.topView).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
