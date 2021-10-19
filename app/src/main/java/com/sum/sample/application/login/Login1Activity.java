package com.sum.sample.application.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.interfaces.SimpleTextWatcher;
import com.sum.sample.base.utils.RxTimerUtil;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.sample.databinding.ActivityLogin1Binding;

import androidx.viewbinding.ViewBinding;

/**
 * @author liujiang
 * created at: 2021/9/29 17:01
 * Desc: 简单的登录页面
 */
public class Login1Activity extends BaseActivity<ActivityLogin1Binding> {
    @Override
    protected ActivityLogin1Binding getViewBinding() {
        return ActivityLogin1Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(this).titleBar(viewBinding.titleBar).init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //密码输入框状态改变
        viewBinding.tbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b)
                    viewBinding.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    viewBinding.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        viewBinding.etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String phone = viewBinding.etPhone.getPhone();
                if (WorkUtils.isPhoneNumber(phone))
                    viewBinding.btnAuth.setEnableCountDown(true);
                else
                    viewBinding.btnAuth.setEnableCountDown(false);
            }
        });

        viewBinding.btnAuth.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        getOauth();
    }

    /**
     * 模拟网络请求失败
     */
    private void getOauth() {
        RxTimerUtil.timer(3000, new RxTimerUtil.IRxNext() {
            @Override
            public void doNext(long number) {
                viewBinding.btnAuth.removeCountDown();
            }
        });
    }
}
