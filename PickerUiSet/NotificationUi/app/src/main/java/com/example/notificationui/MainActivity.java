package com.example.notificationui;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**创建通知的步骤：
 * 1.调用getSystemService(NOTIFICATION_SERVICE)方法获取系统的NotificationManger
 * 2.创建NotificationChannel对象,并在NotificationManager上创建Channel对象
 * 3.通过构造Notification.Builder对象
 * 4.为NotificationCompat.Builder设置通知的各种属性，然后创建Notification
 * 5.通过NotificationManager发送Notification
 */


public class MainActivity extends AppCompatActivity {

    static final int NOTIFICATION_ID = 0x123;
    static final String CHANNEL_ID = "my_channel_id_02";
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 第一步：获取系统的NotificationManger服务
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        // 设置通知Channel的名字
        String name = "测试Channel";
        // 第二步创建通知
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,
                NotificationManager.IMPORTANCE_HIGH);
        nm.createNotificationChannel(channel);
    }
    // 为发送通知的按钮的点击事件定义事件处理的方法
    public void send(View source){
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent(MainActivity.this,OtherActivity.class);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
                0,intent,0);
        // 第三步：通过构造器创建一个Notification.Builder
        // 第四步：设置各种属性，然后创建Notification
        Notification notify = new NotificationCompat.Builder(this, CHANNEL_ID)
                // 设置打开该通知
                .setAutoCancel(true)
                // 设置显示在状态栏的通知提示信息
                .setTicker("有新消息")
                // 设置通知的图标
                .setSmallIcon(R.drawable.timg)
                // 设置通知的自定义声音
                .setSound(Uri.parse("file://H:/Working/AndroidApp/PickerUiSet/NotificationUi/app/src/main/res/raw" + R.raw.msg))
                // 设置通知内容的标题
                .setContentTitle("中国工商银行")
                // 设置通知内容
                .setContentText("恭喜你，您加薪了，工资增加30%")
                // 设置通知的自定义声音
                .setWhen(System.currentTimeMillis())
                // 设置通知将要启动程序的Intent
                .setContentIntent(pi)
                .build();
        // 第五步：发送通知
        nm.notify(NOTIFICATION_ID,notify);
    }
    // 为删除通知的按钮的电机事件定义事件处理的方法
    public void del(View view){
        // 取消通知
        nm.cancel(NOTIFICATION_ID);
    }
}
