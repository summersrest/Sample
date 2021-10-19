package com.sum.sample.base.interfaces;

import android.text.TextWatcher;

/**
 * @author liujiang
 * created at: 2021/9/29 17:18
 * Desc:
 */
public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}