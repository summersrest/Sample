package com.sum.sample.application.tab.radio_viewpager;

import android.app.Activity;
import android.widget.RadioGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class OnPageChangeEven extends ViewPager2.OnPageChangeCallback {
    protected RadioGroup radioGroup;
    protected Activity activity;
    public OnPageChangeEven(Activity activity, RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
        this.activity = activity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //实现滑动页面下方按钮的联动
        switch (position) {
            case 0:
                radioGroup.check(R.id.main_home_page);
                ImmersionBar.with(activity).keyboardEnable(true).statusBarDarkFont(false).init();
                break;
            case 1:
                radioGroup.check(R.id.main_my_page);
                ImmersionBar.with(activity).keyboardEnable(true).statusBarDarkFont(true).init();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
