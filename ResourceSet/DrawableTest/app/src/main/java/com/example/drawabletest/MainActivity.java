package com.example.drawabletest;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.image);
        // 获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable = (ClipDrawable)imageView.getDrawable();
        final Handler handler = new Handler(){
          @Override
          public void handleMessage(Message msg)
          {
              if (msg.what == 0x123){
                  // 修改ClipDrawable的level值
                  drawable.setLevel(drawable.getLevel() + 200);
              }
          }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x123;
                // 发送消息，通知应用修改ClipDrawable对象的level值
                handler.sendMessage(msg);
                // 取消定时器
                if (drawable.getLevel() >= 10000 ){
                    timer.cancel();
                }
            }
        },0,50);
    }

    public void openSecond(View view) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
