package com.example.componenttest;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button)findViewById(R.id.button);
        // 为bn绑定事件监听器
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个ComponentName对象
                ComponentName comp = new ComponentName(MainActivity.this,SecondActivity.class);
                Intent intent = new Intent();
                // 为Intent设置Component属性
                intent.setComponent(comp);
                startActivity(intent);
            }
        });
    }
}
