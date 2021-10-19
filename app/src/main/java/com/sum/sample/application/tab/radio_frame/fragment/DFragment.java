package com.sum.sample.application.tab.radio_frame.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentDBinding;

/**
 * @author liujiang
 * created at: 2021/9/16 13:32
 * Desc:
 */
public class DFragment extends BaseFragment<FragmentDBinding> {

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).reset()
                .statusBarDarkFont(true)
                .titleBar(viewBinding.titleLayout)
                .init();
    }

    @Override
    protected FragmentDBinding getViewBinding() {
        return FragmentDBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }
}
