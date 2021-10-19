package com.sum.sample.application.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ScreenUtils;
import com.sum.sample.databinding.ActivityPopupListBinding;
import com.sum.sample.databinding.ItemPopupListBinding;
import com.sum.sample.databinding.Popup2Binding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;
import java.util.Arrays;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/17 13:46
 * Desc: 列表中弹出方向自适应
 */
public class PopupListActivity extends BaseActivity<ActivityPopupListBinding> {
    @Override
    protected ActivityPopupListBinding getViewBinding() {
        return ActivityPopupListBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        List<String> datas = Arrays.asList(new String[20]);
        viewBinding.recyclerView.setAdapter(new BaseAdapter<ItemPopupListBinding, String>(context, datas) {

            @Override
            protected ItemPopupListBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemPopupListBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemPopupListBinding> holder, String item, int position) {
                holder.binding.moreRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupWindow(v);
                    }
                });
            }
        });
    }

    private void showPopupWindow(View anchorView) {
//        Popup2Binding binding = Popup2Binding.inflate(LayoutInflater.from(context));
//        final View contentView = binding.getRoot();
        //使用viewBindg会导致布局中设置了固定宽度失效，原因暂时不明
        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.popup_2, null);

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //popupwindow设置焦点
        popupWindow.setFocusable(true);
        //设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //点击外面窗口消失
        popupWindow.setOutsideTouchable(true);

        //计算弹出位置
        //获取锚点的坐标
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        //popupwindow宽高
        int contentWidth = contentView.getMeasuredWidth();
        int contentHeight = contentView.getMeasuredHeight();
        //锚点宽高
        int anchorWidth = anchorView.getWidth();
        int anchorHeight = anchorView.getHeight();
        //屏幕的高宽
        int screenWidth = ScreenUtils.getScreenWidth();
        int screenHeight = ScreenUtils.getScreenHeight();
        //判断需要向上弹出还是向下弹出显示
        boolean isNeedShowUp = location[1] + anchorHeight + contentHeight > screenHeight;
        if (isNeedShowUp) {
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - contentWidth, location[1] - contentHeight + anchorHeight / 2);
        } else {
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - contentWidth, location[1] + anchorHeight - anchorHeight / 2);
        }

    }
}
