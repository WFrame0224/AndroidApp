package com.example.parcelableclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.parcelableserver.IPerson;
import com.example.parcelableserver.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private IPerson iPerson;
    private boolean accessFlag = false;

    public ServiceConnection cnn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.d("cylog", "onServiceConnected success");
            // 获取远程Service的onBind方法返回对象的代理
            iPerson = IPerson.Stub.asInterface(service);
            if (iPerson != null){
                System.out.println("-----------66666--------");
                accessFlag = true;// 当连接成功时修改accessFlag为true
            }else {
                accessFlag = false;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("cylog", "onServicedisConnected ");
            iPerson = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText personView = (EditText) findViewById(R.id.person);
        Button addP = (Button) findViewById(R.id.get);
        Button bind = (Button)findViewById(R.id.bind);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建所需绑定的Service的Intent
                Intent intent = new Intent();
                intent.setAction("com.example.aidl.action.PARCELABLE_SERVICE");
                intent.setPackage("com.example.parcelableserver");

                // 绑定远程Service
                bindService(intent, cnn, Service.BIND_AUTO_CREATE);
            }
        });
        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                Person person = new Person("非非",random.nextInt(),random.nextInt()*100);
                try {
                    iPerson.addPerson(person);
                    List<Person> personList = iPerson.getPersonList();
                    personView.setText(personList.toString());
                } catch (RemoteException e) {
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
