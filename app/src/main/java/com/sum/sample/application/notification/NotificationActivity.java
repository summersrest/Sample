package com.sum.sample.application.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.sum.sample.R;
import com.sum.sample.base.activity.BaseActivity;
import com.sum.sample.base.utils.NotificationUtils;
import com.sum.sample.base.utils.RxTimerUtil;
import com.sum.sample.databinding.ActivityNotifycationBinding;

/**
 * @author liujiang
 * created at: 2021/10/19 10:23
 * Desc: 通知栏
 */
public class NotificationActivity extends BaseActivity<ActivityNotifycationBinding> {
    private Context context;
    @Override
    protected ActivityNotifycationBinding getViewBinding() {
        return ActivityNotifycationBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        context = getApplicationContext();

        viewBinding.button1.setOnClickListener(this);
        viewBinding.button2.setOnClickListener(this);
        viewBinding.button3.setOnClickListener(this);
        viewBinding.button4.setOnClickListener(this);
        viewBinding.button5.setOnClickListener(this);
        viewBinding.button6.setOnClickListener(this);
        viewBinding.button7.setOnClickListener(this);
    }

    @Override
    protected void onClickEvent(View v) {
        super.onClickEvent(v);
        //简单的通知
        if (v == viewBinding.button1) {
            simple();
        }
        //简单的通知，静音
        else if (v == viewBinding.button2) {
            simpleCanCancel();
        }
        //点击跳转activity
        else if (v == viewBinding.button3) {
            startNewActivity();
        }
        //无法手动取消的通知
        else if (v == viewBinding.button4) {
            onGoing();
        }
        //自定义布局
        else if (v == viewBinding.button5) {
            customerView();
        }
        //带进度条的布局
        else if (v == viewBinding.button6) {
            progressNotification();
        }
        //取消所有通知
        else if (v == viewBinding.button7) {
            cancelAll();
        }
    }

    /**
     * 简单的通知
     */
    private void simple() {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.setTitle("我是标题")
                .setContent("简单的通知")
                .setTicker("弹出通知")
                //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(R.mipmap.ic_launcher)
                .notify(1);

    }

    /**
     * 简单的通知，静音
     */
    private void simpleCanCancel() {
        //8.0以上需将优先级设置为"IMPORTANCE_LOW"及以下，否则跟随系统声音，无法取消
        NotificationUtils notificationUtils = new NotificationUtils(context, NotificationManager.IMPORTANCE_LOW);
        notificationUtils.setTitle("我是标题")
                .setContent("静音的通知")
                .setTicker("弹出通知")
                //8.0以下使用此方法静音
                .setSound(null)
                //8.0以下使用此方法禁止震动
                .setVibrate(null)
                //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(R.mipmap.ic_launcher)
                .notify(2);

    }

    /**
     * 点击跳转Activity
     */
    private void startNewActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("key", 1);
        PendingIntent resultIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.setTitle("我是标题")
                .setContent("点击跳转Activity")
                .setTicker("弹出通知")
                .setAutoCancel(true)
                //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(R.mipmap.ic_launcher)
                .setIntent(resultIntent)
                .notify(3);
    }

    /**
     * 无法滑动取消的通知
     */
    private void onGoing() {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.setTitle("我是标题")
                .setContent("我无法滑动取消和清理掉")
                .setTicker("弹出通知")
                //无法滑动取消
                .setOngoing(true)
                //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(R.mipmap.ic_launcher)
                .notify(4);
    }

    /**
     * 自定义弹出框布局
     */
    private void customerView() {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.setOngoing(true)
                //必要参数必须设置
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentView(getRemoteViews())
                .notify(5);
    }

    private RemoteViews getRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_mobile_play);
        // 设置 点击通知栏的上一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_pre, getActivityPendingIntent(2));
        // 设置 点击通知栏的下一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_next, getActivityPendingIntent(3));
        // 设置 点击通知栏的播放暂停按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_start, getActivityPendingIntent(4));
        // 设置 点击通知栏的根容器时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.ll_root, getActivityPendingIntent(5));
        remoteViews.setTextViewText(R.id.tv_title, "标题");     // 设置通知栏上标题
        remoteViews.setTextViewText(R.id.tv_artist, "艺术家");   // 设置通知栏上艺术家
        return remoteViews;
    }

    /**
     * 带进度图的通知栏
     */
    private void progressNotification() {
        cancelAll();
        NotificationUtils notificationUtils = new NotificationUtils(context, NotificationManager.IMPORTANCE_LOW);
        notificationUtils.setTitle("应用名称")
                .setContent("下载中．．．")
                .setTicker("正在下载")
                .setOngoing(true)
                .setSound(null)
                .setVibrate(null)
                //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(R.mipmap.ic_launcher)
                .setProgress(100, 0, false)
                .notify(6);

        RxTimerUtil.interval(100, number -> {
            if (number < 100) {
                notificationUtils.updateProgress(6, (int) number);
                notificationUtils.setContent("下载进度:" + number + "%");
            } else {
                RxTimerUtil.cancel();
                notificationUtils.cancelById(6);
            }

        });
    }

    /**
     * 取消所有
     */
    public void cancelAll() {
        NotificationUtils notificationUtils = new NotificationUtils(context);
        notificationUtils.cancelAll();
    }

    private PendingIntent getActivityPendingIntent(int what) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        intent.putExtra("key", what);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, what, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxTimerUtil.cancel();
    }
}
