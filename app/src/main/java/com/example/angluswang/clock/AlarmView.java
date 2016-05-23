package com.example.angluswang.clock;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Date;

/**
 * Created by Jeson on 2016/5/23.
 * 闹钟模块
 */
public class AlarmView extends LinearLayout {
    public AlarmView(Context context) {
        super(context);
    }

    public AlarmView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mBtnAddAlarm = (Button) findViewById(R.id.btnAddAlarm);
        mLvAlarmList = (ListView) findViewById(R.id.lvAlarmList);

        mAlarmAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1);
        mLvAlarmList.setAdapter(mAlarmAdapter);

        mAlarmAdapter.add(new AlarmDate(System.currentTimeMillis()));

        mBtnAddAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarm();
            }
        });
    }

    private void addAlarm() {
        //
    }

    private Button mBtnAddAlarm;
    private ListView mLvAlarmList;
    private ArrayAdapter<AlarmDate> mAlarmAdapter;

    private static class AlarmDate {

        private long mTime = 0;
        private String mTimeTable = "";
        private Date mDate;

        public AlarmDate(long time) {
            mTime = time;

            mDate = new Date(mTime);
            mTimeTable = mDate.getHours() + ":" + mDate.getMinutes();
        }

        public long getTime() {
            return mTime;
        }

        public String getTimeTable() {
            return mTimeTable;
        }

        @Override
        public String toString() {
            return getTimeTable();
        }
    }

}
