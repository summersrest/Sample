package com.sum.sample.application.tab.flycotab_viewpager.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.base.utils.L;
import com.sum.sample.databinding.FragmentFv2Binding;

/**
 * @author liujiang
 * created at: 2021/9/7 11:13
 * Desc:
 */
public class FV2Fragment extends BaseFragment<FragmentFv2Binding> {

    @Override
    public void initView() {

    }

    @Override
    protected FragmentFv2Binding getViewBinding() {
        return FragmentFv2Binding.inflate(getLayoutInflater());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.setTitleBar(activity, viewBinding.titleLayout);
    }


}
