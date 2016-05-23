package com.example.angluswang.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost = (TabHost) findViewById(R.id.tabHost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("tabTime").setIndicator("时钟").setContent(R.id.tabTime));
        mTabHost.addTab(mTabHost.newTabSpec("tabAlarm").setIndicator("闹钟").setContent(R.id.tabAlarm));
        mTabHost.addTab(mTabHost.newTabSpec("tabTimer").setIndicator("计时器").setContent(R.id.tabTimer));
        mTabHost.addTab(mTabHost.newTabSpec("tabStopWatch").setIndicator("秒表").setContent(R.id.tabStopWatch));
    }

    private TabHost mTabHost;
}
