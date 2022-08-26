package com.sum.sample.base.dialog.list_dialog;

import android.app.Activity;
import android.text.TextUtils;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2022/8/26 14:09
 * Desc:    列表弹窗
 */
public class SimpleListDialog<T> {
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 1;
    private int align;
    private Activity activity;
    private String title;
    private int checkedPosition = -1;
    private List<T> data = new ArrayList<>();
    private boolean isDarkTheme;
    private TextFormatter<T> textFormatter;
    private OnDialogItemSelectedListener<T> onDialogItemSelectedListener;

    public SimpleListDialog(Activity activity) {
        this.activity = activity;
    }

    public SimpleListDialog<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    public SimpleListDialog<T> setData(List<T> data) {
        if (null != data)
            this.data = data;
        return this;
    }

    public SimpleListDialog<T> setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        return this;
    }

    public SimpleListDialog<T> setAlign(int align) {
        this.align = align;
        return this;
    }

    public SimpleListDialog<T> setTextFormatter(TextFormatter<T> textFormatter) {
        this.textFormatter = textFormatter;
        return this;
    }

    public SimpleListDialog<T> setOnDialogItemSelectedListener(OnDialogItemSelectedListener<T> onDialogItemSelectedListener) {
        this.onDialogItemSelectedListener = onDialogItemSelectedListener;
        return this;
    }

    public SimpleListDialog<T> setDarkTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        return this;
    }

    public void show() {
        String[] arr = new String[data.size()];
        int[] iconIds = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            if (null != textFormatter) {
                //文字
                arr[i] = textFormatter.format(data.get(i));
                //图标
                iconIds[i] = textFormatter.icon(data.get(i));
            } else {
                arr[i] = (String) data.get(i);
            }
        }
        //如果每个icon都为0，则默认为没有加图标
        int result = 0;
        for (int i : iconIds) {
            result = result + i;
        }
        if (result == 0) {
            iconIds = null;
        }

        if (align == ALIGN_BOTTOM) {
            new XPopup.Builder(activity)
//                    .maxWidth(600)
                    .maxHeight(800)
                    .isDarkTheme(isDarkTheme)
                    .isDestroyOnDismiss(true)
                    .asBottomList(title, arr, iconIds, checkedPosition, new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            onDialogItemSelectedListener.onSelected(data.get(position), position);
                        }
                    }).show();
        } else if (align == ALIGN_CENTER) {
            new XPopup.Builder(activity)
//                    .maxWidth(600)
                    .maxHeight(800)
                    .isDarkTheme(isDarkTheme)
                    .isDestroyOnDismiss(true)
                    .asCenterList(title, arr, iconIds, checkedPosition, new OnSelectListener() {
                        @Override
                        public void onSelect(int position, String text) {
                            onDialogItemSelectedListener.onSelected(data.get(position), position);
                        }
                    }).show();

        }
    }

}
