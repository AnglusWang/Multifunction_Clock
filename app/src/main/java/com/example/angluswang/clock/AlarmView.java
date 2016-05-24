package com.example.angluswang.clock;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.Calendar;

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

        readSaveAlarmList();

//        mAlarmAdapter.add(new AlarmDate(System.currentTimeMillis()));

        mBtnAddAlarm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarm();
            }
        });
    }

    private void addAlarm() {
        //添加闹钟
        Calendar c = Calendar.getInstance();

        //使用时间对话框
        new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                Calendar currentTime= Calendar.getInstance();
                if (calendar.getTimeInMillis() <= currentTime.getTimeInMillis()) {
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + 24*60*60*1000);
                }

                mAlarmAdapter.add(new AlarmDate(calendar.getTimeInMillis()));
                saveAlarmList();
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    private void saveAlarmList() {
        SharedPreferences.Editor editor = getContext().
                getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE).edit();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mAlarmAdapter.getCount(); i++) {
            sb.append(mAlarmAdapter.getItem(i).getTime()).append(",");
        }

        String content = sb.toString().substring(0, sb.length()-1);

        editor.putString(KEY_ALARM_LIST, content);

        System.out.println(content);

        editor.commit();
    }

    private void readSaveAlarmList() {
        SharedPreferences sp = getContext().getSharedPreferences(AlarmView.class.getName(), Context.MODE_PRIVATE);
        String content = sp.getString(KEY_ALARM_LIST, null);

        if (content != null) {
            String[] timeStrings = content.split(",");
            for (String string: timeStrings
                 ) {
                mAlarmAdapter.add(new AlarmDate(Long.parseLong(string)));
            }
        }
    }

    private Button mBtnAddAlarm;
    private ListView mLvAlarmList;
    private ArrayAdapter<AlarmDate> mAlarmAdapter;
    private final static String KEY_ALARM_LIST = "alarm list";

    private static class AlarmDate {

        private long mTime = 0;
        private String mTimeTable = "";
        private Calendar mDate;

        public AlarmDate(long time) {
            mTime = time;

            mDate = Calendar.getInstance();
            mDate.setTimeInMillis(mTime);

            mTimeTable = String.format("%d月%d日  %d:%d",
                    mDate.get(Calendar.MONTH) + 1,
                    mDate.get(Calendar.DAY_OF_MONTH),
                    mDate.get(Calendar.HOUR_OF_DAY),
                    mDate.get(Calendar.MINUTE));
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
