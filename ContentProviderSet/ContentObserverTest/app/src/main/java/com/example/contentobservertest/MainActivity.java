package com.example.contentobservertest;

import android.Manifest;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


/**
 * 监听ContentProvider的数据改变
 * 采用ContentObserver类：
 *      在ContentProvider中，因insert,delete,update等方法导致的数据改变后，程序调用如下代码通知
 *      系统的ContentProvider的数据改变：
 *           getContext().getContentResolver().notifyChange(uri,null);
 *      我们需要完成的工作：
 *          1）通过ContentResolver向指定Uri注册ContentObserver监听器，采用registerContentObserver(...)方法
 *          2）继承ContentObserver基类；
 *          3）重写onChange(boolean selfChange)方法
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  请求获取读取短信的权限
        requestPermissions(new String[]{Manifest.permission.READ_SMS},0x123);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        // 如果用户授权访问短信内容
        if (grantResults != null && grantResults[0] == 0 && requestCode == 0x123){
            // 为Telephony.Sms.CONTENT_URI的数据改变注册监听器
            getContentResolver().registerContentObserver(Uri.parse("content://sms"),
                    true,new SmsObserver(new Handler()));
        }else {
            Toast.makeText(this,"您必须授权访问短信内容才能测试该应用",Toast.LENGTH_SHORT).show();
        }
    }

    private final class SmsObserver extends ContentObserver {
        public SmsObserver(Handler handler) {
            super(handler);
        }
        public void onChange(boolean selfChange){
            // 查询发送邮箱中的短信（处于正在发送状态的短信放在发送箱）
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/outbox"),
                    null,null,null,null);
            // 遍历查询得到的结果集，即可获取用户正在发送的短信
            while (cursor.moveToNext()){
                StringBuilder sb = new StringBuilder();
                // 获取短信的发送地址
                sb.append("address=").append(cursor.getString(cursor.getColumnIndex("address")));
                // 获取短信的标题
                sb.append(";subject=").append(cursor.getString(cursor.getColumnIndex("subject")));
                // 获取短信的内容
                sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));
                // 获取短信的发送时间
                sb.append(";time=").append(cursor.getLong(cursor.getColumnIndex("date")));
                System.out.println("发送短信：" + sb.toString());
            }
        }
    }
}
