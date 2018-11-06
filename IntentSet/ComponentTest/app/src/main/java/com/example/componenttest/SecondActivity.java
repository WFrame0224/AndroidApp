package com.example.componenttest;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText show = (EditText)findViewById(R.id.show);
        // 获取该Activity对应的Intent的Component属性
        ComponentName comp = getIntent().getComponent();
        // 显示该Component对象对应的包名、类名
        show.setText("组件包名为：" + comp.getPackageName()
        + "\n组件类名为：" + comp.getClassName());
    }
}
