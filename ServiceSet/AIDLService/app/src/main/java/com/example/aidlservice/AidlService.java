package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import com.example.aidlservice.ICat.Stub;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class AidlService extends Service {
    public CatBinder catBinder;
    private Timer timer = new Timer();
    final static String[] colors = {"红色","黄色","黑色"};
    final static double[] weights = {2.3,3.1,1.58};
    public static String color = colors[0];
    public static double weight = 0.0;

    // 继承Stub,也就是实现了Icat接口，实现了IBinder接口
    public class CatBinder extends ICat.Stub {
        @Override
        public String getColor(){
            return AidlService.color;
        }
        @Override
        public double getWeight(){
            return AidlService.weight;
        }
    }

    @Override
    public IBinder onBind(Intent intent){
        /**
         * 返回catBinder对象
         * 在绑定本地Service的情况下，该CatBinder对象会直接传给客户端ServiceConnection对象
         * 的onServiceConnected方法的第二个参数
         * 在绑定远程Service的情况下，只将catBinder对象的代理传给客户端的ServiceConnection对象的
         * onServiceConnected方法的第二个参数
         */
        return catBinder;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        catBinder = new CatBinder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 随机改变Service组件内color、weight属性的值
                int rand = (int)(Math.random() * 3 );
                color = colors[rand];
                weight = weights[rand];
            }
        },0,800);
    }
    @Override
    public void onDestroy(){
        timer.cancel();
    }
}
