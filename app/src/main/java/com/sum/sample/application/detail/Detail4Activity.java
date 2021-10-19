package com.sum.sample.application.detail;

import android.annotation.SuppressLint;
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
import com.sum.sample.databinding.ActivityDetail4Binding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author liujiang
 * created at: 2021/9/16 16:11
 * Desc: 详情样式四(CustomToolbarLayout + tabLayout)
 */
public class Detail4Activity extends BaseActivity<ActivityDetail4Binding> {
    private final String[] titles = {"玩家", "属性", "封面", "预览"};
    private final List<Fragment> fragments = new ArrayList<>();

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected ActivityDetail4Binding getViewBinding() {
        return ActivityDetail4Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(activity)
                .titleBar(viewBinding.titleBar)
                .autoDarkModeEnable(true, 0.2f)
                .init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.collapsingLayout.post(new Runnable() {
            @Override
            public void run() {
                final int offHeight = viewBinding.collapsingLayout.getHeight() - ImmersionBar.getStatusBarHeight(activity) - viewBinding.titleBar.getHeight();
                viewBinding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        //是否滑动到了顶部
                        boolean flag = Math.abs(verticalOffset) >= offHeight;
                        //修改状态栏颜色
                        ImmersionBar.with(activity).statusBarDarkFont(flag, 0.2f).init();
                        //设置toolbar内部控件
                        if (flag) {
                            viewBinding.titleBar.setTitleColor(WorkUtils.getColor(R.color.colorText))
                                    .setBackIconResource(R.mipmap.back)
                                    .setDividerAlpha(255)
                                    .setBackgroundAlpha(255);

                        } else {
                            viewBinding.titleBar.setTitleColor(WorkUtils.getColor(R.color.white))
                                    .setBackIconResource(R.mipmap.back_white)
                                    .setDividerAlpha(Math.abs(verticalOffset) / offHeight)
                                    .setBackgroundAlpha(Math.abs(verticalOffset) / offHeight);
                        }
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
