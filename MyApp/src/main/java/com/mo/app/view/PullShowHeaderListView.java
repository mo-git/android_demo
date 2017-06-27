package com.mo.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by mo on 2017/6/27.
 */

public class PullShowHeaderListView extends ListView {

    private View mHeaderView;
    private int startY = -1;// 滑动起点的y坐标
    private int mHeaderViewHeight;//headerview 的高
    private static final int STATE_PULLING_DOWN = 0;//下拉状态
    private static final int STATE_PULLING_UP = 1;//上拉状态
    private int pulling_state = -1;

    public PullShowHeaderListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PullShowHeaderListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullShowHeaderListView(Context context) {
        this(context, null, 0);
    }


    /**
     * 初始化头布局
     * init headerView in your code, init header Id first
     */
    public void initHeaderView(Context context, int layoutId) {
        mHeaderView = View.inflate(context, layoutId, null);
        this.addHeaderView(mHeaderView, null, false);        //这样可以让HeaderView不可点击
        mHeaderView.measure(0, 0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();//我这里得到的结果是152
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// 隐藏头布局1
        mHeaderView.setVisibility(View.GONE);// 隐藏头布局2
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                startY = -1;// 重置
                startY = (int) ev.getRawY();
                Log.i("---->PullShowHeaderListView", "startY " + startY);
                Log.i("---->PullShowHeaderListView", "getFirstVisiblePosition " + getFirstVisiblePosition());
                if (getFirstVisiblePosition() == 0 && mHeaderView.getPaddingTop() != 0) {//在第一个条目可见的情况下，将headerview置为visible，但是隐藏起来
                    mHeaderView.setVisibility(View.VISIBLE);//mHeaderView.getPaddingTop() 如果等于0表示headerView完全可见（也就是我们已经把它拉下来了，此时不需要进行操作）
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                    Log.i("---->PullShowHeaderListView", " visible,but hide");
                } else if (getFirstVisiblePosition() != 0) {
                    mHeaderView.setVisibility(View.GONE);
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                    Log.i("---->PullShowHeaderListView", "not visible,and hide");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mHeaderView.getVisibility() != View.VISIBLE) { //保证下面代码是在，headerview置为visible的情况下才进行操作，
                    break;
                } else {
                    int endY = (int) ev.getRawY();//得到滑动中的距离
                    int dy = endY - startY;// 移动偏移量
                    Log.i("---->PullShowHeaderListView", "endY -startY" + dy);
                    int padding = 0;
                    if (dy > 10 && mHeaderView.getPaddingTop() != 0 && pulling_state != STATE_PULLING_UP) {  // 当手指向下滑动了一定的距离(并且此时的headerView并不是完全可见的)
                        pulling_state = STATE_PULLING_DOWN;
                        padding = getPadding(dy);//控制padding大小在0到-mHeaderViewHeight之间
                        mHeaderView.setPadding(0, padding, 0, 0);
                        Log.i("---->PullShowHeaderListView", "down padding of the headerView" + padding);
                    }
                    if (dy < -10 && mHeaderView.getPaddingTop() != -mHeaderViewHeight && pulling_state != STATE_PULLING_DOWN) {//当手指向上滑动了一定的距离(并且此时的headerView并不是完全隐藏的)
                        pulling_state = STATE_PULLING_UP;
                        padding = getPadding(dy);//控制padding大小在0到-mHeaderViewHeight之间
                        mHeaderView.setPadding(0, padding, 0, 0);
                        Log.i("---->PullShowHeaderListView", "up padding of the headerView" + padding);
                    }

                }

                break;
            case MotionEvent.ACTION_UP:
                if (mHeaderView.getPaddingTop() <= -mHeaderViewHeight / 2) {
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);// 隐藏
                    mHeaderView.setVisibility(View.GONE);
                    Log.i("---->PullShowHeaderListView", "more than 1/2,hiding");
                } else if (mHeaderView.getPaddingTop() > -mHeaderViewHeight / 2) {
                    mHeaderView.setPadding(0, 0, 0, 0);// 显示
                    mHeaderView.setVisibility(View.VISIBLE);
                    Log.i("---->PullShowHeaderListView", "less than 1/2,show");
                }
                startY = -1;// 重置
                pulling_state = -1;
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
//        return false;
    }

    private int getPadding(int dy) {
        int padding = 0;
        if (pulling_state == STATE_PULLING_DOWN) {//dy为正数，此时必须限定padding范围为-150~0
            padding = dy - mHeaderViewHeight;
            if (padding > 0) {
                padding = 0;
            }
        } else if (pulling_state == STATE_PULLING_UP) {//dy为负数，此时必须限定padding范围为0~-150
            padding = dy;
            if (padding < -mHeaderViewHeight) {
                padding = -mHeaderViewHeight;
            }
        }
        return padding;
    }


    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 拦截事件。确保在item上面的click不消耗事件，从而在上面可以下拉
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}