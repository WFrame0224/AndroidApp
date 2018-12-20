package com.example.sortedbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = (Button)findViewById(R.id.bn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建发送有序广播的Intent
                Intent intent = new Intent();
                intent.setAction("com.example.action.FRAME_BROADCAST");
                intent.setPackage("com.example.sortedbroadcast");
                intent.putExtra("msg","你好啊，Android");
                sendOrderedBroadcast(intent,null);
            }
        });
    }
}
