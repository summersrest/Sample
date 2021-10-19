package com.sum.sample.application.drop_down;

import android.os.Bundle;
import android.view.View;

import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.ToastUtils;
import com.sum.sample.base.view.PullDownLayout;
import com.sum.sample.base.view.popup_menu.MenuItem;
import com.sum.sample.base.view.popup_menu.PopMenu;
import com.sum.sample.base.view.popup_view.MenuItems;
import com.sum.sample.base.view.popup_view.MenuTopView;
import com.sum.sample.base.view.popup_view.OnMenuItemClickListener;
import com.sum.sample.databinding.ActivityDrapDownBinding;

/**
 * @author liujiang
 * created at: 2021/9/28 16:26
 * Desc: 下拉窗
 */
public class DropDownActivity extends BaseActivity<ActivityDrapDownBinding> {
    private static final int ID_NEW_CUSTOMER = 0;
    private static final int ID_MAP_MODEL = 1;
    private static final int ID_SET = 2;
    //区域弹窗
    MenuTopView districtDropView;
    //价格弹窗
    MenuTopView priceDropView;
    //面积
    MenuTopView areaDropView;
    //厅室
    MenuTopView roomDropView;

    PopMenu menu;
    @Override
    protected ActivityDrapDownBinding getViewBinding() {
        return ActivityDrapDownBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        viewBinding.titleBar.setOnActionClickListener(v -> {
            menu.show(v, viewBinding.contentLayout);
        });

        districtDropView = new MenuTopView(this, 0, new onItemClickListener());
        districtDropView.add(new MenuItems(0, "默认排序"));
        districtDropView.add(new MenuItems(1, "由大到小"));
        districtDropView.add(new MenuItems(2, "由小到大"));

        roomDropView = new MenuTopView(this, 1, new onItemClickListener());
        roomDropView.add(new MenuItems(0, "默认排序"));
        roomDropView.add(new MenuItems(1, "由大到小"));
        roomDropView.add(new MenuItems(2, "由小到大"));

        priceDropView = new MenuTopView(this, 2, new onItemClickListener());
        priceDropView.add(new MenuItems(0, "默认排序"));
        priceDropView.add(new MenuItems(1, "由高到低"));
        priceDropView.add(new MenuItems(2, "由低到高"));

        areaDropView = new MenuTopView(this, 3, new onItemClickListener());
        areaDropView.add(new MenuItems(0, "默认排序"));
        areaDropView.add(new MenuItems(1, "由高到低"));
        areaDropView.add(new MenuItems(2, "由低到高"));

        //右上角下拉窗
        menu = new PopMenu(activity, new PopMenu.OnMenuItemClickListener() {
            @Override
            public void onMenuClick(int actionId) {
                switch (actionId) {
                    case 0:
                        ToastUtils.showShort("添加终端");
                        break;
                    case 1:
                        ToastUtils.showShort("地图模式");
                        break;
                    case 2:
                        ToastUtils.showShort("设置");
                        break;
                }
            }
        });
        menu.add(new MenuItem(ID_NEW_CUSTOMER, "添加终端", R.mipmap.home_dialog_add));
        menu.add(new MenuItem(ID_MAP_MODEL, "地图模式", R.mipmap.home_dialog_map));
        menu.add(new MenuItem(ID_SET, "设置", R.mipmap.home_dialog_set));

        viewBinding.pdlDistrict.setOnClickListener(this);
        viewBinding.pdlRoom.setOnClickListener(this);
        viewBinding.pdlArea.setOnClickListener(this);
        viewBinding.pdlPrice.setOnClickListener(this);
    }


    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        //区域
        if (v == viewBinding.pdlDistrict) {
            districtDropView.show(viewBinding.lineView, viewBinding.shadeView, viewBinding.pdlDistrict);
        }
        //价格
        else if (v == viewBinding.pdlPrice) {
            priceDropView.show(viewBinding.lineView, viewBinding.shadeView, viewBinding.pdlPrice);
        }
        //面积
        else if (v == viewBinding.pdlArea) {
            areaDropView.show(viewBinding.lineView, viewBinding.shadeView, viewBinding.pdlArea);
        }
        //厅室
        else if (v == viewBinding.pdlRoom) {
            roomDropView.show(viewBinding.lineView, viewBinding.shadeView, viewBinding.pdlRoom);
        }
    }

    /**
     * 下拉弹窗选择监听器
     */
    private class onItemClickListener implements OnMenuItemClickListener {

        @Override
        public void onMenuClick(int widgetId, int actionId, String title) {
            switch (widgetId) {
                //区域
                case 0:
                    //
                    viewBinding.pdlPrice.setText("总价");
                    viewBinding.pdlPrice.setStatus(PullDownLayout.STATUS_INITIAL);
                    priceDropView.clearSelected();
                    //
                    viewBinding.pdlArea.setText("面积");
                    viewBinding.pdlArea.setStatus(PullDownLayout.STATUS_INITIAL);
                    areaDropView.clearSelected();
                    //
                    viewBinding.pdlRoom.setText("厅室");
                    viewBinding.pdlRoom.setStatus(PullDownLayout.STATUS_INITIAL);
                    roomDropView.clearSelected();
                    break;
                //厅室
                case 1:
                    //
                    viewBinding.pdlPrice.setText("总价");
                    viewBinding.pdlPrice.setStatus(PullDownLayout.STATUS_INITIAL);
                    priceDropView.clearSelected();
                    //
                    viewBinding.pdlArea.setText("面积");
                    viewBinding.pdlArea.setStatus(PullDownLayout.STATUS_INITIAL);
                    areaDropView.clearSelected();
                    //
                    viewBinding.pdlDistrict.setText("区域");
                    viewBinding.pdlDistrict.setStatus(PullDownLayout.STATUS_INITIAL);
                    districtDropView.clearSelected();
                    break;
                //价格
                case 2:
                    //
                    viewBinding.pdlDistrict.setText("区域");
                    viewBinding.pdlDistrict.setStatus(PullDownLayout.STATUS_INITIAL);
                    districtDropView.clearSelected();
                    //
                    viewBinding.pdlArea.setText("面积");
                    viewBinding.pdlArea.setStatus(PullDownLayout.STATUS_INITIAL);
                    areaDropView.clearSelected();
                    //
                    viewBinding.pdlRoom.setText("厅室");
                    viewBinding.pdlRoom.setStatus(PullDownLayout.STATUS_INITIAL);
                    roomDropView.clearSelected();
                    break;
                //面积
                case 3:
                    //
                    viewBinding.pdlDistrict.setText("区域");
                    viewBinding.pdlDistrict.setStatus(PullDownLayout.STATUS_INITIAL);
                    districtDropView.clearSelected();
                    //
                    viewBinding.pdlPrice.setText("总价");
                    viewBinding.pdlPrice.setStatus(PullDownLayout.STATUS_INITIAL);
                    priceDropView.clearSelected();
                    //
                    viewBinding.pdlRoom.setText("厅室");
                    viewBinding.pdlRoom.setStatus(PullDownLayout.STATUS_INITIAL);
                    roomDropView.clearSelected();
                    break;
            }
        }
    }
}
