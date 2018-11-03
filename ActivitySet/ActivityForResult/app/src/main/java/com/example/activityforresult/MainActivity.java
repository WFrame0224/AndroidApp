package com.example.activityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button bn;
    EditText selectCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn = (Button) findViewById(R.id.bn);
        selectCity = (EditText) findViewById(R.id.selectCity);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建需要对应于目标Activity的Intent
                Intent intent = new Intent(MainActivity.this,
                        SelectCityActivity.class);
                // 启动指定Activity并等待返回的结果，其中0是请求码，用于标识该请求
                startActivityForResult(intent, 0);
            }
        });
    }

    /**
     * Activity 提供了一个startActivityForResult方法，该方法用于启动指定Activity，并且期望获取
     * 指定Activity返回的结果，为了获取被启动的Activity返回的结果，需要从两方面着手
     *      1、当前Activity方法需要重写onActivityResult（...）方法，当被启动的Activity返回结果时，该
     * 方法将会被触发
     *      2、被启动的Activity需要调用setResult（）方法设置处理结果
     */
    // 重写onActivityResult方法,该方法以回调的方式来获取指定Activity返回的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 当requestCode、resultCode同时为0时，也就是处理特定的结果
        if (requestCode == 0 && resultCode == 0) {
            // 取出Intent里面的Extras数据
            Bundle data = intent.getExtras();
            // 取出Bundle中数据
            String resultCity = data.getString("city");
            // 修改文本框的内容
            selectCity.setText(resultCity);
        }
    }
}
