package com.example.configurationtest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ori, navigation, touch, mnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ori = (EditText) findViewById(R.id.ori);
        navigation = (EditText) findViewById(R.id.navigation);
        touch = (EditText) findViewById(R.id.touch);
        mnc = (EditText) findViewById(R.id.mnc);
        Button bn = (Button) findViewById(R.id.bn1);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取系统的Configuration对象
                Configuration cfg = getResources().getConfiguration();
                String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ?
                        "横向屏幕" : "竖向屏幕";
                String mncCode = mncCode = cfg.mnc + "";
                String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ?
                        "没有方向控制" : cfg.orientation == Configuration.NAVIGATION_WHEEL ?
                        "滚轮控制方向" : cfg.orientation == Configuration.NAVIGATION_DPAD ?
                        "方向键控制方向" : "轨迹球控制方向";
                navigation.setText(naviName);
                String touchName = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ?
                        "无触摸屏" : "支持触摸屏";
                ori.setText(screen);
                mnc.setText(mncCode);
                touch.setText(touchName);
            }
        });
        Button bn2 = (Button) findViewById(R.id.bn2);
        // 为按键时间绑定事件监听器
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration config = getResources().getConfiguration();
                // 如果是当前是横屏
                if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // 设为竖屏
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                // 如果当前是竖屏
                if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    // 设为横屏
                    MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }

    // 重写onConfigurationChanged方法，用于监听系统设置的更改，主要是监听屏幕方向的更改
    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        String screen1 = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                ? "横向屏幕" : "竖向屏幕";
        ori.setText(screen1);
        Toast.makeText(this, "系统的屏幕方向发生改变" + "\n修改后的屏幕方向为："
                + screen1, Toast.LENGTH_LONG).show();
    }
}
