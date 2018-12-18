package com.example.aidlserviceclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aidlservice.ICat;

public class MainActivity extends AppCompatActivity {

    private static ICat catService;
    private boolean accessFlag = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 获取远程Service的onBind方法返回的对象的代理
            catService = ICat.Stub.asInterface(iBinder);
            accessFlag = true;// 当连接成功时修改accessFlag为true
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            catService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button get = (Button) findViewById(R.id.get);
        final TextView color = (TextView) findViewById(R.id.color);
        final TextView weight = (TextView) findViewById(R.id.weight);
        // 创建所需绑定的Service的Intent
        Intent intent = new Intent();
        intent.setAction("com.example.aidl.action.AIDL_SERVICE");
        intent.setPackage("com.example.aidlservice");
        // 绑定远程Service
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accessFlag == true) {
                    // 获取并显示远程Service的状态
                    try {
                        color.setText("名字：" + catService.getColor());
                        weight.setText("重量：" + catService.getWeight());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        //解除绑定
        this.unbindService(conn);
    }
}
