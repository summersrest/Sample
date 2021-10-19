package com.sum.sample.base.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.sum.sample.R;

import static androidx.core.app.NotificationCompat.VISIBILITY_SECRET;

/**
 * @author  liujiang
 * created  at: 2021/10/14 10:29
 * Desc:    8.0以下控制声音可通过build.setSound()等手动控制。
 *          8.0以上设置Channel优先级,来决定是否跟随系统声音震动，手动设置无效。
 */
public class NotificationUtils {
    //默认渠道ID
    private final String CHANNEL_ID = "default";
    //默认渠道名称
    private final String CHANNEL_NAME = "Default_Channel";
    //默认优先级
    private final int IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;

    private final Context context;

    public NotificationCompat.Builder builder;

    public NotificationManager manager;

    /**
     * 构造函数
     * @param context
     */
    public NotificationUtils(Context context) {
        this.context = context;
        getBuilder(CHANNEL_ID, CHANNEL_NAME, IMPORTANCE);
    }

    /**
     * 构造函数
     * @param context    context
     * @param importance 8.0以上可传入，8.0以下无效
     *                   IMPORTANCE_DEFAULT及以上跟随系统声音或震动，自己设置无效，IMPORTANCE_DEFAULT以下可自己设置。
     */
    public NotificationUtils(Context context, int importance) {
        this.context = context;
        getBuilder(CHANNEL_ID, CHANNEL_NAME, importance);
    }

    /**
     * 构造函数
     * @param context       context
     * @param channelId     渠道id
     * @param channelName   渠道名称
     */
    public NotificationUtils(Context context, String channelId, String channelName) {
        this.context = context;
        getBuilder(channelId, channelName, IMPORTANCE);
    }

    /**
     * 构造函数
     * @param context       context
     * @param channelId     渠道id
     * @param channelName   渠道名称
     * @param importance    8.0以上可传入，8.0以下无效
     *                      IMPORTANCE_DEFAULT及以上跟随系统声音或震动，自己设置无效，IMPORTANCE_DEFAULT以下可自己设置。
     */
    public NotificationUtils(Context context, String channelId, String channelName, int importance) {
        this.context = context;
        getBuilder(channelId, channelName, importance);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationUtils getBuilder(String channelId, String channelName, int importance) {
        if (null == builder) {
            //判断是否为8.0 Android.O
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = createNotificationChannel(channelId, channelName, importance);
                getManager().createNotificationChannel(channel);
                builder = new NotificationCompat.Builder(context, channelId);
            } else {
                builder = new NotificationCompat.Builder(context);
            }
            //Notification.DEFAULT_VIBRATE  添加默认震动效果,需要申请震动权限，设置后自定义参数无效
            //Notification.DEFAULT_SOUND    添加系统默认声音效果，设置此值后，调用setSound()设置自定义声音无效
            //Notification.DEFAULT_LIGHTS   添加默认呼吸灯效果，使用时须与 Notification.FLAG_SHOW_LIGHTS 结合使用，否则无效
            //Notification.DEFAULT_ALL      添加上述三种默认提醒效果
//            builder.setDefaults(NotificationCompat.DEFAULT_ALL);
            builder.setWhen(System.currentTimeMillis());
            builder.setAutoCancel(true);
            //部分机型为了避免假应用伪造高频率应用的推送，设置icon不起作用。
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI);
        }

