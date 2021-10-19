package com.sum.sample.application.detail;

import android.os.Bundle;
import android.view.View;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.interfaces.SimpleMultiPurposeLitener;
import com.sum.sample.base.utils.RxTimerUtil;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.sample.databinding.ActivityDetail1Binding;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

/**
 * @author liujiang
 * created at: 2021/9/16 15:00
 * Desc: 详情样式1(白色toolbar,scrollview控制透明度)
 */
public class Detail1Activity extends BaseActivity<ActivityDetail1Binding> {
    //标题栏与banner图下边缘的距离
    private int distance;
    @Override
    protected ActivityDetail1Binding getViewBinding() {
        return ActivityDetail1Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this)
                .titleBar(viewBinding.titleBar)
                .init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.refreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                RxTimerUtil.timer(1000, new RxTimerUtil.IRxNext() {
                    @Override
                    public void doNext(long number) {
                        viewBinding.refreshView.finishRefresh();
                    }
                });
            }
        });
        //下拉刷新时控制标题栏隐藏，防止标题栏覆盖刷新控件
        viewBinding.refreshView.setOnMultiListener(new SimpleMultiPurposeLitener() {

            @Override
            public void onRefreshStart() {
                viewBinding.titleBar.setVisibility(View.GONE);
                ImmersionBar.with(activity)
                        .statusBarDarkFont(true)//标题栏设置为浅色
                        .init();
            }

            @Override
            public void onRefreshComplete() {
                viewBinding.titleBar.setVisibility(View.VISIBLE);
                ImmersionBar.with(activity)
                        .statusBarDarkFont(false)//标题栏设置为深色
                        .init();
            }
        });

        viewBinding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //计算标题栏与banner图下边缘的距离
                if (distance == 0) {
                    int[] location = new int[2];
                    viewBinding.titleBar.getLocationOnScreen(location);
                    viewBinding.bannerIv.getLocationOnScreen(location);
                    distance = viewBinding.bannerIv.getBottom() - viewBinding.titleBar.getBottom();
                }
                if (scrollY <= distance) {
                    float alpha = (float) scrollY / distance;
                    ImmersionBar.with(activity)
                            .statusBarDarkFont(false)//标题栏设置为浅色
                            .init();
                    viewBinding.titleBar.setBackgroundAlpha((int) (alpha * 255))
                            .setDividerAlpha((int) (alpha * 255))
                            .setBackIconResource(R.mipmap.back_white)
                            .setTitleColor(WorkUtils.getColor(R.color.white));
                } else {
                    ImmersionBar.with(activity)
                            .statusBarDarkFont(true)//标题栏设置为深色
                            .init();
                    viewBinding.titleBar.setBackgroundAlpha(255)
                            .setDividerAlpha(255)
                            .setBackIconResource(R.mipmap.back)
                            .setTitleColor(WorkUtils.getColor(R.color.colorText));
                }
            }
        });
    }
}
