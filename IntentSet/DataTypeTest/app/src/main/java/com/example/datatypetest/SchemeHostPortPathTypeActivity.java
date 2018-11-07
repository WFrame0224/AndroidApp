package com.example.datatypetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SchemeHostPortPathTypeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("指定Scheme、Host、Port、Path、Type匹配的Activity");
        tv.setTextSize(30);
        setContentView(tv);
    }
}
