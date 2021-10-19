package com.sum.sample.application.notification;

import android.os.Bundle;

import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivitySecondeBinding;

/**
 * @author liujiang
 * created at: 2021/10/19 10:26
 * Desc:
 */
public class SecondActivity extends BaseActivity<ActivitySecondeBinding> {
    @Override
    protected ActivitySecondeBinding getViewBinding() {
        return ActivitySecondeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        int type = getIntent().getIntExtra("key", 1);

        switch (type) {
            case 1:
                viewBinding.textView.setText("我是第二个页面");
                break;
            case 2:
                viewBinding.textView.setText("上一首");
                break;
            case 3:
                viewBinding.textView.setText("下一首");
                break;
            case 4:
                viewBinding.textView.setText("播放暂停");
                break;
            case 5:
                viewBinding.textView.setText("点击通知栏的根容器时要执行的意图");
                break;
        }
    }
}
