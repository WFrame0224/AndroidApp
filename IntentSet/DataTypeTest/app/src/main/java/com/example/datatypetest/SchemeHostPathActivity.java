package com.example.datatypetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SchemeHostPathActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("指定Scheme、Host、Path匹配的Activity");
        tv.setTextSize(30);
        setContentView(tv);
    }
}
