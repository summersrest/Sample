package com.sum.sample.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import com.sum.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujiang
 * created at: 2021/9/29 17:49
 * Desc:
 */
public class WaveView extends View {
    //填充颜色
    private int solidColor;
    //画笔
    private Paint mPaint;
    //抗锯齿
    private DrawFilter mDrawFilter;
    //屏宽
    private int screenWidth;
    //屏高
    private int screenHeight;
    //波浪点的列表
    private List<Float> postions;
    private List<Float> postions1;
    private List<Float> postions2;
    //临时列表
    private List<Float> temps = new ArrayList<Float>();
    //循环周期
    private float mCycle;
    //浪高
    private int WAVEHEIGHT = 40;
    //速度
    private int mSpeed = 20;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        solidColor = array.getColor(R.styleable.WaveView_solidColor, Color.parseColor("#80ffffff"));
        mPaint.setColor(solidColor);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取屏幕宽高
        screenWidth = getMeasuredWidth();
        screenHeight = getMeasuredHeight();
        //初始化周期
        mCycle = (float) (1.5 * Math.PI / screenWidth);

        postions = new ArrayList<Float>();
        for (int i = 0; i < screenWidth; i++) {
            //初始化波浪点
            //函数取值在（-1到1之间）
            float position = (float) (Math.sin(mCycle * i) + 0);
            //转化到0-1之间
            position = (position + 1) / 2;
            postions.add(position);
        }



//        第二个
        postions1 = new ArrayList<Float>();
        for (int i = screenWidth / 4; i < screenWidth + screenWidth / 4; i++) {
            //初始化波浪点
            //函数取值在（-1到1之间）
            float position = (float) (Math.sin(mCycle * i) + 0);
            //转化到0-1之间
            position = (position + 1) / 2;
            postions1.add(position);
        }

        //        第三个
        postions2 = new ArrayList<Float>();
        for (int i = screenWidth * 3 / 4; i < screenWidth + screenWidth * 3 / 4; i++) {
            //初始化波浪点
            //函数取值在（-1到1之间）
            float position = (float) (Math.sin(mCycle * i) + 0);
            //转化到0-1之间
            position = (position + 1) / 2;
            postions2.add(position);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw
        canvas.setDrawFilter(mDrawFilter);
        drawWave(canvas);
    }

    public void drawWave(Canvas canvas) {
        //画第一个
        for (int i = 0; i < postions.size(); i++) {
            canvas.drawLine(i, postions.get(i) * getHeight(), i, getHeight(), mPaint);
        }
        //画二个
        for (int i = 0; i < postions1.size(); i++) {
            canvas.drawLine(i, postions1.get(i) * getHeight(), i, getHeight(), mPaint);
        }
        //画第三个
        for (int i = 0; i < postions2.size(); i++) {
            canvas.drawLine(i, postions2.get(i) * getHeight(), i, getHeight(), mPaint);
        }




//        //清空临时数据
//        temps.clear();
//        int nowPosition = 0;
//        Iterator<Float> iterator = postions.iterator();
//        while (iterator.hasNext()) {
//            //交换临时点的位置
//            temps.add(iterator.next());
//            iterator.remove();
//            nowPosition = nowPosition + 1;
//            if (nowPosition == mSpeed) {
//                break;
//            }
//        }
//        postions.addAll(temps);
//        //重绘，会调用OnDraw方法
//        invalidate();
    }
}