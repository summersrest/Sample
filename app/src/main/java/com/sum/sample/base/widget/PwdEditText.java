package com.sum.sample.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Toast;

import com.sum.sample.R;

/**
 * @author liujiang
 * created at: 2021/9/29 16:38
 * Desc: 密码输入框
 */
public class PwdEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Context context;
    private int min;
    private int max;
    public PwdEditText(Context context) {
        super(context);
        this.context = context;
    }

    public PwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }
    private void init(AttributeSet attrs) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.PwdEditText);
        min = obtainStyledAttributes.getInt(R.styleable.PwdEditText_minLength, 6);
        max = obtainStyledAttributes.getInt(R.styleable.PwdEditText_maxLength, 20);
        obtainStyledAttributes.recycle();
        //输入内容
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //密码格式
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (!focused) {
            String pwdStr = getText().toString().trim();
//            String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{" + min + "," + max + "}$";
            String regex = "^[0-9A-Za-z]{" + min + "," + max + "}$";
            if (!TextUtils.isEmpty(pwdStr) && !pwdStr.matches(regex))
                Toast.makeText(context, "注：请将密码设置为6-20位，并且为数字或字母，请勿设置过于简单。", Toast.LENGTH_SHORT).show();
        }

    }

}
