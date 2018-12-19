package com.example.parcelableclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.parcelableserver.IPet;
import com.example.parcelableserver.Person;
import com.example.parcelableserver.Pet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IPet perService;
    private boolean accessFlag = false;
    public ServiceConnection cnn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            // 获取远程Service的onBind方法返回对象的代理
            perService = IPet.Stub.asInterface(service);
            if (perService != null){
                System.out.println("-----------66666--------");
                accessFlag = true;// 当连接成功时修改accessFlag为true
            }else {
                accessFlag = false;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            perService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText personView = (EditText) findViewById(R.id.person);
        final ListView showView = (ListView) findViewById(R.id.show);
        Button get = (Button) findViewById(R.id.get);
        Button bind = (Button)findViewById(R.id.bind);
        // 创建所需绑定的Service的Intent
        final Intent intent = new Intent();
        intent.setAction("com.example.aidl.action.PARCELABLE_SERVICE");
        intent.setPackage("com.example.parcelableserver");

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("-----------------");
                System.out.println(intent.getAction());
                System.out.println(intent.getPackage());
                System.out.println("-----------------");
                // 绑定远程Service
                bindService(intent, cnn, Service.BIND_AUTO_CREATE);
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personName = personView.getText().toString();
                System.out.println(personName);
                // 调用远程的Service的方法
                List<Pet> pets = new ArrayList<>();
                try {
                    pets = perService.getPets(new Person(1, personName, personName));
                    if (pets != null){
                        System.out.println("-----------获取了数据------------");
                        // 将程序返回的List包装成ArrayAdapter
                        ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(MainActivity.this,
                                android.R.layout.simple_list_item_1, pets);
                        try {
                            showView.setAdapter(adapter);
                        }catch (NullPointerException e)
                        {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("-----------还是没能获取数据------------");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 解除绑定
        this.unbindService(cnn);
    }
}
