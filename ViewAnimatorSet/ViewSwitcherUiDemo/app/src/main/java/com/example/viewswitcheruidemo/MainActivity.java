package com.example.viewswitcheruidemo;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 获取布局填充器
    LayoutInflater inflater;
    ConstraintLayout parent;
    View child1,child2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = (ConstraintLayout)findViewById(R.id.main_activity);
        inflater = LayoutInflater.from(MainActivity.this);

    }

    public void pre(View view) {
        child1 = inflater.inflate(R.layout.image_switcher_activity,
                parent, false );
        if (child2 != null) {
            parent.removeView(child2);
        }
        parent.addView(child1);
    }

    public void next(View view) {
        child2 = inflater.inflate(R.layout.text_switcher_activity,
                parent, false );
        if (child1 != null) {
            parent.removeView(child1);
        }
        parent.addView(child2);
    }
}