        return this;
    }

    /**
     * 8.0以上需要创建通知栏渠道channel
     */
    @TargetApi(Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(
                channelId,
                channelName,
                importance);
        channel.canBypassDnd();//是否绕过请勿打扰模式
        channel.enableLights(true);//闪光灯
        channel.setSound(null, null);
        channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
        channel.setLightColor(Color.RED);//闪关灯的灯光颜色
        channel.canShowBadge();//桌面launcher的消息角标
        channel.enableVibration(true);//是否允许震动
        channel.getAudioAttributes();//获取系统通知响铃声音的配置
        channel.getGroup();//获取通知取到组
        channel.setBypassDnd(true);//设置可绕过 请勿打扰模式
        channel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
        channel.shouldShowLights();//是否会有灯光
        return channel;
    }



    /**
     * 通知栏标题
     *
     * @param title
     * @return
     */
    public NotificationUtils setTitle(String title) {
        builder.setContentTitle(title);
        return this;
    }

    /**
     * 通知栏内容
     *
     * @param content
     * @return
     */
    public NotificationUtils setContent(String content) {
        builder.setContentText(content);
        return this;
    }

    /**
     * 收到通知时在顶部显示的文字信息
     *
     * @param ticker
     * @return
     */
    public NotificationUtils setTicker(String ticker) {
        builder.setTicker(ticker);
        return this;
    }

    /**
     * 点击是否自动关闭
     *
     * @param autoCancel
     * @return
     */
    public NotificationUtils setAutoCancel(boolean autoCancel) {
        builder.setAutoCancel(autoCancel);
        return this;
    }

    /**
     * 通知栏设置右下角的小图标，在接收到通知的时候顶部也会显示这个小图标
     *
     * @param icon
     * @return
     */
    public NotificationUtils setSmallIcon(int icon) {
        builder.setSmallIcon(icon);
        return this;
    }

    /**
     * 通知栏左边的大图标-必要参数必须设置
     *
     * @param icon
     * @return
     */
    public NotificationUtils setLargeIcon(int icon) {
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon));
        return this;
    }

    /**
     * 设置是否正在通知（true不可滑动取消， false可滑动取消）
     * @param ongoing
     * @return
     */
    public NotificationUtils setOngoing(boolean ongoing) {
        builder.setOngoing(ongoing);
        return this;
    }

    /**
     * 点击跳转Intent
     *
     * @param pendingIntent
     * @return
     */
    public NotificationUtils setIntent(PendingIntent pendingIntent) {
        builder.setContentIntent(pendingIntent);
        return this;
    }

    /**
     * 设置声音(8.0以下有效，8.0以上需通过Channel优先级控制)
     *
     * @param uri
     * @return
     */
    public NotificationUtils setSound(Uri uri) {
        //调用自己提供的铃声，位于 /res/values/raw 目录下
//        builder.setSound(Uri.parse("android.resource://com.littlejie.notification/" + R.raw.sound));
        builder.setSound(uri);
        return this;
    }

    /**
     * 自定义震动(8.0以下有效，8.0以上需通过Channel优先级控制)
     *
     * @param vibrate
     * @return
     */
    public NotificationUtils setVibrate(long[] vibrate) {
//        builder.setVibrate(new long[]{0, 500, 1000, 1500});
        builder.setVibrate(vibrate);
        return this;
    }

    /**
     * 自定义布局
     * @param remoteViews
     * @return
     */
    public NotificationUtils setContentView(RemoteViews remoteViews){
        builder.setContent(remoteViews);
        return this;
    }

    /**
     * 设置进度条
     *
     * @param max           进度最大值
     * @param progress      当前进度
     * @param indeterminate true不确定， false确定
     * @return
     */
    public NotificationUtils setProgress(int max, int progress, boolean indeterminate) {
        builder.setProgress(max, progress, indeterminate);
        return this;
    }

    /**
     * 设置进度条
     *
     * @param progress 当前进度
     * @return
     */
    public NotificationUtils updateProgress(int notifyId, int progress) {
        builder.setProgress(100, progress, false);
        notify(notifyId);
        return this;
    }


    /**
     * 显示进度条
     *
     * @param notifyId
     */
    public void notify(int notifyId) {
        Notification notify = builder.build();
        getManager().notify(notifyId, notify);
    }

    /**
     * 取消通知栏
     *
     * @param notifyId
     */
    public void cancelById(int notifyId) {
        getManager().cancel(notifyId);
    }

    /**
     * 取消所有通知栏
     */
    public void cancelAll() {
        getManager().cancelAll();
    }
}
