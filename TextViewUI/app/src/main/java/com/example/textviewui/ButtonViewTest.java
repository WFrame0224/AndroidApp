package com.example.textviewui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ButtonViewTest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity);

        // 获取上面rg,show两个组件
        RadioGroup rg = (RadioGroup)findViewById(R.id.rg);
        final TextView show = (TextView)findViewById(R.id.show);
        // 为RadioGroup组件的OncheckedChanged事件绑定事件监视器
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // 根据用户勾选的单选按钮来动态改变tip字符串的值
                String tip = checkedId == R.id.male ? "您的性别是男性" : "您的性别是女性";
                //更改show组件中的文本
                show.setText(tip);
            }
        });
    }
}
