package com.example.sharedpreferencestest;

import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preference, preference1;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取只能被应用程序读、写的SharedPreferences 对象
        preference = getSharedPreferences("example", MODE_PRIVATE);
        editor = preference.edit();

        Button read = (Button) findViewById(R.id.bn2);
        Button write = (Button) findViewById(R.id.bn1);

        preference1 = getSharedPreferences("count", MODE_PRIVATE);
        int count = preference1.getInt("count", 0);
        // 显示程序以前的使用次数
        Toast.makeText(this, "程序以前被使用了" + count + "次。", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = preference1.edit();
        // 存入数据
        editor.putInt("count", ++count);
        editor.commit();

    }

    public void writeData(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + "hh:mm:ss");
        // 存入当前时间
        editor.putString("time", sdf.format(new Date()));
        // 存入一个随机数
        editor.putInt("random", (int) (Math.random() * 100));
        // 提交所有存入的数据
        editor.commit();
    }

    public void readData(View view) {
        // 读取字符串数据
        String time = preference.getString("time", null);
        // 读取int类型的数据
        int randNum = preference.getInt("random", 0);
        String result = time == null ? "您暂时还未写入数据" :
                "写入时间为：" + time + "\n上次生成的随机数为：" + randNum;
        // 使用Toast提示信息
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
    }
}
