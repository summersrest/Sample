package com.sum.sample.base.view.popup_menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sum.sample.R;
import com.sum.sample.base.App;
import com.sum.sample.databinding.ItemPopupMenuBinding;
import com.sum.sample.databinding.PopupMenuBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

/**
 * @author liujiang
 * created at: 2021/9/28 15:26
 * Desc: 弹窗
 */
public class PopMenu extends PopupWindow {
    private PopupMenuBinding binding;
    private List<MenuItem> list = new ArrayList<>();
    private PopMenuAdapter adapter;
    private OnDismissListener onDismissListener;
    private FrameLayout contentLayout;

    public PopMenu(Activity activity, final OnMenuItemClickListener listener) {
        binding = PopupMenuBinding.inflate(LayoutInflater.from(activity));

        adapter = new PopMenuAdapter(activity, list, new OnMenuItemClickListener() {
            @Override
            public void onMenuClick(int actionId) {
                listener.onMenuClick(actionId);
            }
        });
        binding.recyclerView.setAdapter(adapter);

        //设置PopupWindow的View
        this.setContentView(binding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupMenu);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (null != onDismissListener)
                    onDismissListener.onDismiss();
                if (null != contentLayout)
                    //设置背景色透明
                    contentLayout.setForeground(new ColorDrawable(ContextCompat.getColor(App.instance(), R.color.transparency)));
            }
        });
    }

    public void setDismissListener(OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void add(MenuItem menuItem) {
        list.add(menuItem);
    }

    public void show(View view) {
        adapter.notifyDataSetChanged();
        showAsDropDown(view, 0, 0);
    }

    public void show(View view, FrameLayout contentLayout) {
        this.contentLayout = contentLayout;
        adapter.notifyDataSetChanged();
        showAsDropDown(view, 0, 0);
        //设置前背景半透明作为遮罩
        contentLayout.setForeground(new ColorDrawable(ContextCompat.getColor(App.instance(), R.color.translucence)));
    }

    private class PopMenuAdapter extends BaseAdapter<ItemPopupMenuBinding, MenuItem> {
        private final OnMenuItemClickListener onMenuItemClickListener;
        public PopMenuAdapter(Context context, List<MenuItem> datas, OnMenuItemClickListener onMenuItemClickListener) {
            super(context, datas);
            this.onMenuItemClickListener = onMenuItemClickListener;
        }

        @Override
        protected ItemPopupMenuBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
            return ItemPopupMenuBinding.inflate(layoutInflater, parent, false);
        }

        @Override
        protected void onBind(Context context, ViewHolder<ItemPopupMenuBinding> holder, MenuItem item, int position) {
            if (0 != item.getIcon()) {
                holder.binding.icon.setVisibility(View.VISIBLE);
                holder.binding.icon.setImageResource(item.getIcon());
            } else {
                holder.binding.icon.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(item.getTitle())) {
                holder.binding.title.setText(item.getTitle());
            }
            holder.binding.itemLayout.setOnClickListener(v -> {
                onMenuItemClickListener.onMenuClick(item.getActionId());
                dismiss();
            });
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuClick(int actionId);
    }
} 
