package com.sum.sample.application.detail.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseFragment;
import com.sum.sample.databinding.FragmentD1Binding;
import com.sum.sample.databinding.ItemOneBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/16 15:41
 * Desc:
 */
public class DFragment1 extends BaseFragment<FragmentD1Binding> {
    @Override
    protected FragmentD1Binding getViewBinding() {
        return FragmentD1Binding.inflate(getLayoutInflater());
    }

    @Override
    public void initView() {
        //插入测试数据
        List<String> datas = new ArrayList<>(getDatas(0));
        viewBinding.recyclerView.setAdapter(new BaseAdapter<ItemOneBinding, String>(context, datas) {
            @Override
            protected ItemOneBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemOneBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemOneBinding> holder, String item, int position) {
                holder.binding.text.setText(item);
                holder.binding.ivIcon.setImageResource(IconProvider.getIcon(position));
            }
        });
    }

    private List<String> getDatas(int start) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            list.add("item" + (start + i));
        }
        return list;
    }

}
