package com.example.gesturetab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener {

    // ViewFilpper实例
    ViewFlipper flipper;
    // 定义手势检测器实例
    GestureDetector detector;
    // 定义一个动画数组，用于为ViewFlipper指定切换动画效果
    Animation[] animations = new Animation[4];
    // 指定手势动作两点之间的最小距离
    private float FLIP_DISTANCE = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建手势检测器
        detector = new GestureDetector(this,this);
        // 获得ViewFlipper实例
        flipper = (ViewFlipper)this.findViewById(R.id.flipper);
        // 为ViewFlipper添加5个ImageView组件
        flipper.addView(addImageView(R.drawable.java));
        flipper.addView(addImageView(R.drawable.javaee));
        flipper.addView(addImageView(R.drawable.ajax));
        flipper.addView(addImageView(R.drawable.android));
        flipper.addView(addImageView(R.drawable.html));
        flipper.addView(addImageView(R.drawable.swift));
        // 初始化Animation数组
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.left_out);
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.right_out);
    }

    // 定义添加ImageView的工具方法
    private View addImageView(int resId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // 将该Activity上的触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {

        // 如果第一个触点事件的X坐标大于第二个触点事件的X坐标超过FLIP_DISTANCE
        // 也就是手势从右向左滑
        if (motionEvent.getX() - motionEvent1.getX() > FLIP_DISTANCE){
            // 为flipper设置切换的动画效果
            flipper.setInAnimation(animations[0]);
            flipper.setInAnimation(animations[1]);
            flipper.showPrevious();
            return true;
        }
        // 如果第二个触点事件的X坐标大于第一个触点事件的X坐标超过FLIP_DISTANCE
        // 也就是手势从右向左滑
        else if (motionEvent1.getX() - motionEvent.getX() > FLIP_DISTANCE){
            // 为Flipper设置切换的动画效果
            flipper.setInAnimation(animations[2]);
            flipper.setInAnimation(animations[3]);
            flipper.showNext();
            return true;
        }
        return false;
    }
}
