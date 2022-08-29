package com.sum.sample.application.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sum.sample.R;
import com.sum.sample.application.dialog.dialog.DialogBottom;
import com.sum.sample.application.dialog.dialog.DialogCenter;
import com.sum.sample.application.dialog.dialog.DialogLeft;
import com.sum.sample.application.dialog.dialog.DialogRight;
import com.sum.sample.application.dialog.dialog.DialogTop;
import com.sum.sample.application.dialog.pojo.ItemDialogBean;
import com.sum.sample.application.dialog.popup.PopupBottom;
import com.sum.sample.application.dialog.popup.PopupCenter;
import com.sum.sample.application.dialog.popup.PopupLeft;
import com.sum.sample.application.dialog.popup.PopupRight;
import com.sum.sample.application.dialog.popup.PopupTop;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.dialog.list_dialog.OnDialogItemSelectedListener;
import com.sum.sample.base.dialog.list_dialog.SimpleListDialog;
import com.sum.sample.base.dialog.list_dialog.TextFormatter;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.databinding.ActivityListDialogBinding;
import com.sum.sample.databinding.ItemMainBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liujiang
 * created at: 2022-8-26 14:40:42
 * Desc: 列表弹窗封装
 */
public class ListDialogActivity extends BaseActivity<ActivityListDialogBinding> {
    private final List<String> datas = Arrays.asList("中间弹窗列表弹窗", "底部弹出带选择的弹窗", "中间弹出黑色主题弹窗", "中间弹出带icon的弹窗");
    private List<ItemDialogBean> itemList = new ArrayList<>();
    private List<ItemDialogBean> itemListWithIcon = new ArrayList<>();
    @Override
    protected ActivityListDialogBinding getViewBinding() {
        return ActivityListDialogBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //普通的list<String>数组
        List<String> items = Arrays.asList("条目1", "条目2", "条目3", "条目4", "条目5");
        //不带icon的数据源
        itemList.add(new ItemDialogBean("条目1"));
        itemList.add(new ItemDialogBean("条目2"));
        itemList.add(new ItemDialogBean("条目3"));
        itemList.add(new ItemDialogBean("条目4"));
        itemList.add(new ItemDialogBean("条目5"));
        //带icon的数据源
        itemListWithIcon.add(new ItemDialogBean("条目1", IconProvider.getIcon(0)));
        itemListWithIcon.add(new ItemDialogBean("条目2", IconProvider.getIcon(1)));
        itemListWithIcon.add(new ItemDialogBean("条目3", IconProvider.getIcon(2)));
        itemListWithIcon.add(new ItemDialogBean("条目4", IconProvider.getIcon(3)));
        itemListWithIcon.add(new ItemDialogBean("条目5", IconProvider.getIcon(4)));
        //设置数据
        viewBinding.recyclerView.setAdapter(new BaseAdapter<ItemMainBinding, String>(context, datas) {

            @Override
            protected ItemMainBinding getViewBinding(int viewType, LayoutInflater layoutInflater, ViewGroup parent) {
                return ItemMainBinding.inflate(layoutInflater, parent, false);
            }

            @Override
            protected void onBind(Context context, ViewHolder<ItemMainBinding> holder, String item, int position) {
                holder.binding.tvTitle.setText(item);
                holder.binding.ivLeft.setImageResource(IconProvider.getIcon(position));
                holder.binding.ivRight.setImageResource(IconProvider.getFlower(position));
                holder.binding.itemLayout.setOnClickListener(v -> {
                    switch (position) {
                        //中间弹窗列表弹窗
                        case 0:
                            new SimpleListDialog<String>(activity)
                                    .setAlign(SimpleListDialog.ALIGN_CENTER)
                                    .setTitle("标题")
                                    .setData(items)
                                    .setOnDialogItemSelectedListener((item1, position1) -> {
                                        ToastUtils.showShort(item1);
                                    })
                                    .show();
                            break;
                        //底部弹出带选择的弹窗
                        case 1:
                            new SimpleListDialog<ItemDialogBean>(activity)
                                    .setAlign(SimpleListDialog.ALIGN_BOTTOM)
                                    .setTitle("标题")
                                    .setData(itemList)
                                    .setCheckedPosition(2)
                                    .setTextFormatter(new TextFormatter<ItemDialogBean>() {
                                        @Override
                                        public String format(ItemDialogBean item13) {
                                            return item13.getTitle();
                                        }
                                    })
                                    .setOnDialogItemSelectedListener((item12, position12) -> {

                                    }).show();
                            break;
                        //中间弹出黑色主题弹窗
                        case 2:
                            new SimpleListDialog<ItemDialogBean>(activity)
                                    .setAlign(SimpleListDialog.ALIGN_CENTER)
                                    .setTitle("标题")
                                    .setData(itemList)
                                    .setDarkTheme(true)
                                    .setTextFormatter(new TextFormatter<ItemDialogBean>() {
                                        @Override
                                        public String format(ItemDialogBean item13) {
                                            return item13.getTitle();
                                        }
                                    })
                                    .setOnDialogItemSelectedListener((item12, position12) -> {

                                    }).show();
                            break;
                        //中间弹出带icon的弹窗
                        case 3:
                            new SimpleListDialog<ItemDialogBean>(activity)
                                    .setAlign(SimpleListDialog.ALIGN_CENTER)
                                    .setTitle("标题")
                                    .setData(itemListWithIcon)
                                    .setTextFormatter(new TextFormatter<ItemDialogBean>() {
                                        @Override
                                        public String format(ItemDialogBean item13) {
                                            return item13.getTitle();
                                        }

                                        @Override
                                        public int icon(ItemDialogBean item13) {
                                            return item13.getIcon();
                                        }
                                    })
                                    .setOnDialogItemSelectedListener((item12, position12) -> {

                                    }).show();
                            break;

                    }
                });
            }
        });
    }

}
