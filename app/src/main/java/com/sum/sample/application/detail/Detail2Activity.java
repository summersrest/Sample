package com.sum.sample.application.detail;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityDetail2Binding;

/**
 * @author liujiang
 * created at: 2021/9/16 15:21
 * Desc: 详情样式2(CustomToolbarLayout+NestScrollView)
 */
public class Detail2Activity extends BaseActivity<ActivityDetail2Binding> {
    @Override
    protected ActivityDetail2Binding getViewBinding() {
        return ActivityDetail2Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        //设置状态栏
        ImmersionBar.with(activity)
                .titleBar(viewBinding.titleBar)
                .statusBarDarkFont(false)
                .init();
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
