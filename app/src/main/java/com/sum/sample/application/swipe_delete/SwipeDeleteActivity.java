package com.sum.sample.application.swipe_delete;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivitySwipeDeleteBinding;
import com.sum.sample.databinding.ItemSwipeDelBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/28 17:06
 * Desc: 侧滑删除
 */
public class SwipeDeleteActivity extends BaseActivity<ActivitySwipeDeleteBinding> {
    private BaseAdapter<ItemSwipeDelBinding, String> adapter;
    private List<String> datas = new ArrayList<>();
    @Override
    protected ActivitySwipeDeleteBinding getViewBinding() {
        return ActivitySwipeDeleteBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        for (int i = 0; i < 20; i++)
            datas.add(String.valueOf(i));

        adapter = new BaseAdapter<ItemSwipeDelBinding, String>(context, datas) {
            @Override
            protected ItemSwipeDelBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemSwipeDelBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemSwipeDelBinding> holder, String item, int position) {
                holder.binding.delBtn.setOnClickListener(v -> {
                    holder.binding.swipeLayout.quickClose();
                    //从列表中删除数据
                    adapter.notifyItemRemoved(position);
                    if (position <= datas.size()) {
                        adapter.notifyItemRangeChanged(position, datas.size() - position);
                    }
                    datas.remove(position);
                });
            }
        };

        viewBinding.recyclerView.setAdapter(adapter);
    }
}
