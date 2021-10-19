package com.sum.sample.application.drawer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ScreenUtils;
import com.sum.sample.databinding.ActivitySlidingDrawerBinding;
import com.sum.sample.databinding.ItemOneBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @author liujiang
 * created at: 2021/9/29 15:19
 * Desc: 侧滑菜单
 */
public class SlidingDrawerActivity extends BaseActivity<ActivitySlidingDrawerBinding> {
    @Override
    protected ActivitySlidingDrawerBinding getViewBinding() {
        return ActivitySlidingDrawerBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(false).titleBar(viewBinding.toolbar).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //修改菜单宽度
        ViewGroup.LayoutParams params = viewBinding.menuLayout.getLayoutParams();
        params.width = ScreenUtils.getScreenWidth() * 2 / 3;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        viewBinding.menuLayout.setLayoutParams(params);

        List<String> list = Arrays.asList("个人中心", "我的订单", "我的任务", "评价管理", "员工管理", "出行检测", "修改密码", "印象墙", "清理缓存", "退出登录");
        viewBinding.menuRv.setAdapter(new BaseAdapter<ItemOneBinding, String>(context, list) {
            @Override
            protected ItemOneBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemOneBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemOneBinding> holder, String item, int position) {
                holder.binding.ivIcon.setImageResource(IconProvider.getIcon(position));
                holder.binding.text.setText(item);
            }
        });



        //1、与Toolbar结合使用
        setSupportActionBar(viewBinding.toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左上角添加小箭头
        }
        viewBinding.toolbar.setTitleTextColor(Color.parseColor("#ffffff"));//设置标题颜色
        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, viewBinding.drawerLayout, viewBinding.toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        viewBinding.drawerLayout.addDrawerListener(mDrawerToggle);


        //2、不关联Toolbar
        viewBinding.btnControl.setOnClickListener(v -> {
            viewBinding.drawerLayout.openDrawer(GravityCompat.START);
        });
    }
}
