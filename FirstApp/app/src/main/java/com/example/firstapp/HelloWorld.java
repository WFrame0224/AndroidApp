package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelloWorld extends AppCompatActivity {
    // 一个Activityzai 的生命周期通常包含：创建，暂停，继续/恢复，停止，恢复，销毁，
    public static final String EXTRA_MESSAGE = "com.example.firstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 正如以下所示，调用这些Activity的生命周期方法时必须始终先调用超类实现，然后再执行操作
        super.onCreate(savedInstanceState);
        //必须在此方法内调用setContentView(),以定义Activity用户界面的布局
        setContentView(R.layout.activity_hello_world);
    }
    /**
    // 以下代码展示本程序未用到的Activity的生命周期方法
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        //....
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        //...
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        //...
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        //...
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        // The activity is about to be destroyed.
        //...
    }
    */
    //点击Send按钮的回调函数
    public void sendMessage(View view)
    {
        // 创建Intent对象，并调用另一个窗口，显式Intent，明确指明了需要启动和触发的组件类名
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText2);
        String message = editText.getText().toString();
        // 该函数将EditText的值添加到intent
        intent.putExtra(EXTRA_MESSAGE,message);
        // 启动Intent指定的DisplayMessageActivity实例
        startActivity(intent);
    }
}
