package com.sum.sample.application.tab.tablayout_viewpager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sum.sample.application.detail.fragment.DFragment1;
import com.sum.sample.application.detail.fragment.DFragment2;
import com.sum.sample.application.detail.fragment.DFragment3;
import com.sum.sample.application.detail.fragment.DFragment4;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.adapter.ViewPagerAdapter;
import com.sum.sample.databinding.ActivityTablayoutViewpagerBinding;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author liujiang
 * created at: 2021/9/16 16:05
 * Desc: TabLayout与ViewPager实现tab
 */
public class TabLayoutAndViewPagerActivity extends BaseActivity<ActivityTablayoutViewpagerBinding> {
    private final String[] titles = {"玩家", "属性", "封面", "预览"};
    private final List<Fragment> fragments = new ArrayList<>();


    @Override
    protected ActivityTablayoutViewpagerBinding getViewBinding() {
        return ActivityTablayoutViewpagerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
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
