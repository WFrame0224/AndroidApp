package com.example.eventcallback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button)findViewById(R.id.bn);
        bn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Log.v("--Listener--","the TouchDown in Listener");
                }
                // 返回false表明该事件向外传播
                return false;
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        Log.v("--Activity--", "the onTouchDown in Activity");
        // 返回true，表明该事件不会向外扩散
//        return true;
        return false;
        // 返回false表明并未完全处理该事件，该事件依然向外扩散
    }
}
