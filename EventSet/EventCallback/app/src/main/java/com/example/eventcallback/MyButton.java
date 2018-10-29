package com.example.eventcallback;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyButton extends AppCompatButton {
    public MyButton(Context context, AttributeSet set){
        super(context,set);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        Log.v("OnTouch", "the onTouchEvent in MyButton");
        // 返回true，表明该事件不会向外扩散
//        return true;
        return false;
        // 返回false表明并未完全处理该事件，该事件依然向外扩散
    }
}
