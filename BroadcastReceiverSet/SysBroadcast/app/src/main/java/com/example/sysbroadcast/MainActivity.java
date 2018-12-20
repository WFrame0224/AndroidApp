package com.example.sysbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView battery_level, battery_scale, battery_status, battery_cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        battery_level = (TextView) findViewById(R.id.battery_level);
        battery_scale = (TextView) findViewById(R.id.battery_scale);
        battery_status = (TextView) findViewById(R.id.battery_healthy);
        battery_cn = (TextView) findViewById(R.id.battery_cn);

        BatteryReceiver batteryReceiver = new BatteryReceiver();
        // 创建IntentFiler
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        registerReceiver(batteryReceiver, filter);

        BatteryManager bm = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        // 获取电池的状态
        Integer st = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS);
        // 获取电池的容量
        Integer a = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
        battery_status.setText("当前的状态为：" + st.toString());
        battery_cn.setText("当前的电量为：" + a.toString());

    }

    public class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("+++++++++++++++++++++++");
            Bundle bundle = intent.getExtras();
            // 获取当前电量
            Integer current = bundle.getInt("level");
            // 获取总电量
            Integer total = bundle.getInt("scale");


            battery_level.setText("当前的电量为：" + current.toString());
            battery_scale.setText("电池的总电量为：" + total.toString());
            // 如果当前电量小于总电量的15%
            if (current * 1.0 / total < 0.18) {
                Toast.makeText(context, "电量过低，请尽快充电！"
                        , Toast.LENGTH_LONG).show();
            }
        }
    }
}
