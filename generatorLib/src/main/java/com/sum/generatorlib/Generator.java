package com.sum.generatorlib;

import com.sum.generatorlib.fragment.MvpFragment;
import com.sum.generatorlib.layout.SimpleFragmentLayout;
import com.sum.generatorlib.fragment.SimpleFragment;
import com.sum.generatorlib.activity.MvpActivity;
import com.sum.generatorlib.mvp.MvpModel;
import com.sum.generatorlib.mvp.MvpPresenter;
import com.sum.generatorlib.mvp.MvpView;
import com.sum.generatorlib.activity.SimpleActivity;
import com.sum.generatorlib.layout.SimpleActivityLayout;

import java.util.Scanner;

public class Generator {
    /**
     * 常规修改区
     */
    //模块名称
    public static final String MODULAR = "ListDialog";
    //注释
    public static final String DESC = "列表弹窗封装";
    //模块目录（aa.bb）
    public static final String PATH = "dialog";
    //1、生成简单的Activity代码;
    //2、生成MVP Activity代码";
    //3、生成简单的Fragment代码";
    //4、生成MVP Fragment代码";
    public static final int result = 1;

    /**
     * 特殊修改区
     */
    //包名
    public static final String BASE_PACKAGE = "com.sum.sample";
    //应用代码路径
    public static final String PATH_APP = "application";
    //Activity目录名称
    public static final String ACTIVITY_PATH = "activity";
    //Activity名字
    public static final String ACTIVITY_NAME = MODULAR + "Activity";
    //ViewBinding命名-Activity
    public static final String BINGING_NAME_ACTIVITY = "Activity" + MODULAR + "Binding";
    //View目录名称
    public static final String VIEW_PATH = "view";
    //View名字
    public static final String VIEW_NAME = MODULAR + "View";
    //Model目录名称
    public static final String MODEL_PATH = "model";
    //Model名字
    public static final String MODEL_NAME = MODULAR + "Model";
    //presenter目录
    public static final String PRESENTER_PATH = "presenter";
    //presenter名字
    public static final String PRESENTER_NAME = MODULAR + "Presenter";
    //fragment目录
    public static final String FRAGMENT_PATH = "fragment";
    //fragment名字
    public static final String FRAGMENT_NAME = MODULAR + "Fragment";
    //ViewBinding命名-Fragment
    public static final String BINGING_NAME_FRAGMENT = "Fragment" + MODULAR + "Binding";

    /**
     * 模板
     */
    //简单的activity_layout
    private static final String simple_activity_layout = "layout_simple.xml.ftl";
    //简单的Activity
    public static final String simple_activity = "SimpleActivity.java.ftl";
    //view
    public static final String mvp_view = "MvpView.java.ftl";
    //model
    public static final String mvp_model = "MvpModel.java.ftl";
    //presenter
    public static final String mvp_presenter = "MvpPresenter.java.ftl";
    //mvp Activity
    public static final String mvp_activity = "MvpActivity.java.ftl";
    //简单的fragment_layout
    public static final String simple_fragment_layout = "layout_fragment.xml.ftl";
    //简单的Fragment
    public static final String simple_fragment = "SimpleFragment.java.ftl";
    //model - fragment
    public static final String mvp_model_fragment = "MvpModel_Fragment.java.ftl";
    //presenter
    public static final String mvp_presenter_fragment = "MvpPresenter_Fragment.java.ftl";
    //mvp Fragment
    public static final String mvp_fragment = "MvpFragment.java.ftl";

    /**
     * 主程序
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        try {
            System.out.println("请输入需要生成的模板编号，点击回车：");
            System.out.println("1、生成简单的Activity代码");
            System.out.println("2、生成MVP Activity代码");
            System.out.println("3、生成简单的Fragment代码");
            System.out.println("4、生成MVP Fragment代码");
//            int result = src.nextInt();

            switch (result) {
                //生成简单的Activity
                case 1:
                    //生成layout
                    new SimpleActivityLayout().generator(simple_activity_layout);
                    //生成activity
                    new SimpleActivity().generator(simple_activity);
                    break;
                //生成MVP代码
                case 2:
                    //生成layout
                    new SimpleActivityLayout().generator(simple_activity_layout);
                    //生成view
                    new MvpView().generator(mvp_view);
                    //生成model
                    new MvpModel().generator(mvp_model);
                    //生成presenter
                    new MvpPresenter().generator(mvp_presenter);
                    //生成activity
                    new MvpActivity().generator(mvp_activity);
                    break;
                case 3:
                    //生成layout
                    new SimpleFragmentLayout().generator(simple_fragment_layout);
                    //生成Fragment
                    new SimpleFragment().generator(simple_fragment);
                    break;
                case 4:
                    //生成layout
                    new SimpleFragmentLayout().generator(simple_fragment_layout);
                    //生成view
                    new MvpView().generator(mvp_view);
                    //生成model
                    new MvpModel().generator(mvp_model_fragment);
                    //生成presenter
                    new MvpPresenter().generator(mvp_presenter_fragment);
                    //生成activity
                    new MvpFragment().generator(mvp_fragment);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            src.close();
        }
    }
}