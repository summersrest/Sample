package com.sum.sample.base.widget.floating_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sum.sample.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

/**
 * @author liujiang
 * created at: 2021/9/30 10:54
 * Desc: 可拖拽View
 */
public class DragView extends RelativeLayout {
    /**
     * 上一次的位置
     */
    private int mLastX;
    private int mLastY;

    /**
     * 在这个view内部，触摸位置距离view左上角的距离
     */
    private int mDisX;
    private int mDisY;
    /**
     * 按下去的时间，用来区别点击事件
     */
    private long mDownTime;
    /**
     * 抬起来的时间，用来区别点击事件
     */
    private long mUpTime;

    /**
     * 默认判定为CANCEL模式的时长
     */
    private static final int CANCEL_INTERVAL_DEFAULT = 500;

    /**
     * 松手后回到侧边的默认时长
     */
    private static final int GO_TO_BOUNDARY_INTERVAL_DEFAULT = 100;

    /**
     * 根据当前这个事件序列判定的模式
     */
    private @Mode
    int mMode = MODE_NONE;

    /**
     * 父控件
     */
    private ViewGroup parentLayout;

    /**
     * 悬浮按钮距离边界的设定距离
     */
    private int distanceLeft;
    private int distanceRight;
    private int distanceTop;
    private int distanceBottom;

    /**
     * 默认控件活动边界留白
     */
    private static final int DISTANCE_LEFT_DEFAULT = 15;
    private static final int DISTANCE_RIGHT_DEFAULT = 15;
    private static final int DISTANCE_TOP_DEFAULT = 15;
    private static final int DISTANCE_BOTTOM_DEFAULT = 15;

    /**
     * 父控件坐标
     */
    private boolean isParentCoordHasGet;
    private int parentTop;
    private int parentLeft;
    private int parentRight;
    private int parentBottom;

    /**
     * 屏幕宽高
     */
    private int screenWidth;
    private int screenHeight;

