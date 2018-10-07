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
    Paint p1 = new Paint();
    Paint p2 = new Paint();
    Paint p3 = new Paint();
    Paint p4 = new Paint();
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
        p1.setColor(Color.GREEN);
        p2.setColor(Color.RED);
        p3.setColor(Color.BLUE);
        p4.setColor(Color.YELLOW);
        // 绘制一个小球
        canvas.drawCircle(currentX-50,currentY-50,15,p1);
        canvas.drawCircle(currentX+50,currentY-50,15,p2);
        canvas.drawCircle(currentX+50,currentY+50,15,p3);
        canvas.drawCircle(currentX-50,currentY+50,15,p4);
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
