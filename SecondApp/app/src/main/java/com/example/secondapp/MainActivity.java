package com.example.secondapp;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 定义一个访问图片的数组
    // 需要事先存放至 app/res/drawable文件中，图片的名字分别是以下内容
    int[] images = new int[]{
      R.drawable.java,
      R.drawable.javaee,
      R.drawable.swift,
      R.drawable.ajax,
      R.drawable.html,
    };
    int currentImg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取布局容器
        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.root);

        //此处采用的是java代码直接创建的ImageView组件，也可以利用XML创建
        // 创建ImageView组件
        final ImageView image = new ImageView(this);
        // 将ImageView组件添加到布局容器之中
        main.addView(image);
        // 初始化显示第一张图片
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 改变ImageView里显示的图片
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
    }
}
