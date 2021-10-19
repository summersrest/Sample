package com.sum.sample.base;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.sum.sample.R;
import com.sum.sample.base.utils.WorkUtils;
import com.sum.titlebar.TitleBar;
import com.youngfeng.snake.Snake;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import androidx.core.content.ContextCompat;
import okhttp3.OkHttpClient;

/**
 * @author liujiang
 * Desc:
 */
public class App extends Application {
    private static App instance;

    public static App instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //网络请求初始化
        initOkHttp();
        //设置标题栏全局属性
        initTitleBar();
        // 对Snake进行初始化
        Snake.init(this);
    }

    /**
     * TitleBar设置默认格式
     */
    private void initTitleBar() {
        TitleBar.getDefaultBuilder().setTitleBarHeight(59)
                .setDividerHeight(1)
                .setDividerColor(WorkUtils.getColor(R.color.colorLine))
                .setTitleBarBackgroundColor(WorkUtils.getColor(R.color.white))
                .setBackIcon(R.mipmap.back);
    }


    /**
     * okhttp初始化
     * @author LiuJiang
     */
    private void initOkHttp() {
        OkGo.getInstance().init(this);
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //自定义拦截器(定时刷新token)
//            RefreshTokenInterceptor tokenInterceptor = new RefreshTokenInterceptor("OkGo");
//            //log打印级别，决定了log显示的详细程度
//            tokenInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//            //log颜色级别，决定了log在控制台显示的颜色
//            tokenInterceptor.setColorLevel(Level.INFO);
//            builder.addInterceptor(tokenInterceptor);

            //logging拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);

            //全局的读取超时时间
            builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //全局的写入超时时间
            builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //全局的连接超时时间
            builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            //使用sp保持cookie，如果cookie不过期，则一直有效
            builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
            OkGo.getInstance().init(this)                       //必须调用初始化
                    .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                    .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                    .setRetryCount(3);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                    .addCommonHeaders(headers)                      //全局公共头
//                    .addCommonParams(params);                       //全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorTitle, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
} 
