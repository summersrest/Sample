package com.sum.sample.base.view.popup_bottom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sum.sample.R;
import com.sum.sample.databinding.ItemPopupMenuBottomBinding;
import com.sum.sample.databinding.PopupMenuBottomBinding;
import com.sum.simple.SimpleRecyclerView;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/17 11:22
 * Desc:
 */
public class MenuBottomView extends PopupWindow {
    private PopupMenuBottomBinding viewBinding;
    private PopMenuAdapter adapter;
    private List<MenuItems> list = new ArrayList<>();
    public MenuBottomView(final Activity activity, OnMenuItemClickListener onMenuItemClickListener) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewBinding = PopupMenuBottomBinding.inflate(inflater);
        viewBinding.tvTitle.setVisibility(View.GONE);
        adapter = new PopMenuAdapter(activity, list, onMenuItemClickListener);
        viewBinding.recyclerView.setAdapter(adapter);
        //设置PopupWindow的View
        this.setContentView(viewBinding.getRoot());
        //设置PopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.anim_menu_bottombar);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f; //0.0-1.0
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    public void setDismissListener(OnDismissListener onDismissListener) {

    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            viewBinding.tvTitle.setVisibility(View.VISIBLE);
            viewBinding.tvTitle.setText(title);
        } else {
            viewBinding.tvTitle.setVisibility(View.GONE);
        }
    }

    public void add(MenuItems menuItem) {
        list.add(menuItem);
    }

    public void clear() {
        list.clear();
    }

    public boolean isClear() {
        return list.size() == 0;
    }

    public void show(Activity activity, View view) {
        adapter.notifyDataSetChanged();
        showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //背景置灰
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    private class PopMenuAdapter extends BaseAdapter<ItemPopupMenuBottomBinding, MenuItems> {
        OnMenuItemClickListener onMenuItemClickListener;

        public PopMenuAdapter(Context context, List<MenuItems> datas, OnMenuItemClickListener onMenuItemClickListener) {
            super(context, datas);
            this.onMenuItemClickListener = onMenuItemClickListener;
        }

        @Override
        protected ItemPopupMenuBottomBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
            return ItemPopupMenuBottomBinding.inflate(layoutInflater, parent, false);
        }

        @Override
        protected void onBind(Context context, ViewHolder<ItemPopupMenuBottomBinding> holder, MenuItems item, int position) {
            if (null != item.getTitle()) {
                holder.binding.itemTv.setText(item.getTitle());
            }
            holder.binding.itemTv.setOnClickListener(v -> {
                onMenuItemClickListener.onMenuClick(item.getId(), item.getTitle());
                dismiss();
            });
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuClick(int actionId, String title);
    }
}
