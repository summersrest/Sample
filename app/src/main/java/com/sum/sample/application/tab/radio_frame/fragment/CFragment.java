package com.sum.sample.application.tab.radio_frame.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentCBinding;

/**
 * @author liujiang
 * created at: 2021/9/16 13:31
 * Desc:
 */
public class CFragment extends BaseFragment<FragmentCBinding> {
    @Override
    protected FragmentCBinding getViewBinding() {
        return FragmentCBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).reset()
                .statusBarDarkFont(false)
                .titleBar(viewBinding.titleLayout)
                .init();
    }

    @Override
    public void initView() {

    }
}