    private Context context;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, @Nullable AttributeSet attrs) {
        this.context = context;
        // 可点击
        setClickable(true);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.DragView);
        distanceLeft = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DragView_fb_distance_left, dp2px(DISTANCE_LEFT_DEFAULT));
        distanceRight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DragView_fb_distance_right, dp2px(DISTANCE_RIGHT_DEFAULT));
        distanceTop = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DragView_fb_distance_top, dp2px(DISTANCE_TOP_DEFAULT));
        distanceBottom = obtainStyledAttributes.getDimensionPixelSize(R.styleable.DragView_fb_distance_bottom, dp2px(DISTANCE_BOTTOM_DEFAULT));
        // 回收typedArray
        obtainStyledAttributes.recycle();

        //屏幕宽高
        screenWidth = getScreenParamsInPixel()[0];
        screenHeight = getScreenParamsInPixel()[1];
    }

    /**
     * 设置父控件
     *
     * @param parentLayout 父控件
     */
    public void setParent(ViewGroup parentLayout) {
        this.parentLayout = parentLayout;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获得触摸点的绝对坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 重置这个开始的时间
                mDownTime = System.currentTimeMillis();

                mDisX = (int) event.getX();
                mDisY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                // 重置这个结束的时间
                mUpTime = System.currentTimeMillis();
                // 设置当前的模式
                if (mMode != MODE_MOVE) {
                    if (mUpTime - mDownTime >= CANCEL_INTERVAL_DEFAULT) {
                        mMode = MODE_CANCEL;
                    } else {
                        mMode = MODE_CLICK;
                    }
                }
                // 根据当前的模式设置是否调用点击事件
                if (mMode == MODE_CLICK) {
                    // 点击事件不需要移动
                    performClick();
                } else {
                    if (x > parentLeft + distanceLeft + mDisX && x < parentRight - distanceRight - (getWidth() - mDisX)) {
                        if (event.getRawX() < screenWidth / 2) {
                            //向左移动
                            // 回到最左侧
                            ObjectAnimator animator = ObjectAnimator.ofFloat(this,
                                    "TranslationX",
                                    getTranslationX(),
                                    getTranslationX() + (-1 * (x - mDisX - distanceLeft))
                            );
                            animator.setDuration(GO_TO_BOUNDARY_INTERVAL_DEFAULT);
                            // 监听动画生命周期
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                }

                                @Override
                                public void onAnimationStart(Animator animation) {
                                }
                            });
                            animator.start();
                        } else {
                            //向右移动
                            // 回到最右侧
                            ObjectAnimator animator = ObjectAnimator.ofFloat(this,
                                    "TranslationX",
                                    getTranslationX(),
                                    getTranslationX() + ((parentRight - distanceRight) - (getWidth() - mDisX + x))
                            );
                            // 监听动画生命周期
                            animator.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                }

                                @Override
                                public void onAnimationStart(Animator animation) {
                                }
                            });
                            animator.setDuration(GO_TO_BOUNDARY_INTERVAL_DEFAULT);
                            animator.start();
                        }
                    }

                }
                // 这个事件序列结束，重置当前的模式
                mMode = MODE_NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx;
                int dy;
                //拿到位置差
                dx = x - mLastX;
                dy = y - mLastY;

                //只有移动距离大于50才设置为move模式，防止单击时手指颤动造成移动导致点击事件无法触发
                if (Math.sqrt(dx * dx + dy * dy) > 10) {
                    mMode = MODE_MOVE;
                }

                //设置边界
                //获取父控件坐标
                if (!isParentCoordHasGet) {
                    //父控件存在，获取父控件坐标
                    if (null != parentLayout) {
                        parentTop = parentLayout.getTop();
                        parentLeft = parentLayout.getLeft();
                        parentRight = parentLayout.getRight();
                        parentBottom = parentLayout.getBottom();
                    } else {
                        //父控件不存在，屏幕坐标作为父控件坐标
                        parentTop = 0;
                        parentLeft = 0;
                        parentRight = screenWidth;
                        parentBottom = screenHeight;
                    }
                    isParentCoordHasGet = true;
                }

                //计算距离父控件左侧的距离
                int parentLeftDis = x - parentLeft - mDisX;
                if (parentLeftDis < distanceLeft) {
                    //超出左边界
                    x = parentLeft + distanceLeft + mDisX;
                    dx = x - mLastX;
                }
                //计算距离父控件右侧的距离
                int parentRightDis = parentRight - x - (getWidth() - mDisX);
                if (parentRightDis < distanceRight) {
                    //超出右边界
                    x = parentRight - distanceRight - (getWidth() - mDisX);
                    dx = x - mLastX;
                }
                //计算距离父控件顶部的距离
                int parentTopDis = y - parentTop - mDisY;
                if (parentTopDis < distanceTop) {
                    //超出上边界
                    y = parentTop + distanceTop + mDisY;
                    dy = y - mLastY;
                }
                //计算距离父控件底部的距离
                int parentBottomDis = parentBottom - y - (getHeight() - mDisY);
                if (parentBottomDis < distanceBottom) {
                    y = parentBottom - distanceBottom - (getHeight() - mDisY);
                    dy = y - mLastY;
                }

                // 移动view
                setTranslationX(getTranslationX() + dx);
                setTranslationY(getTranslationY() + dy);
                break;
        }
        // 更新位置
        mLastX = x;
        mLastY = y;
        return true;
    }

    /**
     * 该控件的三种模式,只要触发了ACTION_MOVE就是MOVE模式
     * 没有触发ACTION_MOVE但是从ACTION_DOWN开始超过了500ms就是CANCEL模式
     * 未超过就是CLICK模式
     */
    private static final int MODE_CANCEL = 100;
    private static final int MODE_CLICK = 101;
    private static final int MODE_MOVE = 102;
    private static final int MODE_NONE = 103;

    @IntDef(value = {
            MODE_CANCEL,
            MODE_CLICK,
            MODE_MOVE,
            MODE_NONE
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Mode {
    }

    /**
     * view所处的位置
     * 上下边界暂时不考虑
     */
    private static final int POSITION_LEFT = 1000;
    private static final int POSITION_RIGHT = 1001;
    private static final int POSITION_FLYING = 1002;

    @IntDef(value = {
            POSITION_LEFT,
            POSITION_RIGHT,
            POSITION_FLYING
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Position {
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 屏幕尺寸
     *
     * @return
     */
    private int[] getScreenParamsInPixel() {
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        return new int[]{width, height};
    }
}