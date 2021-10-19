package com.sum.sample.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.sum.sample.R;
import com.sum.sample.base.dialog.LoadingDialog;
import com.sum.sample.base.pojo.EventMessage;
import com.sum.sample.base.utils.ScreenUtils;
import com.sum.sample.base.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import static com.sum.sample.base.utils.FastDoubleClick.isFastDoubleClick;

/**
 * @author liujiang
 * Desc:基类
 */
public abstract class BaseFragment<V extends ViewBinding> extends Fragment implements View.OnClickListener {
    private LoadingDialog loadDialog = null;

    protected AppCompatActivity activity;

    protected Context context;

    protected V viewBinding;

    protected abstract V getViewBinding();

    public abstract void initView();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
        this.context = context;
    }

    /**
     * 也可以选择反射的方式完成ViewBinding，牺牲一点运行效率，增加编码便捷性
     * 是否使用反射，个人选择
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Type superclass = getClass().getGenericSuperclass();
//        Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
//        try {
//            Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class,ViewGroup.class,boolean.class);
//            viewBinding = (V) method.invoke(null, getLayoutInflater(),container,false);
//        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
//            e.printStackTrace();
//        }
        viewBinding = getViewBinding();
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        initImmersionBar();
    }

    protected void initImmersionBar() {
//        ImmersionBar.with(this).keyboardEnable(true).init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventMessage event) {
        onEvent(event);
    }

    /**
     * 复写此方法接收eventBus消息
     * @param event
     */
    protected void onEvent(EventMessage event) {

    }

    /**
     * 弹出进度条弹窗
     *
     * @param msg
     */
    protected void showDialog(String msg) {
        if (!TextUtils.isEmpty(msg))
            setDialog(msg);
        else
            setDialog(getResources().getString(R.string.loading));
    }

    /**
     * 隐藏进度条弹窗
     *
     * @param msg
     */
    protected void hintDialog(String msg) {
        if (!TextUtils.isEmpty(msg))
            ToastUtils.showShort(msg);
        // 取消加载对话框
        if (loadDialog != null) {
            loadDialog.cancel();
            loadDialog = null;
        }
    }

    /**
     * 设置进度条
     */
    private void setDialog(String text) {
        // 正在加载对话框
        if (loadDialog == null) {
            loadDialog = new LoadingDialog(getActivity(), text);
        } else {
            loadDialog.setText(text);
        }
        loadDialog.getWindow().setDimAmount(0.4f);
        loadDialog.show();
        WindowManager.LayoutParams params = loadDialog.getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth() / 2;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        loadDialog.getWindow().setAttributes(params);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

    }

    @Override
    public void onClick(View v) {
        if (isFastDoubleClick()) {
            return;
        }
        onClickEvent(v);
    }

    /**
     * 点击事件
     */
    protected void onClickEvent(View v) {

    }
}
