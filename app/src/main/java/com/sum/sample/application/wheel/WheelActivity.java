package com.sum.sample.application.wheel;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.sum.sample.application.pojo.ProvinceBean;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.DatePickUtils;
import com.sum.sample.base.utils.DateUtils;
import com.sum.sample.databinding.ActivityWheelBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/29 9:10
 * Desc: Wheel
 */
public class WheelActivity extends BaseActivity<ActivityWheelBinding> {
    private OptionsPickerView<String> pvOptions;
    private OptionsPickerView<String> pvNoLinkOptions;
    @Override
    protected ActivityWheelBinding getViewBinding() {
        return ActivityWheelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.tvDate.setText(DateUtils.getCurrentDate());
        //条件选择初始化
        initOptionPicker();
        //不联动多级
        initNoLinkOptionsPicker();

        viewBinding.btnDate.setOnClickListener(this);
        viewBinding.btnCondition.setOnClickListener(this);
        viewBinding.btnMultiple.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        //时间选择
        if (v == viewBinding.btnDate) {
            DatePickUtils.instance().getBuilder(context, DatePickUtils.YEAR_MONTH_DAY_HOUR_MINUTE, date -> {
                viewBinding.tvDate.setText(date);
            })
                    .setDate(viewBinding.tvDate.getText().toString())
                    .setRangDate(DateUtils.getCurrentDate(), null)
                    .setMinuteList(new ArrayList<>(Arrays.asList(0, 10, 20, 30, 40, 50)))
                    .show();
        }
        //条件选择
        else if (v == viewBinding.btnCondition) {
            pvOptions.show();
        }
        //不联动多级
        else if (v == viewBinding.btnMultiple) {
            pvNoLinkOptions.show();
        }
    }

    /**
     * 条件选择
     */
    private void initOptionPicker() {
        //设置数据源
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");
        List<ProvinceBean> provinces = JSON.parseArray(JsonData, ProvinceBean.class);
        //数据源
        //第一级
        final List<String> provinceList = new ArrayList<>();
        //第二级
        final List<List<String>> cityList = new ArrayList<>();
        //第三级
        final List<List<List<String>>> areaList = new ArrayList<>();
        for (ProvinceBean bean : provinces) {
            List<String> pCitys = new ArrayList<>();
            List<List<String>> pAreas = new ArrayList<>();
            for (ProvinceBean.CityBean cityBean : bean.getCity()) {
                pCitys.add(cityBean.getName());
                List<String> cArea = new ArrayList<>();
                if (null != cityBean.getArea() && 0 < cityBean.getArea().size()) {
                    cArea.addAll(cityBean.getArea());
                }
                pAreas.add(cArea);
            }
            provinceList.add(bean.getName());
            cityList.add(pCitys);
            areaList.add(pAreas);
        }

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String province = provinceList.get(options1);
                String city = cityList.get(options1).get(options2);
                String area = areaList.get(options1).get(options2).get(options3);
                viewBinding.tvCondition.setText(String.format("%s%s%s", province, area, city));
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setCancelText("取消")
                .setSubmitText("确定")
                .build();
        pvOptions.setPicker(provinceList, cityList, areaList);
    }

    /**
     * 不联动多级
     */
    private void initNoLinkOptionsPicker() {
        final List<String> list0 = Arrays.asList("变形金刚", "X战警", "复仇者联盟", "蜘蛛侠", "神奇四侠");
        final List<String> list1 = Arrays.asList("鱼香肉丝", "宫保鸡丁", "京酱肉丝");
        final List<String> list2 = Arrays.asList("天龙八部", "欢乐颂", "青云志", "盗墓笔记", "太极宗师", "人民的名义");


        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                viewBinding.tvMultiple.setText(String.format("%s%s%s", list0.get(options1), list1.get(options2), list2.get(options3)));
            }
        })
                .setItemVisibleCount(5)
                // .setSelectOptions(0, 1, 1)
                .build();
        pvNoLinkOptions.setNPicker(list0, list1, list2);
        pvNoLinkOptions.setSelectOptions(0, 1, 1);
    }
}
