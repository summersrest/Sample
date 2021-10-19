package com.sum.sample.application.tab.flycotab_viewpager.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.base.utils.L;
import com.sum.sample.databinding.FragmentFv4Binding;

/**
 * @author liujiang
 * created at: 2021/9/7 11:15
 * Desc:
 */
public class FV4Fragment extends BaseFragment<FragmentFv4Binding> {

    @Override
    public void initView() {

    }

    @Override
    protected FragmentFv4Binding getViewBinding() {
        return FragmentFv4Binding.inflate(getLayoutInflater());
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.setTitleBar(activity, viewBinding.titleLayout);
    }

}
