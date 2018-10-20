package com.example.viewswitcheruidemo;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pre(View view) {
        // 创建Intent对象，并调用另一个窗口，显示Intent,明确指明了需要启动和触发的组件名称
        Intent intent = new Intent(this,ImageSwitcherActivity.class);
        startActivity(intent);
    }

    public void next(View view) {
        // 创建Intent对象，并调用另一个窗口，显示Intent,明确指明了需要启动和触发的组件名称
        Intent intent = new Intent(this,TextSwitcherActivity.class);
        startActivity(intent);
    }
}
