package com.sum.sample.application.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ScreenUtils;
import com.sum.sample.databinding.ActivityPopupArrBinding;

/**
 * @author liujiang
 * created at: 2021/9/17 13:30
 * Desc: popupWindow弹出方向与箭头自适应
 */
public class PopupArrActivity extends BaseActivity<ActivityPopupArrBinding> {
    @Override
    protected ActivityPopupArrBinding getViewBinding() {
        return ActivityPopupArrBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.button0.setOnClickListener(this);
        viewBinding.button1.setOnClickListener(this);
        viewBinding.button2.setOnClickListener(this);
        viewBinding.button3.setOnClickListener(this);
        viewBinding.button4.setOnClickListener(this);
        viewBinding.button5.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        showPopupWindow(v);
    }

    public void showPopupWindow(final View anchorView) {
        final View contentView = LayoutInflater.from(anchorView.getContext()).inflate(R.layout.popup_1, null);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 创建PopupWindow时候指定高宽时showAsDropDown能够自适应
        // 如果设置为wrap_content,showAsDropDown会认为下面空间一直很充足
        // 备注如果PopupWindow里面有ListView,ScrollView时，一定要动态设置PopupWindow的大小
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //想要用showAsDropDown方式来设置弹出位置，必须指定宽高，不可用wrap_content，否则一直弹出在下方
        final PopupWindow popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight(), false);
        popupWindow.setFocusable(true);//popupwindow设置焦点
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
        popupWindow.setOutsideTouchable(true);//点击外面窗口消失

        /**
         * 设置位置方法1，使用showAsDropDown,一定要动态设置PopupWindow的大小,不可用wrap_content
         */
//        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // 自动调整箭头的位置
//                autoAdjustArrowPos0(popupWindow, contentView, anchorView);
//                contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
//        //获取锚点的坐标
//        int[] location = new int[2];
//        anchorView.getLocationOnScreen(location);
//        //popupwindow宽高
//        int contentWidth = contentView.getMeasuredWidth();
//        int contentHeight = contentView.getMeasuredHeight();
//        //锚点宽高
//        int anchorWidth = anchorView.getWidth();
//        int anchorHeight = anchorView.getHeight();
//        // 如果希望showAsDropDown方法能够在下面空间不足时自动在anchorView的上面弹出
//        // 必须在创建PopupWindow的时候指定高度，不能用wrap_content
//        popupWindow.showAsDropDown(anchorView, -(contentWidth / 2 - anchorWidth / 2), 0);

        /**
         * 设置位置方法2，使用showAtLoaction,可以使用wrap_content
         */
        //调整箭头的位置
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //调整箭头的位置
                autoAdjustArrowPos1(contentView, anchorView);
                contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
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
        //屏幕的高宽
        int screenWidth = ScreenUtils.getScreenWidth();
        int screenHeight = ScreenUtils.getScreenHeight();
        //判断需要向上弹出还是向下弹出显示
        boolean isNeedShowUp = location[1] + anchorHeight + contentHeight > screenHeight;
        View upArrow = contentView.findViewById(R.id.up_arrow);
        View downArrow = contentView.findViewById(R.id.down_arrow);
        if (isNeedShowUp) {
            upArrow.setVisibility(View.INVISIBLE);
            downArrow.setVisibility(View.VISIBLE);
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - (contentWidth / 2 - anchorWidth / 2), location[1] - contentHeight);
        } else {
            upArrow.setVisibility(View.VISIBLE);
            downArrow.setVisibility(View.INVISIBLE);
            popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0] - (contentWidth / 2 - anchorWidth / 2), location[1] + anchorHeight);
        }
    }

    private void autoAdjustArrowPos0(PopupWindow popupWindow, View contentView, View anchorView) {
        View upArrow = contentView.findViewById(R.id.up_arrow);
        View downArrow = contentView.findViewById(R.id.down_arrow);

        int pos[] = new int[2];
        contentView.getLocationOnScreen(pos);
        int popLeftPos = pos[0];
        anchorView.getLocationOnScreen(pos);
        int anchorLeftPos = pos[0];
        int arrowLeftMargin = anchorLeftPos + anchorView.getWidth() / 2 - popLeftPos - upArrow.getWidth() / 2;
        upArrow.setVisibility(popupWindow.isAboveAnchor() ? View.INVISIBLE : View.VISIBLE);
        downArrow.setVisibility(popupWindow.isAboveAnchor() ? View.VISIBLE : View.INVISIBLE);

        RelativeLayout.LayoutParams upArrowParams = (RelativeLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;
        RelativeLayout.LayoutParams downArrowParams = (RelativeLayout.LayoutParams) downArrow.getLayoutParams();
        downArrowParams.leftMargin = arrowLeftMargin;
    }

    private void autoAdjustArrowPos1(View contentView, View anchorView) {
        View upArrow = contentView.findViewById(R.id.up_arrow);
        View downArrow = contentView.findViewById(R.id.down_arrow);

        int pos[] = new int[2];
        contentView.getLocationOnScreen(pos);
        int popLeftPos = pos[0];
        anchorView.getLocationOnScreen(pos);
        int anchorLeftPos = pos[0];
        int arrowLeftMargin = anchorLeftPos + anchorView.getWidth() / 2 - popLeftPos - upArrow.getWidth() / 2;

        RelativeLayout.LayoutParams upArrowParams = (RelativeLayout.LayoutParams) upArrow.getLayoutParams();
        upArrowParams.leftMargin = arrowLeftMargin;
        RelativeLayout.LayoutParams downArrowParams = (RelativeLayout.LayoutParams) downArrow.getLayoutParams();
        downArrowParams.leftMargin = arrowLeftMargin;
    }
}
