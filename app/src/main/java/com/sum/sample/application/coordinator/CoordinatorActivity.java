package com.sum.sample.application.coordinator;

import android.os.Bundle;

import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityCoordinatorBinding;

/**
 * @author liujiang
 * created at: 2021/9/17 9:46
 * Desc: CoordinatorLayout Demo
 */
public class CoordinatorActivity extends BaseActivity<ActivityCoordinatorBinding> {
    @Override
    protected ActivityCoordinatorBinding getViewBinding() {
        return ActivityCoordinatorBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
