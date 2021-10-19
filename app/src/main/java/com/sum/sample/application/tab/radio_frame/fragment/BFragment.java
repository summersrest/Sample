package com.sum.sample.application.tab.radio_frame.fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentBBinding;

/**
 * @author liujiang
 * created at: 2021/9/16 13:30
 * Desc:
 */
public class BFragment extends BaseFragment<FragmentBBinding> {
    @Override
    protected FragmentBBinding getViewBinding() {
        return FragmentBBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        ImmersionBar.with(this).reset()
                .statusBarDarkFont(true)
                .statusBarView(R.id.top_view)
                .init();
    }
}
