package com.sum.sample.base.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author liujiang
 * created at: 2021/9/29 16:13
 * Desc: 电话号码输入框
 */
public class PhoneEditText extends androidx.appcompat.widget.AppCompatEditText {
    public PhoneEditText(@NonNull Context context) {
        super(context);
    }

    public PhoneEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置最长13位
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});
        //允许输入电话号码()
//        this.setInputType(InputType.TYPE_CLASS_PHONE); //InputType.TYPE_CLASS_PHONE属性输入空格会报错
        this.setKeyListener(DigitsKeyListener.getInstance("1234567890"));

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
                String str = text.toString().replace(" ", "");
                if (str.length() > 0 && str.length() <= 11) {

                    boolean deleteEmpty = false;

                    String pattern1 = "^(\\d{7}) (/\\d{4})$";//1835723 7923
                    String pattern2 = "^(\\d{3}) (\\d{8})$";//183 57127923

                    Pattern r1 = Pattern.compile(pattern1);
                    Pattern r2 = Pattern.compile(pattern2);

                    Matcher m1 = r1.matcher(text.toString());
                    Matcher m2 = r2.matcher(text.toString());
                    if (m1.find()) {
                        deleteEmpty = true;
                    } else if (m2.find()) {
                        deleteEmpty = true;
                    }

                    if (deleteEmpty && lengthBefore == 1) {
                        start = start - 1;
                        text = text.toString().substring(0, start) + text.toString().substring(start + 1);
                        str = text.toString().replace(" ", "");
                    }

                    String endStr = "";
                    int len = str.length();
                    for (int i = 0; i < len; i++) {
                        endStr += str.charAt(i);
                        if ((i + 2) % 4 == 0 && (i + 1) != len) {
                            endStr += " ";
                        }
                    }

                    if (endStr.endsWith(" ")) {
                        endStr = endStr.substring(0, endStr.lastIndexOf(" "));
                    }

                    removeTextChangedListener(this);
                    setText(endStr);
                    addTextChangedListener(this);

                    //计算光标位置
                    if (lengthAfter == 0) {//删除
                        int selValue = start - (deleteEmpty ? 1 : 0) - (text.length() - endStr.length());
                        setSelection(selValue);
                    } else if (lengthAfter == 1) {//输入一个数字
                        if ((start - 3) % 5 == 0) {
                            start++;
                        }
                        setSelection(start + lengthAfter);
                    } else if (lengthAfter > 1) {//输入多个数字
                        setSelection(getText().toString().length());
                    }
                } else {
                    removeTextChangedListener(this);
                    setText(str);
                    addTextChangedListener(this);
                    setSelection(getText().toString().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setContent(StringBuffer sb) {
        setText(sb.toString());
        //移动光标到最后面
        setSelection(sb.length());
    }

    public String getPhone() {
        return getText().toString().replaceAll(" ", "");
    }
}
