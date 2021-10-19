package com.sum.sample.application.tab.flycotab_viewpager.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.base.utils.L;
import com.sum.sample.databinding.FragmentFv3Binding;

/**
 * @author liujiang
 * created at: 2021/9/7 11:14
 * Desc:
 */
public class FV3Fragment extends BaseFragment<FragmentFv3Binding> {
    @Override
    public void initImmersionBar() {
        ImmersionBar.setTitleBar(activity, viewBinding.toolbar);
    }

    @Override
    public void initView() {

    }

    @Override
    protected FragmentFv3Binding getViewBinding() {
        return FragmentFv3Binding.inflate(getLayoutInflater());
    }
}
