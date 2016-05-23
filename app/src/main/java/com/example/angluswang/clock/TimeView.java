package com.example.angluswang.clock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Jeson on 2016/5/23.
 * 自定义时间控件
 */
public class TimeView extends LinearLayout {

    public TimeView(Context context) {
        super(context);
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTvTime = (TextView) findViewById(R.id.tvTime);
        mTvTime.setText("Hello");


    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            timeHandler.sendEmptyMessage(0);
        }else {
            //窗口不显示则不移除消息
            timeHandler.removeMessages(0);
        }
    }

    //刷新当前时间
    private void refreshTime() {
        Calendar c = Calendar.getInstance();

        mTvTime.setText(String.format("%d:%d:%d",
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND)));
    }

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            refreshTime();

            if (getVisibility() == View.VISIBLE) {
                sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    private TextView mTvTime;
}
