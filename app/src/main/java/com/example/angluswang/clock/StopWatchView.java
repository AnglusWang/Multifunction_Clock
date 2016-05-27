package com.example.angluswang.clock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jeson on 2016/5/27.
 * 秒表类
 */

public class StopWatchView extends LinearLayout {

    public StopWatchView(Context context) {
        super(context);
    }

    public StopWatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StopWatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mTimeHour = (TextView) findViewById(R.id.timeHour);
        mTimeHour.setText("0");
        mTimeMin = (TextView) findViewById(R.id.timeMin);
        mTimeMin.setText("0");
        mTimeSec = (TextView) findViewById(R.id.timeSec);
        mTimeSec.setText("0");
        mTimeMSec = (TextView) findViewById(R.id.timeMSec);
        mTimeMSec.setText("0");

        mBtnStart = (Button) findViewById(R.id.btnSMStart);
        mBtnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();

                mBtnStart.setVisibility(GONE);
                mBtnPause.setVisibility(VISIBLE);
                mBtnLap.setVisibility(VISIBLE);
            }
        });

        mBtnPause = (Button) findViewById(R.id.btnSMPause);
        mBtnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();

                mBtnPause.setVisibility(GONE);
                mBtnResume.setVisibility(VISIBLE);
                mBtnLap.setVisibility(GONE);
                mBtnReset.setVisibility(VISIBLE);
            }
        });

        mBtnResume = (Button) findViewById(R.id.btnSMResume);
        mBtnResume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();

                mBtnResume.setVisibility(GONE);
                mBtnPause.setVisibility(VISIBLE);
                mBtnReset.setVisibility(GONE);
                mBtnLap.setVisibility(VISIBLE);
            }
        });

        mBtnLap = (Button) findViewById(R.id.btnSMLap);
        mBtnLap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.insert(String.format("%d : %d : %d . %d",
                        mTenMSecs/100/60/60, mTenMSecs/100/60%60, mTenMSecs/100%60, mTenMSecs%100), 0);
            }
        });

        mBtnReset = (Button) findViewById(R.id.btnSMReset);
        mBtnReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                mTenMSecs = 0;
                mAdapter.clear();

                mBtnStart.setVisibility(VISIBLE);
                mBtnPause.setVisibility(GONE);
                mBtnResume.setVisibility(GONE);
                mBtnLap.setVisibility(GONE);
                mBtnReset.setVisibility(GONE);
            }
        });

        mBtnPause.setVisibility(GONE);
        mBtnResume.setVisibility(GONE);
        mBtnLap.setVisibility(GONE);
        mBtnReset.setVisibility(GONE);

        mLvWatchTime = (ListView) findViewById(R.id.lvWatchTime);
        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1);
        mLvWatchTime.setAdapter(mAdapter);

        mShowTimeTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(MSG_WHAT_SHOW_TIME);
            }
        };
        mTimer.schedule(mShowTimeTask, 200, 200);
    }

    private void startTimer() {
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    mTenMSecs ++;
                }
            };

            mTimer.schedule(mTimerTask, 10, 10);
        }
    }

    private void stopTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    private int mTenMSecs = 0;
    private Timer mTimer = new Timer();
    private TimerTask mTimerTask = null;
    private TimerTask mShowTimeTask = null;

    private TextView mTimeHour, mTimeMin, mTimeSec, mTimeMSec;
    private Button mBtnStart, mBtnPause, mBtnResume, mBtnLap, mBtnReset;
    private ListView mLvWatchTime;
    private ArrayAdapter<String> mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_SHOW_TIME:
                    mTimeHour.setText(mTenMSecs/100/60/60 + "");
                    mTimeMin.setText(mTenMSecs/100/60%60 + "");
                    mTimeSec.setText(mTenMSecs/100%60 + "");
                    mTimeMSec.setText(mTenMSecs%100 + "");
                    break;
                default:
                    break;
            }
        }
    };

    private static final int MSG_WHAT_SHOW_TIME = 1;

    public void onDestrory() {
        mTimer.cancel();
    }
}
