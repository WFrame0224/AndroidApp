package com.example.activitylifecycle;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Frame";
    Button finish,startActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"-----------onCreate----------");
        finish = (Button)findViewById(R.id.finish);
        startActivity = (Button)findViewById(R.id.startActivity);
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 结束该Activity
                MainActivity.this.finish();
            }
        });
    }
    /**
     * android中有5种级别的log，分别为：
     * 1.v(verbose)：任何信息都会输出
     * 2.i(info)：输出提示信息
     * 3.e(error)：输出错误信息
     * 4.d(debug)：输出调试信息
     * 5.w(warning)：输出警告信息
     */
    @Override
    public void onStart(){
        super.onStart();
        // 输出日志
        Log.d(TAG,"-----------onStart------------");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"-----------onRestart-----------");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"------------onResume-----------");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"-------------onPause-----------");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"--------------onStop-----------");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"-------------onDestroy---------");
    }
}
