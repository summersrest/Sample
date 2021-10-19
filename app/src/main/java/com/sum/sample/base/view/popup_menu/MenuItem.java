package com.sum.sample.base.view.popup_menu;

/**
 * @author liujiang
 * created at: 2021/9/28 15:22
 * Desc:
 */
public class MenuItem {
    private int actionId;
    private String title;
    private int icon;

    public MenuItem(int actionId, String title, int icon) {
        this.actionId = actionId;
        this.title = title;
        this.icon = icon;
    }

    public MenuItem(int actionId, String title) {
        this.actionId = actionId;
        this.title = title;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
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
