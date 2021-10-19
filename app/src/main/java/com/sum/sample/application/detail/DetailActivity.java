package com.sum.sample.application.detail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.databinding.ActivityDetailBinding;
import com.sum.sample.databinding.ItemDetailBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;
import com.sum.simpleadapter.interfaces.SimpleOnItemClickListener;

import java.util.Arrays;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/16 14:55
 * Desc:
 */
public class DetailActivity extends BaseActivity<ActivityDetailBinding> {
    private final List<String> datas = Arrays.asList("title+NestScrollView", "CollapsingToolbarLayout+NestScrollView", "CollapsingToolbarLayout+tabLayout实现tab吸顶1",
            "CollapsingToolbarLayout+tabLayout实现tab吸顶2", "上滑隐藏标题栏", "CollapsingToolbarLayout+tabLayout实现tab吸顶标题栏渐变");
    @Override
    protected ActivityDetailBinding getViewBinding() {
        return ActivityDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.toolbar).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BaseAdapter<ItemDetailBinding, String> adapter = new BaseAdapter<ItemDetailBinding, String>(context, datas) {
            @Override
            protected ItemDetailBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemDetailBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemDetailBinding> holder, String item, int position) {
                holder.binding.tvNum.setText((position + 1) + "、 ");
                holder.binding.tvTitle.setText(item);
            }
        };
        viewBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SimpleOnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                switch (position) {
                    //详情样式一(title+NestScrollView,NestScrollView控制透明度)
                    case 0:
                        ActivityUtils.startActivity(activity, Detail1Activity.class);
                        break;
                    //详情样式二(CollapsingToolbarLayout+NestScrollView)
                    case 1:
                        ActivityUtils.startActivity(activity, Detail2Activity.class);
                        break;
                    //详情样式三(CollapsingToolbarLayout+tabLayout)
                    case 2:
                        ActivityUtils.startActivity(activity, Detail3Activity.class);
                        break;
                    //详情样式四(CollapsingToolbarLayout+tabLayout)
                    case 3:
                        ActivityUtils.startActivity(activity, Detail4Activity.class);
                        break;
                    //详情样式五(上滑隐藏标题栏)
                    case 4:
                        ActivityUtils.startActivity(activity, Detail5Activity.class);
                        break;
                    //CollapsingToolbarLayout+tabLayout实现tab吸顶标题栏渐变
                    case 5:
                        ActivityUtils.startActivity(activity, Detail6Activity.class);
                        break;
                }
            }
        });
    }
}
