package com.sum.sample.base.dialog.list_dialog;


/**
 * @author liujiang
 * created at: 2022/4/29 10:51
 * Desc:
 */
public abstract class TextFormatter<T> implements Formatter<T>{
    @Override
    public int icon(T item) {
        return 0;
    }
}
