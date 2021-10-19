package com.sum.sample.application.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityLogin3Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:12
 * Desc: 登录灵感1
 */
public class Login3Activity extends BaseActivity<ActivityLogin3Binding> {
    @Override
    protected ActivityLogin3Binding getViewBinding() {
        return ActivityLogin3Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.topLayout).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
