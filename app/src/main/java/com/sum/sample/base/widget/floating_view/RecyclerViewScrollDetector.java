package com.sum.sample.base.widget.floating_view;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liujiang
 * created at: 2021/9/30 10:48
 * Desc:
 */
public abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
    private int mScrollThreshold;

    abstract void onScrollUp();

    abstract void onScrollDown();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
        if (isSignificantDelta) {
            if (dy > 0) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
    }

    public void setScrollThreshold(int scrollThreshold) {
        mScrollThreshold = scrollThreshold;
    }
}