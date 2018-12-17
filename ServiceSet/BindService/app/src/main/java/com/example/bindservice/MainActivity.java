package com.example.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bind,unbind,getServiceStatus;
    // 保持所启动的Service的IBinder对象
    BindService.MyBinder binder;
    // 定义一个ServiceConnection对象,用于bindService方法的第二参数
    private ServiceConnection conn = new ServiceConnection() {
        // 当该Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("--Service Connected--");
            // 获取Service的onBind方法返回的MyBinder对象
            binder = (BindService.MyBinder)iBinder;
        }
        // 当该Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("--Service Disconnected--");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取程序界面中的start、stop、getServiceStatus按钮
        bind = (Button)findViewById(R.id.bt_bind);
        unbind = (Button)findViewById(R.id.bt_unbind);
        getServiceStatus = (Button)findViewById(R.id.bt_bindStatus);
        // 创建启动Service的Intent,这里必须采用显示创建Intent的方法
        final Intent intent = new Intent(this,BindService.class);
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 绑定指定Service
                bindService(intent,conn,Service.BIND_AUTO_CREATE);
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 解除绑定Service
                unbindService(conn);
            }
        });
        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取并显示Service的count值
                Toast.makeText(MainActivity.this,
                        "Service的count值为：" + binder.getCount(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
