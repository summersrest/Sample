package com.sum.sample.application.tab.radio_viewpager.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentTwoBinding;

/**
 * @author liujiang
 * created at: 2021/8/6 13:22
 * Desc:
 */
public class TwoFragment extends BaseFragment<FragmentTwoBinding> {

    @Override
    public void initView() {

    }

    @Override
    protected FragmentTwoBinding getViewBinding() {
        return FragmentTwoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initImmersionBar() {
        //单独设置标题栏高度
        ImmersionBar.setTitleBar(activity, viewBinding.titleLayout);

    }


}
