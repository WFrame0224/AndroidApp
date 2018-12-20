package com.example.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取程序界面的按钮
        bn_send = (Button)findViewById(R.id.bn_send);
        bn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建Intent对象
                Intent intent = new Intent();
                // 设置Intent的Action属性
                intent.setAction("com.example.action.FRAME_BROADCAST");
                intent.setPackage("com.example.broadcast");
                intent.putExtra("msg","简单的消息");
                // 发送广播
                sendBroadcast(intent);
            }
        });
    }
}
