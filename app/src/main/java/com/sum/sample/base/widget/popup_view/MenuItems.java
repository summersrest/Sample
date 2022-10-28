package com.sum.sample.base.widget.popup_view;

/**
 * @author liujiang
 * created at: 2021/9/28 15:46
 * Desc:
 */
public class MenuItems {
    private int id;
    private String title;

    public MenuItems(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
} 
