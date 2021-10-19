package com.sum.sample.application.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityPopupDirectionBinding;
import com.sum.sample.databinding.Popup0Binding;

/**
 * @author liujiang
 * created at: 2021/9/17 13:39
 * Desc: PopupWindows弹出方向
 */
public class PopupDirectionActivity extends BaseActivity<ActivityPopupDirectionBinding> {
    private int direction = 0;
    @Override
    protected ActivityPopupDirectionBinding getViewBinding() {
        return ActivityPopupDirectionBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.top_rb) {
                    direction = 0;
                } else if (checkedId == R.id.bottom_rb) {
                    direction = 1;
                } else if (checkedId == R.id.left_rb) {
                    direction = 2;
                } else if (checkedId == R.id.right_rb) {
                    direction = 3;
                }
            }
        });

        viewBinding.button.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        showPopupWindow(viewBinding.button, direction);
    }

    private void showPopupWindow(Button anchorView, int direction) {
        Popup0Binding binding = Popup0Binding.inflate(LayoutInflater.from(context));
        View contentView = binding.getRoot();
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(),
                contentView.getMeasuredHeight(), false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
        popupWindow.setOutsideTouchable(true);//点击外面窗口消失
        // 设置为true之后，PopupWindow内容区域 才可以响应点击事件
        popupWindow.setTouchable(true);
        // true时，点击返回键先消失 PopupWindow
        // 但是设置为true时setOutsideTouchable，setTouchable方法就失效了（点击外部不消失，内容区域也不响应事件）
        // false时PopupWindow不处理返回键
        popupWindow.setFocusable(false);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;   // 这里面拦截不到返回键
            }
        });

        //获取锚点的坐标
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        //popupwindow宽高
        int contentWidth = contentView.getMeasuredWidth();
        int contentHeight = contentView.getMeasuredHeight();
        //锚点宽高
        int anchorWidth = anchorView.getWidth();
        int anchorHeight = anchorView.getHeight();

        switch (direction) {
            //上
            case 0:
                popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - (contentWidth / 2 - anchorWidth / 2), location[1] - contentHeight);
                break;
            //下
            case 1:
                popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - (contentWidth / 2 - anchorWidth / 2), location[1] + anchorHeight);
                break;
            //左
            case 2:
                popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - contentWidth, location[1] - (contentHeight / 2 - anchorHeight / 2));
                break;
            //右
            case 3:
                popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] + anchorWidth, location[1] - (contentHeight / 2 - anchorHeight / 2));
                break;
        }
    }
}
