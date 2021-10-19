package com.sum.sample.base.interfaces;

import androidx.viewpager2.widget.ViewPager2;

/**
 * @author liujiang
 * created at: 2021/9/16 10:50
 * Desc: viewpager滑动监听
 */
public abstract class SimplePageChangeListener extends ViewPager2.OnPageChangeCallback {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
    }
}
