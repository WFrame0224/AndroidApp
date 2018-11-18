package com.example.atrributetest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class AlphaImageView extends AppCompatImageView {
    // 图像透明度每次改变的大小
    private int alphaDelta = 0;
    // 记录当前的透明度
    private int curAlpha = 0;
    // 每隔多少透明度改变一次
    private final int SPEED = 300;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                // 每次增加curAlpha的值
                curAlpha += alphaDelta;
                if (curAlpha > 255) curAlpha = 255;
                // 修改该ImageView的透明度
                AlphaImageView.this.setAlpha(curAlpha);
            }
        }
    };

    public AlphaImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AlphaImageView);
        // 获取duration参数
        int duration = typedArray.getInt(R.styleable.AlphaImageView_duration, 0);
        // 计算图像透明度每次改变的大小
        alphaDelta = 255 * SPEED / duration;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.setImageAlpha(curAlpha);
        super.onDraw(canvas);
        final Timer timer = new Timer();
        // 按固定间隔发送消息，通知系统改变图片的透明度
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0x123;
                if (curAlpha >= 255) {
                    timer.cancel();
                } else {
                    handler.sendMessage(msg);
                }
            }
        }, 0, SPEED);
    }
}
