package com.sum.sample.application.dialog.pojo;

/**
 * @author liujiang
 * created at: 2022/8/26 14:46
 * Desc:
 */
public class ItemDialogBean {
    private String title;

    private int icon;

    public ItemDialogBean(String title) {
        this.title = title;
    }

    public ItemDialogBean(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
