package com.sum.sample.base.pojo;

import java.util.List;

/**
 * @author liujiang
 * Desc:分页解析对象
 */
public class PagePojo<T> {
    private List<T> records;
    private int total;
    private int size;
    private int current;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

} 
