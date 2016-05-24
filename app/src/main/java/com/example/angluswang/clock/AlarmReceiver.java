package com.example.angluswang.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Jeson on 2016/5/24.
 * 闹钟的广播接受者
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("闹钟执行了~");
    }
}
