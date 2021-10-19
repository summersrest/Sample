package com.sum.sample.application.tab.radio_frame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.tab.radio_frame.fragment.AFragment;
import com.sum.sample.application.tab.radio_frame.fragment.BFragment;
import com.sum.sample.application.tab.radio_frame.fragment.CFragment;
import com.sum.sample.application.tab.radio_frame.fragment.DFragment;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.databinding.ActivityRadioFrameBinding;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author liujiang
 * created at: 2021/9/16 13:14
 * Desc: RadioGroup与FrameLayout实现tab
 */
public class RadioAndFrameActivity extends BaseActivity<ActivityRadioFrameBinding> {
    AFragment aFragment;
    BFragment bFragment;
    CFragment cFragment;
    DFragment dFragment;
    //当前fragment的index
    private int currentTabIndex;
    private Fragment[] fragments;
    //防止后台长期运行cash
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        ImmersionBar.with(this).init();
    }

    @Override
    protected ActivityRadioFrameBinding getViewBinding() {
        return ActivityRadioFrameBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //设置默认的fragment;
        setDefaultFragment();
        //底部tab监听
        viewBinding.mainRadio.setOnCheckedChangeListener(new OnCheckedChangeEven());
    }

    /**
     * fragment初始化
     */
    private void setDefaultFragment() {
        List<Fragment> list = getSupportFragmentManager().getFragments();
        if (list.size() > 0) {
            for (Fragment fragment : list) {
                if (null != fragment) {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }
        }
        aFragment = new AFragment();
        bFragment = new BFragment();
        cFragment = new CFragment();
        dFragment = new DFragment();
        fragments = new Fragment[]{aFragment, bFragment, cFragment, dFragment};
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction().add(R.id.id_content, aFragment)
                .add(R.id.id_content, bFragment).hide(bFragment).show(aFragment)
                .commit();
    }

    /**
     * 底部tab监听
     */
    private class OnCheckedChangeEven implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            int index = 0;
            if (checkedId == R.id.main_home_page) {
                index = 0;
            } else if (checkedId == R.id.main_order_page) {
                index = 1;
            } else if (checkedId == R.id.main_my_page) {
                index = 2;
            } else if (checkedId == R.id.main_my_page1) {
                index = 3;
            }
            setSelected(index);
        }
    }

    /**
     * 设置radiobutton选中item
     * @param index
     */
    private void setSelected(int index) {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.id_content, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        // 事务提交
        transaction.commit();
        currentTabIndex = index;
    }
}
