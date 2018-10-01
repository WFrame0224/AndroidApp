package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //获取此活动的Intent，并获取外部的字符串
        // 可能是隐式Intent,指明启动或触发的组件应该满足的条件
        Intent intent = getIntent();
        String message = intent.getStringExtra(HelloWorld.EXTRA_MESSAGE);

        // 捕获布局的TextView，并将其设置为自己的文本值
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);

        // 另一个窗口显示时间
        ((TextView)findViewById(R.id.timeview)).setText("Time is: "+ new java.util.Date());

    }
}
