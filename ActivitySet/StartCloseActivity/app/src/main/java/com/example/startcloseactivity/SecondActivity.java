package com.example.startcloseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Button previous = (Button)findViewById(R.id.button2);
        Button close = (Button)findViewById(R.id.button3);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取启动当前Activity的上一个Intent
                Intent intent = new Intent(SecondActivity.this,
                        MainActivity.class);
                // 启动Intent对应的Activity
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                // 启动Intent对应的Activity
                startActivity(intent);
                // 结束当前Activity
                finish();
            }
        });
    }
}
