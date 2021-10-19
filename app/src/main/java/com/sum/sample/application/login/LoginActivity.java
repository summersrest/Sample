package com.sum.sample.application.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.databinding.ActivityLoginBinding;
import com.sum.sample.databinding.ItemDetailBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;
import com.sum.simpleadapter.interfaces.SimpleOnItemClickListener;

import java.util.Arrays;
import java.util.List;


/**
 * @author liujiang
 * created at: 2021/9/29 15:59
 * Desc: 登录样式
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private final List<String> datas = Arrays.asList("简单的登录样式", "仿数控谷登录页面样式1", "登录灵感1", "登录灵感2", "登录灵感3",
            "登录灵感4", "登录灵感5");
    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
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
                holder.binding.tvNum.setText((position + 1) + "、");
                holder.binding.tvTitle.setText(item);
            }
        };
        viewBinding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new SimpleOnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                switch (position) {
                    //简单的登录样式
                    case 0:
                        ActivityUtils.startActivity(activity, Login1Activity.class);
                        break;
                    //仿数控谷登录页面样式1
                    case 1:
                        ActivityUtils.startActivity(activity, Login2Activity.class);
                        break;
                    //登录灵感1
                    case 2:
                        ActivityUtils.startActivity(activity, Login3Activity.class);
                        break;
                    //登录灵感2
                    case 3:
                        ActivityUtils.startActivity(activity, Login4Activity.class);
                        break;
                    //登录灵感3
                    case 4:
                        ActivityUtils.startActivity(activity, Login5Activity.class);
                        break;
                    //登录灵感4
                    case 5:
                        ActivityUtils.startActivity(activity, Login6Activity.class);
                        break;
                    //登录灵感5
                    case 6:
                        ActivityUtils.startActivity(activity, Login7Activity.class);
                        break;
                }
            }
        });
    }
}
