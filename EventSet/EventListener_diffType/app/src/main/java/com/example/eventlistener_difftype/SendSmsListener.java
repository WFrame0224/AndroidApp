package com.example.eventlistener_difftype;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class SendSmsListener implements View.OnLongClickListener {
    private Activity act;
    private String address;
    private String content;

    public SendSmsListener(Activity activity, String address, String content) {
        this.act = activity;
        this.address = address;
        this.content = content;
    }
    @Override
    public boolean onLongClick(View view){
        // 获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();
        // 创建发送短信的PendingIntent
        PendingIntent sentIntent = PendingIntent.getBroadcast(this.act,0,
                new Intent(), 0);
        // 发送文本短信
        smsManager.sendTextMessage(address,null,
                address,sentIntent,null);
        Toast.makeText(this.act,"短信发送完成",Toast.LENGTH_SHORT).show();
        return false;
    }
}
