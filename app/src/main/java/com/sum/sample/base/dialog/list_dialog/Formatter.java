package com.sum.sample.base.dialog.list_dialog;


/**
 * @author liujiang
 * created at: 2022/4/29 10:51
 * Desc:
 */
public interface Formatter<T> {
    String format(T item);

    int icon(T item);
}
