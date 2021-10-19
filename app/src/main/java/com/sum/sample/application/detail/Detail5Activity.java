package com.sum.sample.application.detail;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityDetail5Binding;

/**
 * @author liujiang
 * created at: 2021/9/16 16:36
 * Desc: 详情样式五(上滑隐藏标题栏)
 */
public class Detail5Activity extends BaseActivity<ActivityDetail5Binding> {
    @Override
    protected ActivityDetail5Binding getViewBinding() {
        return ActivityDetail5Binding.inflate(getLayoutInflater());
    }

//    @Override
//    protected void setStatusBar() {
//        ImmersionBar.with(this).titleBar(viewBinding.toolbar).statusBarDarkFont(true).init();
//    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
