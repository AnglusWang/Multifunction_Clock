package com.example.angluswang.clock;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jeson on 2016/5/24.
 * 计时器类
 */

public class TimerView extends LinearLayout {

    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        etHour = (EditText) findViewById(R.id.etHour);
        etMin = (EditText) findViewById(R.id.etMin);
        etSec = (EditText) findViewById(R.id.etSec);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        etHour.setText("00");
        etHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etHour.setText("59");
                    }else if (value < 0){
                        etHour.setText("0");
                    }
                }

                CheckToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etMin.setText("00");
        etMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etMin.setText("59");
                    }else if (value < 0){
                        etMin.setText("0");
                    }
                }

                CheckToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etSec.setText("00");
        etSec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!TextUtils.isEmpty(s)) {
                    int value = Integer.parseInt(s.toString());

                    if (value > 59) {
                        etSec.setText("59");
                    }else if (value < 0){
                        etSec.setText("0");
                    }
                }

                CheckToEnableBtnStart();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnStart.setVisibility(VISIBLE);
        btnStart.setEnabled(false);
        btnPause.setVisibility(GONE);
        btnResume.setVisibility(GONE);
        btnReset.setVisibility(GONE);
    }

    private void CheckToEnableBtnStart() {
        btnStart.setEnabled(
                (!TextUtils.isEmpty(etHour.getText()) && Integer.parseInt(etHour.getText().toString()) > 0) ||
                (!TextUtils.isEmpty(etMin.getText()) && Integer.parseInt(etMin.getText().toString()) > 0) ||
                (!TextUtils.isEmpty(etSec.getText()) && Integer.parseInt(etSec.getText().toString()) > 0)  );
    }

    private void startTimer() {

        allTimerCount = Integer.parseInt(etHour.getText().toString())*60*60 +
                Integer.parseInt(etMin.getText().toString())*60 +
                Integer.parseInt(etSec.getText().toString());
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    allTimerCount --;

                    if (allTimerCount <= 0) {

                        mhandler.sendEmptyMessage(MSG_WHAT_TIME_IS_UP);
                        stopTimer();
                    }
                }
            };

            mTimer.schedule(mTimerTask, 1000, 1000);

            btnStart.setVisibility(GONE);
            btnPause.setVisibility(VISIBLE);
            btnReset.setVisibility(VISIBLE);
        }
    }

    private void stopTimer() {
        if ( mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_TIME_IS_UP:
                    new AlertDialog.Builder(getContext()).setTitle("Time is up")
                            .setMessage("Time is up").setNegativeButton("Cancel", null).show();
                            break;
                default:
                    break;
            }
        }
    };

    private static final int MSG_WHAT_TIME_IS_UP = 1;
    private int allTimerCount = 0;
    private Timer mTimer = new Timer();
    private TimerTask mTimerTask = null;
    private EditText etHour, etMin, etSec;
    private Button btnStart, btnPause, btnResume, btnReset;

}
