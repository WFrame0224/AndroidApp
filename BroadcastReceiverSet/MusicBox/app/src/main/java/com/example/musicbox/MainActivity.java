package com.example.musicbox;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 获取界面中显示歌曲标题、作者文本框
    TextView title,author;
    // 播放/暂停、停止按钮
    ImageButton play,stop;
    ActivityReceiver activityReceiver;
    public static final String CTL_ACTION = "com.example.action.CTL_ACTION";
    public static final String UPDATE_ACTION = "com.example.action.UPDATE_ACTION";
    // 定义音乐的播放状态，0x11代表没有播放，0x12代表正在播放，0x13代表暂停
    int status = 0x11;
    // 定义音乐信息
    String[] titleStrs = new String[] { "心愿", "约定", "美丽新世界" };
    String[] authorStrs = new String[] { "未知艺术家", "周蕙", "伍佰" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取界面中的按钮
        play = (ImageButton)findViewById(R.id.play);
        stop = (ImageButton)findViewById(R.id.stop);
        title = (TextView)findViewById(R.id.m_title);
        author = (TextView)findViewById(R.id.m_author);
        // 为两个按钮的单击事件添加监听器
        play.setOnClickListener(this);
        stop.setOnClickListener(this);

        activityReceiver = new ActivityReceiver();
        // 创建IntentFilter
        IntentFilter filter = new IntentFilter();
        // 指定BroadcastReceiver
        filter.addAction(UPDATE_ACTION);
        // 注册BroadcastReceiver
        registerReceiver(activityReceiver,filter);
        Intent intent = new Intent(this,MusicService.class);
        // 启动后台Service
        startService(intent);
        Log.d("frame","------Start Service---------");
    }
    @Override
    public void onClick(View view){
        // 创建Intent
        Intent intent = new Intent(CTL_ACTION);
        intent.setPackage("com.example.musicbox");
        switch (view.getId()){
            // 按下播放/暂停按钮
            case R.id.play:
                intent.putExtra("control",1);
                Log.d("frame","--------------你点击了 play------------------");
                break;
            case R.id.stop:
                intent.putExtra("control",2);
                Log.d("frame","--------------你点击了 stop------------------");
                break;
        }
        // 发送广播，将被Service组件中的BroadcastReceiver接收到
        sendBroadcast(intent);

    }
    // 自定义的BroadcastReceiver，负责监听从Service传回来的广播
    public class ActivityReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            // 获取Intent中的Update消息，update代表播放状态
            int update = intent.getIntExtra("update",-1);
            // 获取Intent中的current消息，current代表当前正在播放的歌曲
            int current = intent.getIntExtra("current",-1);
            if (current >= 0 ){
                title.setText(titleStrs[current]);
                author.setText(authorStrs[current]);
            }
            switch (update){
                case 0x11:
                    play.setImageResource(R.drawable.play);
                    status = 0x11;
                    break;
                // 控制系统进入播放状态
                case 0x12:
                    // 播放状态下设置使用暂停图标
                    play.setImageResource(R.drawable.pause);
                    // 设置当前状态
                    status = 0x12;
                    break;
                // 控制系统进入暂停状态
                case 0x13:
                    // 暂停状态下设置使用播放图标
                    play.setImageResource(R.drawable.play);
                    // 设置当前状态
                    status = 0x13;
                    break;
            }
        }
    }
}
