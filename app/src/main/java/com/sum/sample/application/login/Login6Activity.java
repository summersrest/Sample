package com.sum.sample.application.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityLogin6Binding;

/**
 * @author liujiang
 * created at: 2021/9/30 10:22
 * Desc: 登录灵感4
 */
public class Login6Activity extends BaseActivity<ActivityLogin6Binding> {
    @Override
    protected ActivityLogin6Binding getViewBinding() {
        return ActivityLogin6Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.topView).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
