package com.sum.sample.base.widget.popup_view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sum.sample.R;
import com.sum.sample.base.App;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.sample.base.widget.PullDownLayout;
import com.sum.sample.databinding.ItemTopViewBinding;
import com.sum.sample.databinding.PopupTopViewBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @author liujiang
 * created at: 2021/9/28 15:48
 * Desc:
 */
public class MenuTopView extends PopupWindow {
    private PopupTopViewBinding binding;
    //选择标记位(当作控件id使用)
    private int flag;
    private PopMenuAdapter adapter;
    private OnMenuItemClickListener onMenuItemClickListener;
    private List<MenuItems> list = new ArrayList<>();
    //需要添加遮罩的framelayout
    private FrameLayout shadeView;
    //需要改变状态的View
    private PullDownLayout pdLayout;
    //已选中了哪个
    private int position = -1;

    public MenuTopView(Activity activity, OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
        init(activity);
    }

    public MenuTopView(Activity activity, int flag, OnMenuItemClickListener onMenuItemClickListener) {
        this.flag = flag;
        this.onMenuItemClickListener = onMenuItemClickListener;
        init(activity);
    }

    /**
     * 初始化
     *
     * @param activity
     */
    private void init(Activity activity) {
        binding = PopupTopViewBinding.inflate(LayoutInflater.from(activity));

        adapter = new PopMenuAdapter(activity, list, onMenuItemClickListener);
        binding.recyclerView.setAdapter(adapter);

        //设置PopupWindow的View
        this.setContentView(binding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.topAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (position != -1)
                    //如果选中了某个条目，改变弹出按钮状态为选中状态
                    pdLayout.setStatus(PullDownLayout.STATUS_SELECTED);
                else
                    //按钮重置为初始化状态
                    pdLayout.setStatus(PullDownLayout.STATUS_INITIAL);
                //设置背景色透明
                shadeView.setForeground(new ColorDrawable(ContextCompat.getColor(App.instance(), R.color.transparency)));
            }
        });
    }

    /**
     * 添加数据
     *
     * @param menuItem
     */
    public void add(MenuItems menuItem) {
        list.add(menuItem);
    }

    /**
     * 显示
     *
     * @param anchor    显示位置锚点
     * @param shadeView 需要添加遮罩的FrameLayout
     */
    public void show(View anchor, FrameLayout shadeView) {
        if (list.size() == 0) {
            ToastUtils.showShort("暂无数据");
            return;
        }
        this.shadeView = shadeView;
        adapter.notifyDataSetChanged();
        //popupwindows弹出位置
        showAsDropDown(anchor);
        //设置前背景半透明作为遮罩
        shadeView.setForeground(new ColorDrawable(ContextCompat.getColor(App.instance(), R.color.translucence)));
        //弹出动画
        shadeAnimation(shadeView, 0f, 1.0f);
    }

    /**
     * 显示
     *
     * @param anchor    显示位置锚点
     * @param shadeView 需要添加遮罩的FrameLayout
     * @param pdLayout  需要改变状态的View
     */
    public void show(View anchor, FrameLayout shadeView, PullDownLayout pdLayout) {
        if (list.size() == 0) {
            ToastUtils.showShort("暂无数据");
            return;
        }
        this.shadeView = shadeView;
        this.pdLayout = pdLayout;
        adapter.notifyDataSetChanged();
        //popupwindows弹出位置
        showAsDropDown(anchor);
        //设置前背景半透明作为遮罩
        shadeView.setForeground(new ColorDrawable(ContextCompat.getColor(App.instance(), R.color.translucence)));
        //弹出动画
        shadeAnimation(shadeView, 0f, 1.0f);
        //改变弹出按钮状态为打开状态
        pdLayout.setStatus(PullDownLayout.STATUS_OPEN);
    }

    /**
     * 获取上次选中的item位置
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * 选中
     */
    public void setPosition(int i) {
        position = i;
        if (null != onMenuItemClickListener && list.size() > i) {
            onMenuItemClickListener.onMenuClick(flag, list.get(i).getId(), list.get(i).getTitle());
            if (null != pdLayout)
                pdLayout.setText(list.get(i).getTitle());
        }
    }

    /**
     * 获取某一条item
     *
     * @return
     */
    public MenuItems getItem() {
        if (position == -1 || position > list.size() - 1)
            return null;
        else
            return list.get(position);
    }

    /**
     * 获取某一条item
     *
     * @return
     */
    public MenuItems getItem(int position) {
        if (position == -1 || position > list.size() - 1)
            return null;
        else
            return list.get(position);
    }

    /**
     * 清空
     */
    public void clear() {
        list.clear();
    }

    /**
     * 清除选中条目
     */
    public void clearSelected() {
        position = -1;
    }


    private class PopMenuAdapter extends BaseAdapter<ItemTopViewBinding, MenuItems> {
        private final OnMenuItemClickListener onMenuItemClickListener;

        public PopMenuAdapter(Context context, List<MenuItems> datas, OnMenuItemClickListener onMenuItemClickListener) {
            super(context, datas);
            this.onMenuItemClickListener = onMenuItemClickListener;
        }

        @Override
        protected ItemTopViewBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
            return ItemTopViewBinding.inflate(layoutInflater, parent, false);
        }

        @Override
        protected void onBind(Context context, ViewHolder<ItemTopViewBinding> holder, MenuItems item, int i) {
            if (!TextUtils.isEmpty(item.getTitle())) {
                holder.binding.tvItem.setText(item.getTitle());
            }
            if (i == position) {
                holder.binding.tvItem.setTextColor(WorkUtils.getColor(R.color.appColor));
            } else {
                holder.binding.tvItem.setTextColor(WorkUtils.getColor(R.color.colorText));
            }
            holder.binding.itemLayout.setOnClickListener(v -> {
                position = i;
                onMenuItemClickListener.onMenuClick(flag, item.getId(), item.getTitle());
                if (null != pdLayout)
                    pdLayout.setText(item.getTitle());
                dismiss();
            });
        }
    }

    //遮罩动画
    public void shadeAnimation(final View view, float from, final float to) {
        AlphaAnimation anim = new AlphaAnimation(from, to);
        anim.setDuration(200);
        view.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                if (to == 1.0f)
//                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                if (to == 0f)
//                    view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
} 
