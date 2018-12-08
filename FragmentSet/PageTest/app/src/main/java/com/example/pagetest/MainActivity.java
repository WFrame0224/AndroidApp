package com.example.pagetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    // 去掉menu大于3个后的动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_device:
                        mTextMessage.setText("device");
                        return true;
                    case R.id.navigation_person:
                        mTextMessage.setText("account");
                        return true;
                    case R.id.navigation_project:
                        mTextMessage.setText("project");
                        return true;
                    case R.id.navigation_score:
                        mTextMessage.setText("score");
                        return true;
                }
                return false;
            }
        });

    }

}
