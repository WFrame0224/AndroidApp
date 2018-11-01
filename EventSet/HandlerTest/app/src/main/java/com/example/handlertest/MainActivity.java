package com.example.handlertest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String UPPER_NUM = "upper";
    EditText etNum;
    CalThread calThread;

    // 定义一个线程类
    class CalThread extends Thread {
        public Handler mHandler;

        public void run() {
            // 1、调用Lopper的prepare()方法为当前线程创建Looper对象，创建Lopper对象时，
            // 它的构造器会创建与之配套的MessageQueue
            Looper.prepare();
            // 2、有了Lopper之后创建Handler子类的实例，重写handleMessage()方法，
            // 该方法负责处理来自于其他线程的消息
            mHandler = new Handler() {
                // 定义处理消息的方法
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer> nums = new ArrayList<>();
                        // 计算从2开始，到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            // 用i除以从2开始，到i的平方根的所有数
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                // 如果可以整除，则表明这个数不是质数
                                if (i != 2 && i % j == 0)
                                {
                                    continue outer;
                                }
                                nums.add(i);
                            }
                            // 使用Toast显示统计出来的所有质数
                            Toast.makeText(MainActivity.this,nums.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                }
            };
            // 3、调用Lopper的loop()方法启动Looper
            Looper.loop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum = (EditText)findViewById(R.id.editText);
        calThread = new CalThread();
        // 启动新线程
        calThread.start();
    }

    public void cal(View view) {
        // 创建消息
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
        // 向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);
    }
}
