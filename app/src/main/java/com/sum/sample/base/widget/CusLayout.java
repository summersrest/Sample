package com.sum.sample.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sum.sample.R;

import androidx.annotation.Nullable;

/**
 * @author liujiang
 * created at: 2021/9/22 17:59
 * Desc:
 */
public class CusLayout extends LinearLayout {
    public CusLayout(Context context) {
        super(context);
    }

    public CusLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        initView(context, attrs);

    }

    public CusLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        viewBinding = ViewToolbarBarBinding.inflate(inflater, this, true);
        LayoutInflater.from(context).inflate(R.layout.view_toolbar_bar, this);
    }
}
