package com.sum.sample.application.detail;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.detail.fragment.DFragment1;
import com.sum.sample.application.detail.fragment.DFragment2;
import com.sum.sample.application.detail.fragment.DFragment3;
import com.sum.sample.application.detail.fragment.DFragment4;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.adapter.ViewPagerAdapter;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.sample.databinding.ActivityDetail6Binding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author liujiang
 * created at: 2021/9/16 16:37
 * Desc: CollapsingToolbarLayout+tabLayout实现tab吸顶标题栏渐变
 */
public class Detail6Activity extends BaseActivity<ActivityDetail6Binding> {
    private final String[] titles = {"玩家", "属性", "封面", "预览"};
    private final List<Fragment> fragments = new ArrayList<>();
    @Override
    protected ActivityDetail6Binding getViewBinding() {
        return ActivityDetail6Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.titleBar).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //控制title渐变
        viewBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float alpha = Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange();
                viewBinding.titleBar.setBackgroundAlpha((int) (alpha * 255));
                if (alpha > 0.5) {
                    //滑动距离超过一半时控件设置深色
                    viewBinding.titleBar.setBackIconResource(R.mipmap.back)
                            .setTitleColor(WorkUtils.getColor(R.color.colorText));
                    ImmersionBar.with(activity).statusBarDarkFont(true).init();
                } else {
                    //滑动距离不足一半时控件设置浅色
                    viewBinding.titleBar.setBackIconResource(R.mipmap.back_white)
                            .setTitleColor(WorkUtils.getColor(R.color.white));
                    ImmersionBar.with(activity).statusBarDarkFont(false).init();
                }
            }
        });

        fragments.add(new DFragment1());
        fragments.add(new DFragment2());
        fragments.add(new DFragment3());
        fragments.add(new DFragment4());
        viewBinding.viewPager.setAdapter(new ViewPagerAdapter(activity, fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });
        tabLayoutMediator.attach();
    }
}
