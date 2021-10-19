package com.sum.sample.application.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityLogin5Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:20
 * Desc: 登录灵感3
 */
public class Login5Activity extends BaseActivity<ActivityLogin5Binding> {
    @Override
    protected ActivityLogin5Binding getViewBinding() {
        return ActivityLogin5Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.topView).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
