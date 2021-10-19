package com.sum.sample.application.tab.radio_viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.tab.radio_viewpager.fragment.OneFragment;
import com.sum.sample.application.tab.radio_viewpager.fragment.TwoFragment;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.adapter.ViewPagerAdapter;
import com.sum.sample.databinding.ActivityRadioViewpagerBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author liujiang
 * created at: 2021/8/6 10:49
 * Desc: RadioGroup与viewpager实现tab
 */
public class RadioAndViewPagerActivity extends BaseActivity<ActivityRadioViewpagerBinding> {

    //防止后台长期运行cash
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(activity).keyboardEnable(true).statusBarDarkFont(false).init();
    }

    @Override
    protected ActivityRadioViewpagerBinding getViewBinding() {
        return ActivityRadioViewpagerBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        //底部tab监听
        viewBinding.radioGroup.setOnCheckedChangeListener(new OnCheckedChangeEven(activity, viewBinding.viewPager));
        viewBinding.viewPager.setAdapter(new ViewPagerAdapter(RadioAndViewPagerActivity.this, fragments));
        viewBinding.viewPager.registerOnPageChangeCallback(new OnPageChangeEven(activity, viewBinding.radioGroup));
        //默认选中第一项
        viewBinding.radioGroup.check(R.id.main_home_page);
    }

}
