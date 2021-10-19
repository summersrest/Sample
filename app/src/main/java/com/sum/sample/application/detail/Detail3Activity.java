package com.sum.sample.application.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.application.detail.fragment.DFragment1;
import com.sum.sample.application.detail.fragment.DFragment2;
import com.sum.sample.application.detail.fragment.DFragment3;
import com.sum.sample.application.detail.fragment.DFragment4;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.adapter.ViewPagerAdapter;
import com.sum.sample.databinding.ActivityDetail3Binding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author liujiang
 * created at: 2021/9/16 15:32
 * Desc: 详情样式三(CustomToolbarLayout + tabLayout)
 */
public class Detail3Activity extends BaseActivity<ActivityDetail3Binding> {
    private final String[] titles = {"玩家", "属性", "封面", "预览"};
    private final List<Fragment> fragments = new ArrayList<>();

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setStatusBar() {
        //设置顶部toolbar
        ImmersionBar.setTitleBar(this, viewBinding.toolbar);
        //设置滑到顶部后的占位
        ImmersionBar.with(activity)
                .statusBarView(viewBinding.view)
                .autoDarkModeEnable(true, 0.2f)
                .init();
    }

    @Override
    protected ActivityDetail3Binding getViewBinding() {
        return ActivityDetail3Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.collapsingLayout.post(new Runnable() {
            @Override
            public void run() {
                final int offHeight = viewBinding.collapsingLayout.getHeight() - ImmersionBar.getStatusBarHeight(activity);
                viewBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        ImmersionBar.with(activity).statusBarDarkFont(Math.abs(verticalOffset) >= offHeight, 0.2f).init();
                    }
                });
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
