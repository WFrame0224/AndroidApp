package com.example.thirdapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    public float currentX = 40;
    public float currentY = 50;
    // 定义并创建绘笔
    Paint[] ps = new Paint[4];
    public DrawView(Context context){
        super(context);
    }
    public DrawView(Context context, AttributeSet set) {
        super(context,set);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        // 设置画笔颜色
        for(int i = 0;i<=3;i++) {
            ps[i] = new Paint();
        }
        ps[0].setColor(Color.GREEN);
        ps[1].setColor(Color.RED);
        ps[2].setColor(Color.BLUE);
        ps[3].setColor(Color.YELLOW);
        // 绘制一个小球
        canvas.drawCircle(currentX-50,currentY-50,15,ps[0]);
        canvas.drawCircle(currentX+50,currentY-50,15,ps[1]);
        canvas.drawCircle(currentX+50,currentY+50,15,ps[2]);
        canvas.drawCircle(currentX-50,currentY+50,15,ps[3]);
    }
    //为该组件的触碰事件重写事件处理的方法
    @Override
    public boolean onTouchEvent(MotionEvent event){
        // 修改currentX,currentY两个属性
        currentX = event.getX();
        currentY = event.getY();
        // 通知当前组件重绘自己
        invalidate();
        // 返回true 表明该处理方法已经处理该事件
        return true;
    }
}
