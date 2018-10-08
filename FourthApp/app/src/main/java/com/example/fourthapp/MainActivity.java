package com.example.fourthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void openFrameLayout(View view){
        // 创建Intent对象，并调用另一个窗口，显示Intent，明确指明了需要启动和触发的组件类名
        Intent intent = new Intent(this,FrameLayoutDemo.class);
        startActivity(intent);
    }
}
