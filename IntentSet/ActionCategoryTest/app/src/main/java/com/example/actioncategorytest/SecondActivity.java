package com.example.actioncategorytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.Set;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText show = (EditText) findViewById(R.id.show);
        // 获取该Activity对应的Intent的Action属性
        String action = getIntent().getAction();
        // 获取该Activity对应的Intent的Category属性
        Set<String> category = getIntent().getCategories();
        // 显示Action属性
        show.setText("Action为：" + action + "\nCategory为：" + category);
    }
}
