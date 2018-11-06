package com.example.actioncategorytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // 定义一个Action常量
    public static final String FRAME_ACTION = "com.example.actioncategorytest.FRAME_ACTION";
    // 定义一个Category常量
    public static final String FRAME_CATEGORY = "com.example.actioncategorytest.FRAME_CATEGORY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 设置Action属性
                intent.setAction(MainActivity.FRAME_ACTION);
                // 设置Category属性
                intent.addCategory(MainActivity.FRAME_CATEGORY);
                startActivity(intent);
            }
        });
    }
}
