package com.sum.sample.application.swipe_close;

import android.os.Bundle;
import android.view.View;

import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.databinding.ActivitySwipeCloseBinding;
import com.youngfeng.snake.Snake;
import com.youngfeng.snake.annotations.EnableDragToClose;

/**
 * @author liujiang
 * created at: 2021/9/28 17:18
 * Desc: 滑动返回
 */
@EnableDragToClose
public class SwipeCloseActivity extends BaseActivity<ActivitySwipeCloseBinding> {
    @Override
    protected ActivitySwipeCloseBinding getViewBinding() {
        return ActivitySwipeCloseBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        updateOpenStatus();

        viewBinding.btnOn.setOnClickListener(this);
        viewBinding.btnOff.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        if (v == viewBinding.btnOn) {
            if (!Snake.dragToCloseEnabled(this)) {
                Snake.enableDragToClose(this, true);
                updateOpenStatus();
                ToastUtils.showShort("滑动关闭功能已开启");
            } else {
                ToastUtils.showShort("滑动关闭功能已开启，无需重复调用");
            }
        } else if (v == viewBinding.btnOff) {
            if (Snake.dragToCloseEnabled(this)) {
                Snake.enableDragToClose(this, false);
                updateOpenStatus();
                ToastUtils.showShort("滑动关闭功能已禁用");
            } else {
                ToastUtils.showShort("滑动关闭功能已禁用，无需重复调用");
            }
        }
    }

    private void updateOpenStatus() {
        String status = Snake.dragToCloseEnabled(this) ? "已开启" : "已禁用";
        viewBinding.tvStatus.setText(status);
    }
}
