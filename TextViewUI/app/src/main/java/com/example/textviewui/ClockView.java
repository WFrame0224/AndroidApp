package com.example.textviewui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class ClockView extends AppCompatActivity {
    Chronometer ch;
    Button start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_view_activity);

        // 获取计时器组件
        ch = (Chronometer) findViewById(R.id.chronometer1);
        // 获取开始按钮
        start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View Source) {
                // 设置开始计时时间
                ch.setBase(SystemClock.elapsedRealtime());
                // 启动定时器
                ch.start();
                start.setEnabled(false);
            }
        });
        // 为Chronometer绑定事件监听器
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer ch) {
                // 如果从开始计时到现在超过20s
                if (SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000) {
                    ch.stop();
                    start.setEnabled(true);
                }
            }
        });
    }
}
