package com.example.fragmentlifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startActivity, addFragment, backFragment, replaceFragment, finsih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity = (Button)findViewById(R.id.startActivity);
        addFragment = (Button)findViewById(R.id.addFragment);
        backFragment = (Button)findViewById(R.id.backFragment);
        replaceFragment = (Button)findViewById(R.id.replaceFragment);
        finsih = (Button)findViewById(R.id.finish);

        // 为各个按钮绑定事件监听器
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LifecycleFragment fragment = new LifecycleFragment();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });
        backFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.container,fragment)
                        .addToBackStack("aa")// 将替换前的Fragment添加到Back栈
                        .commit();
            }
        });
        replaceFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
            }
        });
        finsih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 结束该Activity
                MainActivity.this.finish();
            }
        });
    }
}
