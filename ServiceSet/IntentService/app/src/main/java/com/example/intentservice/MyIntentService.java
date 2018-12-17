package com.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


/**
 * IntentService具有如下特征：
 *      1）能够创建单独的worker线程来处理所有的Intent请求；
 *      2）会创建单独的worker线程来处理onHandleIntent()方法实现的代码，因此开发正无线处理多线程问题；
 *      3）当所有请求处理完成后，IntentService会自动停止，因此开发正无须调用stopSelif()方法来停止该Service
 *      4）为Service的onBind()方法提供了默认实现，默认实现的onBind()方法null
 *      5)为Service的onStartCommand()方法提供了默认实现，该实现会将请求Intent添加到队列上
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    // IntentService会使用单独的线程来执行该方法的代码
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 该方法内可以执行任何耗时任务，比如下载文件等，此处只是让线程暂停20s
        long endTime = System.currentTimeMillis() + 20 * 1000;
        System.out.println("onStartCommand");
        while (System.currentTimeMillis() < endTime) {
            synchronized (this){
                try {
                    wait(endTime - System.currentTimeMillis());
                }catch (Exception e){
                }
            }
        }
        System.out.println("---耗时任务执行完成---");
    }
}
