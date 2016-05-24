package com.example.angluswang.clock;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Jeson on 2016/5/24.
 * 闹钟界面
 */

public class PlayAlarmAty extends Activity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);

        mp = MediaPlayer.create(this, R.raw.music_one);
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mp.stop();
        mp.release();
    }
}
