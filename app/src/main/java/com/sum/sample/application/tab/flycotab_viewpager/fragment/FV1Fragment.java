package com.sum.sample.application.tab.flycotab_viewpager.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.base.utils.L;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.databinding.FragmentFv1Binding;
import com.sum.sample.databinding.ItemOneBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;
import com.sum.simpleadapter.interfaces.SimpleOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author liujiang
 * created at: 2021/9/7 10:58
 * Desc:
 */
public class FV1Fragment extends BaseFragment<FragmentFv1Binding> {
    List<String> datas = new ArrayList<>();
    BaseAdapter<ItemOneBinding, String> adapter;
    @Override
    protected void initImmersionBar() {
        //设置状态栏字体深色
        ImmersionBar.with(this)
                .reset()
                .statusBarColorTransformEnable(false)
                .titleBar(viewBinding.toolbar)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected FragmentFv1Binding getViewBinding() {
        return FragmentFv1Binding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        viewBinding.recyclerView.setNestedScrollingEnabled(false);
        viewBinding.recyclerView.setFocusable(false);
        //插入测试数据
        datas.addAll(getDatas(0));
        adapter = new BaseAdapter<ItemOneBinding, String>(context, datas) {

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
                datas.clear();
                datas.addAll(getDatas(0));
                viewBinding.refreshView.finishRefresh();
                adapter.notifyDataSetChanged();
            }
        });

        viewBinding.refreshView.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                datas.addAll(getDatas(datas.size()));
                viewBinding.refreshView.finishLoadMore();
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnItemClickListener(new SimpleOnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                ToastUtils.showShort(String.valueOf(position));
            }
        });

    }

    private List<String> getDatas(int start) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            list.add("item" + (start + i));
        }
        return list;
    }


}
