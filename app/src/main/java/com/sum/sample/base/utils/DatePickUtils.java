package com.sum.sample.base.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.sum.sample.R;
import java.util.Calendar;
import java.util.List;
import androidx.core.content.ContextCompat;


/**
 * @author liujiang
 * created at: 2021/9/29 9:47
 * Desc: 时间选择器
 */
public class DatePickUtils {
    private TimePickerBuilder builder;
    public static final int ALL = 0;
    public static final int YEAR_MONTH = 1;
    public static final int YEAR_MONTH_DAY = 2;
    public static final int YEAR_MONTH_DAY_HOUR = 3;
    public static final int YEAR_MONTH_DAY_HOUR_MINUTE = 4;

    public DatePickUtils() {
    }

    public static volatile DatePickUtils datePickUtils;

    public static DatePickUtils instance() {
        if (datePickUtils == null) {
            synchronized (DatePickUtils.class) {
                if (datePickUtils == null) {
                    datePickUtils = new DatePickUtils();
                }
            }
        }
        return datePickUtils;
    }

    public DatePickUtils getBuilder(Context context, OnDateSelectListener onDateSelectListener) {
        getBuilder(context, ALL, onDateSelectListener);
        return this;
    }

    public DatePickUtils getBuilder(Context context, int type, OnDateSelectListener onDateSelectListener) {
        boolean[] typeArr;
        switch (type) {
            case YEAR_MONTH:
                typeArr = new boolean[]{true, true, false, false, false, false};
                break;
            case YEAR_MONTH_DAY:
                typeArr = new boolean[]{true, true, true, false, false, false};
                break;
            case YEAR_MONTH_DAY_HOUR:
                typeArr = new boolean[]{true, true, true, true, false, false};
                break;
            case YEAR_MONTH_DAY_HOUR_MINUTE:
                typeArr = new boolean[]{true, true, true, true, true, false};
                break;
            default:
                typeArr = new boolean[]{true, true, true, true, true, true};
                break;
        }
        builder = new TimePickerBuilder(context, (date, v) -> {
            String dateStr;
            switch (type) {
                case YEAR_MONTH:
                    dateStr = DateUtils.date2StringYM(date);
                    break;
                case YEAR_MONTH_DAY:
                    dateStr = DateUtils.date2StringYMD(date);
                    break;
                case YEAR_MONTH_DAY_HOUR:
                    dateStr = DateUtils.date2StringYMDH(date);
                    break;
                case YEAR_MONTH_DAY_HOUR_MINUTE:
                    dateStr = DateUtils.date2StringYMDHM(date);
                    break;
                default:
                    dateStr = DateUtils.date2String(date);
                    break;
            }
            onDateSelectListener.onDateSelect(dateStr);
        })
                .isDialog(true)
                .setType(typeArr)
                .setDate(Calendar.getInstance())
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("时间选择")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setLineSpacingMultiplier(2.2f)
                .setTextColorCenter(ContextCompat.getColor(context, R.color.colorTitle))//设置选中项的颜色
                .setTitleColor(ContextCompat.getColor(context, R.color.white))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.white))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.white))//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(context, R.color.colorTitle))//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(context, R.color.white))//滚轮背景颜色 Night mode
                .setTextXOffset(0, 0, 0, 0, 0, 0)//设置X轴偏移量，形成弧度
                .setLabel("年", "月", "日", "时", "分", "秒");
        return this;
    }


    /**
     * 设置当前时间
     *
     * @param date
     * @return
     */
    public DatePickUtils setDate(String date) {
        builder.setDate(DateUtils.string2Calendar(date));
        return this;
    }

    /**
     * 设置时间区间
     *
     * @param minDate
     * @return
     */
    public DatePickUtils setRangDate(String minDate, String maxDate) {
        Calendar start;
        if (!TextUtils.isEmpty(minDate)) {
            start = DateUtils.string2Calendar(minDate);
        } else {
            start = Calendar.getInstance();
        }
        Calendar end;
        if (!TextUtils.isEmpty(maxDate)) {
            end = DateUtils.string2Calendar(maxDate);
        } else {
            end = DateUtils.string2Calendar("2099-01-01");
        }
        builder.setRangDate(start, end);
        return this;
    }

    /**
     * 自定义时
     *
     * @param hourList 范围：0-23
     * @return
     */
    public DatePickUtils setHourList(List<Integer> hourList) {
        builder.setHourList(hourList);
        return this;
    }

    /**
     * 自定义“分”
     *
     * @param minuteList 范围：0-59
     * @return
     */
    public DatePickUtils setMinuteList(List<Integer> minuteList) {
        builder.setMinuteList(minuteList);
        return this;
    }

    /**
     * 自定义“秒”
     *
     * @param secondList 范围：0-59
     * @return
     */
    public DatePickUtils setSecondList(List<Integer> secondList) {
//        builder.setSecondList(new ArrayList<>(Arrays.asList(9, 10, 11, 12, 13, 14, 15, 16, 17, 18)));
        builder.setSecondList(secondList);
        return this;
    }

    /**
     * 弹出
     */
    public void show() {
        TimePickerView datePicker = builder.build();
        Dialog mDialog = datePicker.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            datePicker.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
        datePicker.show();
    }

    public interface OnDateSelectListener {
        void onDateSelect(String date);
    }
}
