package com.example.textviewui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class ButtonViewTest extends AppCompatActivity {
    CheckBox[] love_colors = new CheckBox[3];
    public static byte CheckBoxIndex = 0b00000000;
    static String love_color = "";
    public void getChechBox(byte dex){
        CheckBoxIndex = (byte)(CheckBoxIndex & dex);
        switch (CheckBoxIndex){
            case 1:
                love_color = "红色";
                break;
            case 2:
                love_color = "蓝色";
                break;
            case 3:
                love_color = "红色、蓝色";
                break;
            case 4:
                love_color = "绿色";
                break;
            case 5:
                love_color = "红色、绿色";
                break;
            case 6:
                love_color = "蓝色、绿色";
                break;
            case 7:
                love_color = "红色、绿色、蓝色";
                break;
            default:
                love_color = "";
                break;
        }
        ((TextView) findViewById(R.id.show1)).setText("您最喜欢的颜色是" + love_color);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_activity);

        // 获取上面rg,show两个组件
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final TextView show = (TextView) findViewById(R.id.show);
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

        love_colors[0] = (CheckBox) findViewById(R.id.color1);
        love_colors[1] = (CheckBox) findViewById(R.id.color2);
        love_colors[2] = (CheckBox) findViewById(R.id.color3);
        love_colors[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getChechBox((byte)0x01);
                } else {
                    getChechBox((byte)0x7e);
                }
            }
        });
        love_colors[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getChechBox((byte)0x02);
                } else {
                    getChechBox((byte)0x7d);
                }
            }
        });
        love_colors[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getChechBox((byte)0x04);
                } else {
                    getChechBox((byte)0x7b);
                }
            }
        });


    }
}
