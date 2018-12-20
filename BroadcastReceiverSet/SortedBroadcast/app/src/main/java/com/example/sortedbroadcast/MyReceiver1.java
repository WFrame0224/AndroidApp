package com.example.sortedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"第一个监听者接收到的消息为：" + intent.getAction() + "\n消息内容是："
                + intent.getStringExtra("msg"),Toast.LENGTH_SHORT).show();
        // 创建一个Bundle对象，并存入
        Bundle bundle = new Bundle();
        bundle.putString("first","这是第一个BroadCastReceiver存入的消息");
        // 将bundle放入结果中
        setResultExtras(bundle);
        // 取消BroadCast的继续传播
//        abortBroadcast();
    }
}
