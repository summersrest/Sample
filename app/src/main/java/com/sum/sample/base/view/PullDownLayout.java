package com.sum.sample.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sum.sample.R;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.sample.databinding.ViewPullDownBinding;

import androidx.annotation.Nullable;

/**
 * @author liujiang
 * created at: 2021/9/28 15:49
 * Desc:
 */
public class PullDownLayout extends LinearLayout {
    private ViewPullDownBinding binding;
    //初始状态
    public static final int STATUS_INITIAL = 0;
    //选中数据的状态
    public static final int STATUS_SELECTED = 1;
    //下拉窗打开的状态
    public static final int STATUS_OPEN = 2;
    private int icon;
    private String text;
    public PullDownLayout(Context context) {
        super(context);
    }

    public PullDownLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //加载视图的布局
        binding = ViewPullDownBinding.inflate(LayoutInflater.from(context), this, true);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.PullDownLayout);
        icon = obtainStyledAttributes.getResourceId(R.styleable.PullDownLayout_pl_icon, R.mipmap.arr_down_triangle);
        text = obtainStyledAttributes.getString(R.styleable.PullDownLayout_pl_text);
        obtainStyledAttributes.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        binding.tvItem.setText(text);
        binding.ivItem.setImageResource(icon);
    }

    //设置选中状态
    public void setStatus(int status) {
        //初始状态
        if (status == STATUS_INITIAL) {
            binding.tvItem.setTextColor(WorkUtils.getColor(R.color.unSelectColor));
            binding.ivItem.setImageResource(R.mipmap.arr_down_triangle);
        } else if (status == STATUS_SELECTED) {
            //选中状态
            binding.tvItem.setTextColor(WorkUtils.getColor(R.color.selectColor));
            binding.ivItem.setImageResource(R.mipmap.arr_up_triangle_d);
        } else if (status == STATUS_OPEN) {
            //打开状态
            binding.tvItem.setTextColor(WorkUtils.getColor(R.color.selectColor));
            binding.ivItem.setImageResource(R.mipmap.arr_up_triangle);
        }
    }

    public void setText(String text) {
        binding.tvItem.setText(text);
    }
}
