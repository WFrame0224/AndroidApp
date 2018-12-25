package com.example.httpurlconnection;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 实现多线程下载的步骤：
 *  1）创建URL对象
 *  2）获取指定URL对象所指向资源的大小（由getContentLength()方法实现），此处用到了HTTPURLConnection类
 *  3）在本地磁盘上创建一个与网络资源相同大小的空文件
 *  4）计算每条线程应该下载网络资源哪个部分（从哪个字节开始，到哪个字节结束）
 *  5）一次创建、启动多条线程来下载网络资源的指定部分
 */

public class MainActivity extends AppCompatActivity {

    EditText url, target;
    Button downBn;
    ProgressBar bar;
    DownUtil downUtil;
    private int mDownStatus;
    private class MyHandler extends Handler {
        WeakReference<MainActivity> mWeakReferenceActivity;
        public MyHandler(MainActivity activity) {
            mWeakReferenceActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                bar.setProgress(mDownStatus);
            }
        }
    }

    // 创建一个Handler对象
    private MyHandler handler = new MyHandler(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = (EditText) findViewById(R.id.url);
        target = (EditText) findViewById(R.id.target);
        // 获取SD卡对应的存储目录
        File sdCardDir = Environment.getExternalStorageDirectory();
        try {
            target.setText(sdCardDir.getCanonicalPath() + "/frame.chm");
        } catch (IOException e) {
            e.printStackTrace();
        }
        downBn = (Button) findViewById(R.id.down);
        bar = (ProgressBar) findViewById(R.id.bar);
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0x456);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permission,
                                           int[] grantResults) {
        if (requestCode == 0x456 && grantResults != null
                && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            downBn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 初始化DownUtil对象（最后一个参数指定线程数）
                    downUtil = new DownUtil(url.getText().toString(),target.getText().toString(),6);
                    new Thread(){
                        @Override
                        public void run(){
                            try{
                                // 开始下载
                                downUtil.download();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            // 定义每秒调度获取一次系统的完成进度
                            final Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    // 获取下载任务的完成比例
                                    double complateRate = downUtil.getCompleteRate();
                                    mDownStatus = (int)(complateRate * 100);
                                    // 发送消息通知界面更新进度条
                                    handler.sendEmptyMessage(0x123);
                                    // 下载完全后取消任务调度
                                    if (mDownStatus >= 100){
                                        timer.cancel();
                                    }
                                }
                            },0,100);
                        }

                    }.start();
                }
            });
        }
    }
}
