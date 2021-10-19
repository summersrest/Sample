package com.sum.sample.application.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.camera.PictureSelectActivity;
import com.sum.sample.application.coordinator.CoordinatorActivity;
import com.sum.sample.application.detail.DetailActivity;
import com.sum.sample.application.dialog.DialogActivity;
import com.sum.sample.application.drawer.SlidingDrawerActivity;
import com.sum.sample.application.drop_down.DropDownActivity;
import com.sum.sample.application.login.LoginActivity;
import com.sum.sample.application.notification.NotificationActivity;
import com.sum.sample.application.swipe_close.SwipeCloseActivity;
import com.sum.sample.application.swipe_delete.SwipeDeleteActivity;
import com.sum.sample.application.tab.TabLayoutActivity;
import com.sum.sample.application.wheel.WheelActivity;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.databinding.ActivityMainBinding;
import com.sum.sample.databinding.ItemMainBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.Arrays;
import java.util.List;


/**
 * @author liujiang
 * created at: 2021/8/6 10:10
 * Desc: activity
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {
    //标题栏与banner图下边缘的距离
    private int distance;
    private final List<String> datas = Arrays.asList("tabLayout", "详情样式demo", "CoordinatorLayout Demo", "弹窗",
            "图片选择器", "下拉窗", "侧滑删除", "侧滑返回", "通知栏", "Wheel", "加入购物车", "悬浮按钮",
            "登录功能", "规格自适应", "侧滑菜单");

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.titleLayout)
                .statusBarColorTransformEnable(false)//设置状态栏不跟随变色（必须添加）
                .keyboardEnable(false).init();
    }


    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        //禁用recyclerview滑动
        viewBinding.recyclerView.setNestedScrollingEnabled(false);
        //防止recyclerview抢焦点导致滑动到顶部
        viewBinding.recyclerView.setFocusable(false);
        //设置数据
        viewBinding.recyclerView.setAdapter(new BaseAdapter<ItemMainBinding, String>(context, datas) {
                                                @Override
                                                protected ItemMainBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                                                    return ItemMainBinding.inflate(layoutInflater, parent, false);
                                                }

                                                @Override
                                                protected void onBind(Context context, ViewHolder<ItemMainBinding> holder, String item, int position) {
                                                    holder.binding.tvTitle.setText(item);
                                                    holder.binding.ivLeft.setImageResource(IconProvider.getIcon(position));
                                                    holder.binding.ivRight.setImageResource(IconProvider.getFlower(position));
                                                    holder.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            switch (position) {
                                                                //tabLayout
                                                                case 0:
                                                                    ActivityUtils.startActivity(activity, TabLayoutActivity.class);
                                                                    break;
                                                                //详情样式demo
                                                                case 1:
                                                                    ActivityUtils.startActivity(activity, DetailActivity.class);
                                                                    break;
                                                                //CoordinatorLayout Demo
                                                                case 2:
                                                                    ActivityUtils.startActivity(activity, CoordinatorActivity.class);
                                                                    break;
                                                                //弹窗
                                                                case 3:
                                                                    ActivityUtils.startActivity(activity, DialogActivity.class);
                                                                    break;
                                                                //图片选择器
                                                                case 4:
                                                                    ActivityUtils.startActivity(activity, PictureSelectActivity.class);
                                                                    break;
                                                                //下拉窗
                                                                case 5:
                                                                    ActivityUtils.startActivity(activity, DropDownActivity.class);
                                                                    break;
                                                                //侧滑删除
                                                                case 6:
                                                                    ActivityUtils.startActivity(activity, SwipeDeleteActivity.class);
                                                                    break;
                                                                //侧滑返回
                                                                case 7:
                                                                    ActivityUtils.startActivity(activity, SwipeCloseActivity.class);
                                                                    break;
                                                                //通知栏
                                                                case 8:
                                                                    ActivityUtils.startActivity(activity, NotificationActivity.class);
                                                                    break;
                                                                //Wheel
                                                                case 9:
                                                                    ActivityUtils.startActivity(activity, WheelActivity.class);
                                                                    break;
                                                                //加入购物车
                                                                case 10:
//                                                                    ActivityUtils.startActivity(activity, ShopCarActivity.class);
                                                                    break;
                                                                //悬浮按钮
                                                                case 11:
//                                                                    ActivityUtils.startActivity(activity, FloatingActivity.class);
                                                                    break;
                                                                //登录功能
                                                                case 12:
                                                                    ActivityUtils.startActivity(activity, LoginActivity.class);
                                                                    break;
                                                                //规格自适应
                                                                case 13:
//                                                                    ActivityUtils.startActivity(activity, SelfActivity.class);
                                                                    break;
                                                                //侧滑菜单
                                                                case 14:
                                                                    ActivityUtils.startActivity(activity, SlidingDrawerActivity.class);
                                                                    break;
                                                                default:
                                                                    ToastUtils.showShort("无跳转");
                                                                    break;
                                                            }
                                                        }
                                                    });
                                                }
                                            }
        );
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
                    ImmersionBar.with(activity)
                            .statusBarColorTransform(R.color.colorRed)//设置目标颜色
                            .addViewSupportTransformColor(viewBinding.titleLayout)//设置要变化的layout
                            .statusBarAlpha(alpha)//设置透明度
                            .init();
                } else {
                    ImmersionBar.with(activity)
                            .statusBarColorTransform(R.color.colorRed)//设置目标颜色
                            .statusBarAlpha(1)//设置透明度
                            .init();
                }
            }
        });
    }


}