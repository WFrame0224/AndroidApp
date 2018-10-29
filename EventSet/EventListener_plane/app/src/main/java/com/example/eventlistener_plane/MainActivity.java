package com.example.eventlistener_plane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/** 监听事件：
 * 基于监听事件的处理有如下机制：
 *      三种必要的因素：
 *              1）事件源，可以是应用程序的任何一个组件
 *              2）注册监听器，只要调用事件源的setXxxListener(XxxListener)方法即可
 *              3）事件监听器，就是程序中的MyClickListener类，由程序员实现，实现监听类事件最为关键的就是实现处理方法
 *      事件监听器，通常有如下方法：
 *          1）内部类形式
 *          2）外部类形式
 *          3）Activity本身作为事件监听器类：让Activity本身实现监听器接口，并实践事件处理方法
 *          4）匿名内部类形式
 *
 */
public class MainActivity extends AppCompatActivity {

    // 定义飞机的移动速度
    private int speed = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 创建PlaneView组件
        final PlaneView planeView = new PlaneView(this);
        setContentView(planeView);
        planeView.setBackgroundResource(R.drawable.back);
        // 获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        final DisplayMetrics metrics = new DisplayMetrics();
        // 获取屏幕的宽和高,获取屏幕的分辨率信息
        display.getMetrics(metrics);
        // 设置飞机的初始位置
        planeView.currentX = (float) (metrics.widthPixels / 2);
        planeView.currentY = (float) (metrics.heightPixels - 700);


        // 为planeView 组件的键盘事件绑定监听器
        planeView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getX() < metrics.widthPixels / 8) {
                    planeView.currentX -= speed;
                }
                if (motionEvent.getX() > metrics.widthPixels * 7 / 8) {
                    planeView.currentX += speed;
                }
                if (motionEvent.getY() < metrics.heightPixels / 8) {
                    planeView.currentY -= speed;
                }
                if (motionEvent.getY() > metrics.heightPixels * 7 / 8) {
                    planeView.currentY += speed;
                }
                return true;
            }
        });

    }

}
