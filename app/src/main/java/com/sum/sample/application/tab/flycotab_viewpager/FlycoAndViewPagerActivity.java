package com.sum.sample.application.tab.flycotab_viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.tab.flycotab_viewpager.fragment.FV1Fragment;
import com.sum.sample.application.tab.flycotab_viewpager.fragment.FV2Fragment;
import com.sum.sample.application.tab.flycotab_viewpager.fragment.FV3Fragment;
import com.sum.sample.application.tab.flycotab_viewpager.fragment.FV4Fragment;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.adapter.ViewPagerAdapter;
import com.sum.sample.base.interfaces.SimplePageChangeListener;
import com.sum.sample.base.interfaces.SimpleTabListener;
import com.sum.sample.databinding.ActivityFlycoViewpagerBinding;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author liujiang
 * created at: 2021/8/6 14:31
 * Desc: FlycoTabLayout与viewpager实现tab
 */
public class FlycoAndViewPagerActivity extends BaseActivity<ActivityFlycoViewpagerBinding> {
    ArrayList<Fragment> fragments = new ArrayList<>();

    //防止后台长期运行cash
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected ActivityFlycoViewpagerBinding getViewBinding() {
        return ActivityFlycoViewpagerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(activity).reset().keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        fragments.add(new FV1Fragment());
        fragments.add(new FV2Fragment());
        fragments.add(new FV3Fragment());
        fragments.add(new FV4Fragment());
        viewBinding.viewpager.setAdapter(new ViewPagerAdapter(activity, fragments));
        viewBinding.tabLayout.setTabData(getTabEntity());
        viewBinding.tabLayout.setOnTabSelectListener(new SimpleTabListener() {
            @Override
            public void onTabSelect(int position) {
                viewBinding.viewpager.setCurrentItem(position);
                setStatusbar(position);
            }
        });
        viewBinding.viewpager.registerOnPageChangeCallback(new SimplePageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                viewBinding.tabLayout.setCurrentTab(position);
                setStatusbar(position);
            }
        });

        viewBinding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }


    /**
     * 设置标题栏
     * @param position
     */
    private void setStatusbar(int position) {
        switch (position) {
            case 0:
            case 2:
                ImmersionBar.with(activity).reset().keyboardEnable(true).statusBarDarkFont(true, 0.2f).init();
                break;
            case 1:
            case 3:
                ImmersionBar.with(activity).reset().keyboardEnable(true).statusBarDarkFont(false).init();
                break;
        }
    }

    public ArrayList<CustomTabEntity> getTabEntity() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        String[] mTitles = {"首页", "类目", "进货单", "我"};
        int[] mIconUnselectIds = {R.mipmap.main1_home_unpressed, R.mipmap.main1_kind_unpressed,
                R.mipmap.main1_purchase_unpressed, R.mipmap.main1_my_unpressed};
        int[] mIconSelectIds = {R.mipmap.main1_home_pressed, R.mipmap.main1_kind_pressed,
                R.mipmap.main1_purchase_pressed, R.mipmap.main1_my_pressed};
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        return mTabEntities;
    }
}
