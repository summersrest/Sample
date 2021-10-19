package com.sum.sample.base.interfaces;

import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnMultiListener;

import androidx.annotation.NonNull;

/**
 * @author liujiang
 * created at: 2021/8/6 13:17
 * Desc:下拉刷新状态监控
 */
public abstract class SimpleMultiPurposeLitener implements OnMultiListener {
    @Override
    public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        //开始刷新
        if (oldState == RefreshState.None && newState == RefreshState.PullDownToRefresh) {
            onRefreshStart();
        }
        //取消刷新
        if (oldState == RefreshState.PullDownCanceled && newState == RefreshState.None) {
            onRefreshComplete();
        }
        //结束刷新
        if (oldState == RefreshState.RefreshFinish && newState == RefreshState.None) {
            onRefreshComplete();
        }
        //惯性滑动结束的操作
        if (oldState == RefreshState.PullDownToRefresh && newState == RefreshState.None) {
            onRefreshComplete();
        }
    }

    public void onRefreshStart() {

    }

    public void onRefreshComplete() {

    }
}
