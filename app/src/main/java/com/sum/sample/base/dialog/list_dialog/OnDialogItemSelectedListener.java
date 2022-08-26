package com.sum.sample.base.dialog.list_dialog;

/**
 * @author liujiang
 * created at: 2022/8/26 14:10
 * Desc:
 */
public interface OnDialogItemSelectedListener<T> {
    void onSelected(T item, int position);
}

