package com.sum.sample.application.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.sum.sample.R;
import com.sum.sample.application.dialog.dialog.DialogBottom;
import com.sum.sample.application.dialog.dialog.DialogCenter;
import com.sum.sample.application.dialog.dialog.DialogLeft;
import com.sum.sample.application.dialog.dialog.DialogRight;
import com.sum.sample.application.dialog.dialog.DialogTop;
import com.sum.sample.application.dialog.popup.PopupBottom;
import com.sum.sample.application.dialog.popup.PopupCenter;
import com.sum.sample.application.dialog.popup.PopupLeft;
import com.sum.sample.application.dialog.popup.PopupRight;
import com.sum.sample.application.dialog.popup.PopupTop;
import com.sum.sample.application.main.IconProvider;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ActivityUtils;
import com.sum.sample.base.view.popup_bottom.MenuBottomView;
import com.sum.sample.base.view.popup_bottom.MenuItems;
import com.sum.sample.databinding.ActivityDialogBinding;
import com.sum.sample.databinding.ItemMainBinding;
import com.sum.simpleadapter.BaseAdapter;
import com.sum.simpleadapter.base.ViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/17 9:52
 * Desc: 常用弹窗
 */
public class DialogActivity extends BaseActivity<ActivityDialogBinding> {
    private final List<String> datas = Arrays.asList("屏幕中央dialog", "屏幕顶部dialog", "屏幕左边dialog", "屏幕右边dialog", "屏幕底部dialog",
            "屏幕中央PopupWindow", "屏幕顶部PopupWindow", "屏幕左边PopupWindow", "屏幕右边PopupWindow", "屏幕底部PopupWindow",
            "popupWindows各方向弹出", "popupWindows方向与箭头自适应", "popupWindows列表中自适应", "popupWindows封装控件底部弹出",
            "列表弹窗封装");
    //屏幕中央dialog
    DialogCenter dialogCenter;
    //顶部弹出dialog
    DialogTop dialogTop;
    //左边弹出dialog
    DialogLeft dialogLeft;
    //右边弹出dialog
    DialogRight dialogRight;
    //底部弹出dialog
    DialogBottom dialogBottom;
    //屏幕中央PopupWindow
    PopupCenter popupCenter;
    //屏幕顶部PopupWindow
    PopupTop popupTop;
    //屏幕左边PopupWindow
    PopupLeft popupLeft;
    //屏幕右边PopupWindow
    PopupRight popupRight;
    //底部弹出popupWindow
    PopupBottom popupBottom;
    //底部弹出的popupWindow菜单
    MenuBottomView menuBottomView;
    @Override
    protected ActivityDialogBinding getViewBinding() {
        return ActivityDialogBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setStatusBar() {
        ImmersionBar.with(activity)
                .titleBar(viewBinding.toolbar)
                .init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //底部弹出的PopupWindow菜单
        menuBottomView = new MenuBottomView(activity, new MenuBottomView.OnMenuItemClickListener() {
            @Override
            public void onMenuClick(int actionId, String title) {

            }
        });
        menuBottomView.setTitle("车属公司");
        menuBottomView.add(new MenuItems(0, "外部车"));
        menuBottomView.add(new MenuItems(1, "外租车"));
        menuBottomView.add(new MenuItems(2, "一队"));
        menuBottomView.add(new MenuItems(3, "内部车"));

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
                holder.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            //屏幕中央dialog
                            case 0:
                                dialogCenter = new DialogCenter(activity);
                                dialogCenter.show();
                                break;
                            //屏幕顶部dialog
                            case 1:
                                dialogTop = new DialogTop(context, R.style.DialogStyleFull);
                                dialogTop.show();
                                break;
                            //左边弹出dialog
                            case 2:
                                dialogLeft = new DialogLeft(context, R.style.DialogStyleFull);
                                dialogLeft.show();
                                break;
                            //右边弹出dialog
                            case 3:
                                dialogRight = new DialogRight(context, R.style.DialogStyleFull);
                                dialogRight.show();
                                break;
                            //底部弹出dialog
                            case 4:
                                dialogBottom = new DialogBottom(context, R.style.DialogStyleFull);
                                dialogBottom.show();
                                break;
                            //屏幕中央PopupWindow
                            case 5:
                                if (null == popupCenter) {
                                    popupCenter = new PopupCenter(activity);
                                }
                                popupCenter.show(activity, findViewById(R.id.content_layout));
                                break;
                            //屏幕顶部PopupWindow
                            case 6:
                                if (null == popupTop) {
                                    popupTop = new PopupTop(activity);
                                }
                                popupTop.show(activity, findViewById(R.id.content_layout));
                                break;
                            //屏幕左边PopupWindow
                            case 7:
                                if (null == popupLeft) {
                                    popupLeft = new PopupLeft(activity);
                                }
                                popupLeft.show(activity, findViewById(R.id.content_layout));
                                break;
                            //屏幕右边PopupWindow
                            case 8:
                                if (null == popupRight) {
                                    popupRight = new PopupRight(activity);
                                }
                                popupRight.show(activity, findViewById(R.id.content_layout));
                                break;
                            //屏幕底部popupWindow
                            case 9:
                                if (null == popupBottom) {
                                    popupBottom = new PopupBottom(activity);
                                }
                                popupBottom.show(activity, findViewById(R.id.content_layout));
                                break;
                            //popupwindows各方向弹出
                            case 10:
                                ActivityUtils.startActivity(activity, PopupDirectionActivity.class);
                                break;
                            //popupWindows方向与箭头自适应
                            case 11:
                                ActivityUtils.startActivity(activity, PopupArrActivity.class);
                                break;
                            //popupWindows列表中自适应
                            case 12:
                                ActivityUtils.startActivity(activity, PopupListActivity.class);
                                break;
                            //popupWindows封装控件底部弹出
                            case 13:
                                menuBottomView.show(activity, findViewById(R.id.content_layout));
                                break;
                            //列表dialog封装
                            case 14:
                                ActivityUtils.startActivity(activity, ListDialogActivity.class);
                                break;
                        }
                    }
                });
            }
        });
    }
}
