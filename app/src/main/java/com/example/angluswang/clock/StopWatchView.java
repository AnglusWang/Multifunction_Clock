package com.example.angluswang.clock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

        mBtnSMStart = (Button) findViewById(R.id.btnSMStart);
        mBtnSMPause = (Button) findViewById(R.id.btnSMPause);
        mBtnSMResume = (Button) findViewById(R.id.btnSMResume);
        mBtnSMLap = (Button) findViewById(R.id.btnSMLap);
        mBtnSMReset = (Button) findViewById(R.id.btnSMReset);

        mLvWatchTime = (ListView) findViewById(R.id.lvWatchTime);
        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1);
        mLvWatchTime.setAdapter(mAdapter);
    }

    private TextView mTimeHour, mTimeMin, mTimeSec, mTimeMSec;
    private Button mBtnSMStart, mBtnSMPause, mBtnSMResume, mBtnSMLap, mBtnSMReset;
    private ListView mLvWatchTime;
    private ArrayAdapter<String> mAdapter;

}
