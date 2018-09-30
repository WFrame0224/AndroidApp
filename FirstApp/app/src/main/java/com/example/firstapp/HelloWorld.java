package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelloWorld extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.firstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
    }
    //点击Send按钮的回调函数
    public void sendMessage(View view)
    {
        // 创建Intent对象，并调用另一个窗口
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText2);
        String message = editText.getText().toString();
        // 该函数将EditText的值添加到intent
        intent.putExtra(EXTRA_MESSAGE,message);
        // 启动Intent指定的DisplayMessageActivity实例
        startActivity(intent);
    }
}
