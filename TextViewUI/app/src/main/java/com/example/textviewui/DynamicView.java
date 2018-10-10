package com.example.textviewui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class DynamicView extends AppCompatActivity {
    ToggleButton toggle;
    Switch switcher;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_view_activity);

        // 获取toogle和switch对象
        toggle = (ToggleButton)findViewById(R.id.toggle);
        switcher = (Switch)findViewById(R.id.switcher);
        final LinearLayout test = (LinearLayout)findViewById(R.id.line2);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){

                    // 设置LinearLayout垂直布局
                    test.setOrientation(LinearLayout.VERTICAL);
                    toggle.setChecked(true);
                    switcher.setChecked(true);
                }else{
                    test.setOrientation(LinearLayout.HORIZONTAL);
                    toggle.setChecked(false);
                    switcher.setChecked(false);
                }
            }
        };
        toggle.setOnCheckedChangeListener(listener);
        switcher.setOnCheckedChangeListener(listener);
    }
}
