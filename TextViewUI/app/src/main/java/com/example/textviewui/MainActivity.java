package com.example.textviewui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenEditTextView(View view){
        Intent intent = new Intent(this,EditTextTest.class);
        startActivity(intent);
    }
    public void OpenButtonView(View view){
        Intent intent = new Intent(this,ButtonViewTest.class);
        startActivity(intent);
    }
    public void OpenClockView(View view){
        Intent intent = new Intent(this,ClockView.class);
        startActivity(intent);
    }
    public void OpenDynamicView(View view){
        Intent intent = new Intent(this,DynamicView.class);
        startActivity(intent);
    }
}
