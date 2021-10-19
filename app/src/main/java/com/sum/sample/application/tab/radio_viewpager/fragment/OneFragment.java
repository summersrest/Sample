package com.sum.sample.application.tab.radio_viewpager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.base.interfaces.SimpleMultiPurposeLitener;
import com.sum.sample.base.utils.L;
import com.sum.sample.base.utils.RxTimerUtil;
import com.sum.sample.databinding.FragmentOneBinding;
import com.sum.sample.databinding.ItemOneBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;


/**
 * @author liujiang
 * created at: 2021/8/6 11:13
 * Desc:
 */
public class OneFragment extends BaseFragment<FragmentOneBinding> {
    BaseAdapter<ItemOneBinding, String> adapter;
    List<String> datas = new ArrayList<>();
    //标题栏与banner图下边缘的距离
    private int distance;

    @Override
    public void initView() {
        viewBinding.recyclerView.setNestedScrollingEnabled(false);
        viewBinding.recyclerView.setFocusable(false);
        //插入测试数据
        datas.addAll(getDatas(0));
        adapter = new BaseAdapter<ItemOneBinding, String>(activity, datas) {
            @Override
            protected ItemOneBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemOneBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemOneBinding> holder, String item, int position) {
                holder.binding.text.setText(item);
                holder.binding.ivIcon.setImageResource(IconProvider.getIcon(position));
            }
        };
        viewBinding.recyclerView.setAdapter(adapter);

        viewBinding.refreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                RxTimerUtil.timer(2000, new RxTimerUtil.IRxNext() {
                    @Override
                    public void doNext(long number) {
                        datas.clear();
                        datas.addAll(getDatas(0));
                        viewBinding.refreshView.finishRefresh();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        viewBinding.refreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                RxTimerUtil.timer(2000, new RxTimerUtil.IRxNext() {
                    @Override
                    public void doNext(long number) {
                        datas.addAll(getDatas(datas.size()));
                        viewBinding.refreshView.finishLoadMore();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        //下拉刷新时控制标题栏隐藏，防止标题栏覆盖刷新控件
        viewBinding.refreshView.setOnMultiListener(new SimpleMultiPurposeLitener() {
            @Override
            public void onRefreshStart() {
                viewBinding.titleLayout.setVisibility(View.GONE);
            }

            @Override
            public void onRefreshComplete() {
                viewBinding.titleLayout.setVisibility(View.VISIBLE);
            }
        });

        viewBinding.nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //计算标题栏与banner图下边缘的距离
                if (distance == 0) {
                    int[] location = new int[2];
                    viewBinding.titleLayout.getLocationOnScreen(location);
                    viewBinding.ivBanner.getLocationOnScreen(location);
                    distance = viewBinding.ivBanner.getBottom() - viewBinding.titleLayout.getBottom();
                }
                if (scrollY <= distance) {
                    float alpha = (float) scrollY / distance;
                    viewBinding.titleLayout.getBackground().mutate().setAlpha((int) (alpha * 255));
                } else {
                    viewBinding.titleLayout.getBackground().mutate().setAlpha(255);
                }
            }
        });
    }

    @Override
    protected FragmentOneBinding getViewBinding() {
        return FragmentOneBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarColorTransformEnable(false)
                .statusBarDarkFont(false)
                .titleBar(viewBinding.titleLayout)
                .init();
        //titleLayout必须设置background属性，否则空指针异常
        viewBinding.titleLayout.getBackground().mutate().setAlpha(0);
    }


    private List<String> getDatas(int start) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add("item" + (start + i));
        }
        return list;
    }
}