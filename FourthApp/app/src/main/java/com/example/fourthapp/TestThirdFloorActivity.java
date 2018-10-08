package com.example.fourthapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

public class TestThirdFloorActivity extends AppCompatActivity {
    GridLayout gridLayout;
    // 定义16个按钮的文本
    String[] chars = new String[]{
            "7", "8", "9", "÷",
            "4", "5", "6", "×",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_third_floor_activity);
        // 创建GridLayout布局
        gridLayout = (GridLayout)findViewById(R.id.third);
        //循环创建16个按钮
        for (int i = 0;i< chars.length;i++){
            Button bn = new Button(this);
            bn.setText(chars[i]);
            bn.setTextSize(40);
            // 设置按钮四周的空白区域
            bn.setPadding(5,35,5,35);
            // 指定该组件所在的行
            GridLayout.Spec rowSpec = GridLayout.spec(i/4 + 2);
            // 指定该组件所在的列
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
            // 指定该组件占满父容器
            params.setGravity(Gravity.FILL_HORIZONTAL);
            gridLayout.addView(bn,params);

        }
    }
}
