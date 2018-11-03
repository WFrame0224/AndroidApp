package com.example.bundletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView name = (TextView) findViewById(R.id.name);
        TextView passwd = (TextView) findViewById(R.id.passwd);
        TextView gender = (TextView) findViewById(R.id.gender);

        // 获取启动该Activ的Intent
        Intent intent = getIntent();
        // 直接通过Intent取出它所携带的Bundle数据包中的数据
        Person person = (Person) intent.getSerializableExtra("person");
        name.setText("您的用户名为：" + person.getName());
        passwd.setText("您的密码为" + person.getPasswd());
        gender.setText("您的性别为" + person.getGender());
    }
}
