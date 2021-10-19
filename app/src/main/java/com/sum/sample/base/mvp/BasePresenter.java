package com.sum.sample.base.mvp;

import android.content.Context;

/**
 * @author liujiang
 * Desc:
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected V listener;
    protected M model;
    protected Context context;

    public abstract M createModel();

    public BasePresenter(V listener, Context context) {
        this.listener = listener;
        this.context = context;
        model = createModel();
    }
}