package com.example.gesturetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener {

    // 定义手势检测器实例
    GestureDetector detector;
    ImageView imageView;
    // 初始的图片资源
    Bitmap bitmap;
    // 定义图片的宽、高
    int width,height;
    // 记录当前的缩放比
    float currentScale = 1;
    // 控制图片缩放的Matrix
    Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建手势检测器
        detector = new GestureDetector(this,this);
        imageView = (ImageView)findViewById(R.id.show);
        // 创建matrix用于控制图像的变换
        matrix = new Matrix();
        // 获取被缩放的源图片
        bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.flower);
        // 获得位图宽
        width = bitmap.getWidth();
        // 或得位图高
        height = bitmap.getHeight();
        // 设置ImageView初始化时显示的图片
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.flower));
    }
    // 将该Activity 上的触碰事件交给GestureDetector处理
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        return detector.onTouchEvent(motionEvent);
    }
    // 当触碰事件按下时触发该方法
    @Override
    public boolean onDown(MotionEvent arg0){
        return false;
    }
    // 当用户在触摸屏上“拖过”时触发该方法，其中velocityX,velocityY代表“拖过”动作在横向、纵向上的速度
    @Override
    public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
        velocityX = velocityX > 4000 ? 4000 : velocityX;
        velocityX = velocityX < -4000 ? -4000 : velocityX;
        // 根据手势的速度来计算缩放比，如果velocityX>0,放大图像，否则缩小图像
        currentScale += currentScale * velocityX / 4000.0f;
        // 保证currentScale不会等于0
        currentScale = currentScale > 0.01 ? currentScale:0.01f;
        // 重置Matrix
        matrix.reset();
        // 缩放Matrix
        matrix.setScale(currentScale,currentScale,160,200);
        BitmapDrawable tmp = (BitmapDrawable)imageView.getDrawable();
        // 如果图片还未回收，先强制回收该图像
        if (!tmp.getBitmap().isRecycled()){
            tmp.getBitmap().recycle();
        }
        // 根据原始位图和Matrix创建新图片
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        // 显示新的位图
        imageView.setImageBitmap(bitmap2);

        return false;
    }
    // 当用户手指在屏幕上长按时触发该方法
    @Override
    public void onLongPress(MotionEvent event){
    }
    // 当用户手指在屏幕上滚动时触发该方法
    @Override
    public boolean onScroll(MotionEvent e1,MotionEvent e2,float distanceX,float distanceY){
        return false;
    }
    // 当用户手指在屏幕上按下，而且还未移动和松开时触发该方法
    @Override
    public void onShowPress(MotionEvent event){
    }
    // 用户手指在触摸屏上轻击事件将会触发该方法
    @Override
    public boolean onSingleTapUp(MotionEvent event){
        return false;
    }
}
