package com.sum.sample.application.tab.radio_viewpager;

import android.app.Activity;
import android.widget.RadioGroup;
import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class OnCheckedChangeEven implements RadioGroup.OnCheckedChangeListener {
    protected ViewPager2 viewPager;
    protected Activity activity;
    public OnCheckedChangeEven(Activity activity, ViewPager2 viewPager) {
        this.viewPager = viewPager;
        this.activity = activity;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.main_home_page) {
            viewPager.setCurrentItem(0);
            ImmersionBar.with(activity).keyboardEnable(true).statusBarDarkFont(false).init();
        } else if (checkedId == R.id.main_my_page) {
            viewPager.setCurrentItem(1);
            ImmersionBar.with(activity).keyboardEnable(true).statusBarDarkFont(true).init();
        }

    }
}
