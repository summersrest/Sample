package com.sum.sample.application.tab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.application.tab.flycotab_viewpager.FlycoAndViewPagerActivity;
import com.sum.sample.application.tab.radio_frame.RadioAndFrameActivity;
import com.sum.sample.application.tab.radio_viewpager.RadioAndViewPagerActivity;
import com.sum.sample.application.tab.tablayout_viewpager.TabLayoutAndViewPagerActivity;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.databinding.ActivityTabLayoutBinding;
import com.sum.sample.databinding.ItemMainBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;
import com.sum.simpleadapter.interfaces.SimpleOnItemClickListener;

import java.util.Arrays;
import java.util.List;

public class TabLayoutActivity extends BaseActivity<ActivityTabLayoutBinding> {
    private final List<String> datas = Arrays.asList("RadioGroup与viewpager实现tab", "RadioGroup与FrameLayout实现tab",
            "FlycoTabLayout与viewpager实现tab",  "TabLayout与ViewPager实现tab");


    @Override
    protected ActivityTabLayoutBinding getViewBinding() {
        return ActivityTabLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.toolbar).init();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        BaseAdapter<ItemMainBinding, String> adapter = new BaseAdapter<ItemMainBinding, String>(context, datas) {
            @Override
            protected ItemMainBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemMainBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemMainBinding> holder, String item, int position) {
                holder.binding.tvTitle.setText(item);
                holder.binding.ivLeft.setImageResource(IconProvider.getIcon(position));
                holder.binding.ivRight.setImageResource(IconProvider.getFlower(position));
            }
        };
        adapter.setOnItemClickListener(new SimpleOnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                switch (position) {
                    //RadioGroup与viewpager实现tab
                    case 0:
                        ActivityUtils.startActivity(activity, RadioAndViewPagerActivity.class);
                        break;
                    //RadioGroup与FrameLayout实现tab
                    case 1:
                        ActivityUtils.startActivity(activity, RadioAndFrameActivity.class);
                        break;
                    //FlycoTabLayout与viewpager实现tab
                    case 2:
                        ActivityUtils.startActivity(activity, FlycoAndViewPagerActivity.class);
                        break;
                    //TabLayout与ViewPager实现tab
                    case 3:
                        ActivityUtils.startActivity(activity, TabLayoutAndViewPagerActivity.class);
                        break;
                }
            }
        });
        viewBinding.recyclerView.setAdapter(adapter);
    }

}