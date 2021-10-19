package com.sum.sample.application.tab.radio_frame.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentABinding;

/**
 * @author liujiang
 * created at: 2021/9/16 13:28
 * Desc:
 */
public class AFragment extends BaseFragment<FragmentABinding> {
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).reset()
                .statusBarDarkFont(false)
                .titleBar(viewBinding.toolbar)
                .init();
    }

    @Override
    protected FragmentABinding getViewBinding() {
        return FragmentABinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {

    }

}
