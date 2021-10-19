package com.sum.sample.base.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.sum.sample.R;
import androidx.appcompat.app.AlertDialog;

/**
 * @author liujiang
 * Desc:加载弹窗
 */
public class LoadingDialog extends AlertDialog {
    private TextView tips_loading_msg;

    private String message = null;

    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(false);
    }

    public LoadingDialog(Context context, int theme, String message) {
        super(context, theme);
        this.message = message;
        this.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_base_loading);
        tips_loading_msg = findViewById(R.id.tips_loading_msg);
        if (null != tips_loading_msg)
            tips_loading_msg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        tips_loading_msg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }
}
