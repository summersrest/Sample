package com.sum.sample.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author liujiang
 * Desc: 覆盖内部点击事件
 */
public class ClickableLinerLayout extends LinearLayout {
    public ClickableLinerLayout(Context context) {
        super(context);
    }


    public ClickableLinerLayout(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }


    public ClickableLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
} 
